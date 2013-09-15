package org.heatmanagment.spring.controllers;

import java.util.ArrayList;
import java.util.List;

import org.heatmanagment.hibernate.domain.BuildingInfo;
import org.heatmanagment.hibernate.domain.CommunityInfo;
import org.heatmanagment.hibernate.domain.UnitInfo;
import org.heatmanagment.hibernate.util.FileUploader;
import org.heatmanagment.hibernate.util.MapperTool;
import org.heatmanagment.hibernate.util.ResultHolder;
import org.heatmanagment.spring.entity.SuccessOut;
import org.heatmanagment.spring.entity.UnitOut;
import org.heatmanagment.spring.services.BuildingService;
import org.heatmanagment.spring.services.CommunityService;
import org.heatmanagment.spring.services.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
@RequestMapping("/data/level")
public class UnitController {

	@Autowired
	private UnitService unitService;

	@Autowired
	private CommunityService communityService;

	@Autowired
	private BuildingService buildingService;

	@Autowired
	private FileUploader fileUploader;

	@Autowired
	private MapperTool tool;

	@RequestMapping("/danyuan/del")
	@ResponseBody
	public String delete(@RequestParam Long id) {
		this.unitService.deleteUnit(id);
		return this.tool.successRetort();
	}

	@RequestMapping("/danyuan/update")
	@ResponseBody
	public String saveOrUpdate(
			@RequestParam(required = false) Long untid,
			@RequestParam String untname,
			@RequestParam Long bldid,
			@RequestParam(required = false) Long cmtid,
			@RequestParam(required = false) String gis,
			@RequestParam(required = false, value = "picaddress") CommonsMultipartFile file,
			@RequestParam(required = false) String desp) {
		SuccessOut out = new SuccessOut();
		out.reset();

		String filePath = null;
		if (file != null && !file.isEmpty()) {
			// upload the file
			if (untid != null) {
				// user want to upload a new version of pic
				UnitInfo temp = this.unitService.findById(untid);
				String tempPath = temp.getPicaddress();
				if (tempPath != null && !tempPath.equals("")) {
					this.fileUploader.deleteFile(tempPath);
				}
			}
			filePath = this.fileUploader.upload(file, "unit");
		}

		this.unitService.saveOrUpdateUnit(untid, untname, bldid, cmtid, gis,
				filePath);
		return this.tool.successRetort();
	}

	@RequestMapping("/danyuan/list")
	@ResponseBody
	public String inquireAll(
			@RequestParam(required = false, defaultValue = "0") Integer start,
			@RequestParam(required = false, defaultValue = "20") Integer limit) {
		SuccessOut out = new SuccessOut();
		out.reset();
		if (start == null) {
			start = new Integer(0);
		}
		if (limit == null) {
			limit = new Integer(20);
		}
		List<UnitInfo> infos = this.unitService.findPage(start, limit);
		List<Object> data = new ArrayList<Object>();

		for (UnitInfo i : infos) {
			UnitOut temp = new UnitOut();
			temp.setUntid(i.getUntid());
			temp.setUntname(i.getUntname());
			temp.setGis(i.getGis());
			temp.setDesp(i.getDesp());
			temp.setPicaddress(i.getPicaddress());
			CommunityInfo cmt;
			BuildingInfo bld;
			if ((cmt = i.getCommunityInfo()) != null) {
				temp.setCmtid(cmt.getCmtid());
				temp.setCmtname(cmt.getCmtname());
			}
			if ((bld = i.getBuildingInfo()) != null) {
				temp.setBldid(bld.getBldid());
				temp.setBldname(bld.getBldname());
			}
			data.add(temp);
		}
		out.setData(data);
		out.setTotalProperty(this.unitService.count());
		return this.tool.result(out);
	}

	@RequestMapping("/danyuan/queryShequ")
	@ResponseBody
	public String inquireCmt() {
		ResultHolder holder = new ResultHolder();
		List<CommunityInfo> cmt = this.communityService.findAll();

		holder.add(0l, "全部社区");

		for (CommunityInfo i : cmt) {
			holder.add(i.getCmtid(), i.getCmtname());
		}
		return this.tool.result(holder.getData());
	}

	@RequestMapping("/danyuan/queryLoudong")
	@ResponseBody
	public String inquireBld(@RequestParam(required = false) String query,
			@RequestParam(required = false) Long cmtid) {

		ResultHolder holder = new ResultHolder();
		/**
		 * if query is 0, return all the building info. else return the selected
		 * ones.
		 */
		if (query == null) {
			List<BuildingInfo> bld = this.buildingService.findAll();
			for (BuildingInfo i : bld) {
				holder.add(i.getBldid(), i.getBldname());
			}
		} else if (query.equals("true")) {
			if (cmtid == null || cmtid == 0) {
				List<BuildingInfo> bld = this.buildingService.findAll();

				holder.add(0l, "全部楼栋");
				for (BuildingInfo i : bld) {
					holder.add(i.getBldid(), i.getBldname());
				}
			} else {
				List<BuildingInfo> bld = this.buildingService
						.findAllByCmtId(cmtid);
				return "[[6,\"fda\"]]";
				// for (BuildingInfo i : bld) {
				// holder.add(i.getBldid(), i.getBldname());
				// }
			}
		}
		return this.tool.result(holder.getData());
	}

	@RequestMapping("/danyuan/queryDanyuan")
	@ResponseBody
	public String queryUnt(@RequestParam Long bldid) {

		ResultHolder holder = new ResultHolder();

		List<UnitInfo> unt = this.unitService.findByBldid(bldid);
		for (UnitInfo i : unt) {
			holder.add(i.getUntid(), i.getUntname());
		}
		return this.tool.result(holder.getData());
	}
}
