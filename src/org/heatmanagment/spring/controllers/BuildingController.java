package org.heatmanagment.spring.controllers;

import java.util.ArrayList;
import java.util.List;

import org.heatmanagment.hibernate.domain.BuildingInfo;
import org.heatmanagment.hibernate.domain.CommunityInfo;
import org.heatmanagment.hibernate.domain.MachinesetInfo;
import org.heatmanagment.hibernate.util.FileUploader;
import org.heatmanagment.hibernate.util.MapperTool;
import org.heatmanagment.hibernate.util.ResultHolder;
import org.heatmanagment.spring.entity.BuildingOut;
import org.heatmanagment.spring.entity.SuccessOut;
import org.heatmanagment.spring.services.BuildingService;
import org.heatmanagment.spring.services.CommunityService;
import org.heatmanagment.spring.services.MachineSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
@RequestMapping("/data/level")
public class BuildingController {

	@Autowired
	private BuildingService buildingService;

	@Autowired
	private CommunityService communityService;

	@Autowired
	private MachineSetService machineSetService;

	@Autowired
	private FileUploader fileUploader;

	@Autowired
	private MapperTool tool;

	@RequestMapping("/loudong/update")
	@ResponseBody
	public String saveOrUpdate(@RequestParam(required = false) Long bldid,
			@RequestParam String bldname, @RequestParam String bldaddress,
			@RequestParam Long cmtid, @RequestParam Long mchid,
			@RequestParam String heattype, @RequestParam String gis,
			@RequestParam(value = "picaddress") CommonsMultipartFile file,
			@RequestParam(required = false) String desp) {
		SuccessOut out = new SuccessOut();
		out.reset();

		String filePath = null;
		if (!file.isEmpty()) {
			// upload the file
			if (bldid != null) {
				// user want to upload a new version of pic
				BuildingInfo temp = this.buildingService.findById(bldid);
				String tempPath = temp.getPicaddress();
				if (tempPath != null && !tempPath.equals("")) {
					this.fileUploader.deleteFile(tempPath);
				}
			}
			filePath = this.fileUploader.upload(file, "building");
		}

		this.buildingService.saveOrUpdateBuilding(bldid, bldname, bldaddress,
				cmtid, mchid, heattype, gis, filePath, desp);
		return this.tool.successRetort();
	}

	@RequestMapping("/loudong/del")
	@ResponseBody
	public String delete(@RequestParam Long id) {
		this.buildingService.deleteBuilding(id);
		return this.tool.successRetort();
	}

	@RequestMapping("/loudong/list")
	@ResponseBody
	public String listItems(
			@RequestParam(required = false, defaultValue = "0") Long start,
			@RequestParam(required = false, defaultValue = "20") Long limit,
			@RequestParam(required = false) Long cmtid) {
		SuccessOut out = new SuccessOut();
		out.reset();
		if (start == null) {
			start = 0l;
		}
		if (limit == null) {
			limit = 20l;
		}
		List<BuildingInfo> infos = null;
		List<Object> data = new ArrayList<Object>();
		if (cmtid != null) {
			infos = this.buildingService.findByCmtid(cmtid, start, limit);
			out.setTotalProperty(this.buildingService.countByCmtId(cmtid));
		} else {
			infos = this.buildingService.findPage(start, limit);
			out.setTotalProperty(this.buildingService.count());
		}
		for (BuildingInfo i : infos) {
			BuildingOut temp = new BuildingOut();
			temp.setBldid(i.getBldid());
			temp.setBldname(i.getBldname());
			temp.setBldaddress(i.getAddress());
			temp.setHeattype(i.getHeattype());
			temp.setGis(i.getGis());
			temp.setPicaddress(i.getPicaddress());
			temp.setDesp(i.getDesp());
			MachinesetInfo t;
			CommunityInfo c;
			if ((t = i.getMachinesetInfo()) != null) {
				temp.setMchid(t.getMchid());
				temp.setMchname(t.getMchname());
			}
			if ((c = i.getCommunityInfo()) != null) {
				temp.setCmtid(c.getCmtid());
				temp.setCmtname(c.getCmtname());
			}
			data.add(temp);
		}
		out.setData(data);
		return this.tool.result(out);
	}

	@RequestMapping("/loudong/queryShequ")
	@ResponseBody
	public String listCmt(@RequestParam(required = false) String query) {
		/**
		 * lack of parameter, no need to add extra item in the first place.
		 */
		ResultHolder holder = new ResultHolder();
		List<CommunityInfo> cmt = this.communityService.findAll();
		if (query != null) {
			holder.add(0l, "全部社区");
		}

		for (CommunityInfo i : cmt) {
			holder.add(i.getCmtid(), i.getCmtname());
		}
		return this.tool.result(holder.getData());
	}

	@RequestMapping("/loudong/queryMch")
	@ResponseBody
	public String listMch() {
		ResultHolder holder = new ResultHolder();
		List<MachinesetInfo> mch = this.machineSetService.findAll();
		for (MachinesetInfo i : mch) {
			holder.add(i.getMchid(), i.getMchname());
		}
		return this.tool.result(holder.getData());
	}
}
