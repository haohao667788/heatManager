package org.heatmanagment.spring.controllers;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.heatmanagment.hibernate.domain.CommunityInfo;
import org.heatmanagment.hibernate.domain.ProjectInfo;
import org.heatmanagment.hibernate.util.FileUploader;
import org.heatmanagment.hibernate.util.MapperTool;
import org.heatmanagment.hibernate.util.ResultHolder;
import org.heatmanagment.spring.entity.CommunityOut;
import org.heatmanagment.spring.entity.SuccessOut;
import org.heatmanagment.spring.services.CommunityService;
import org.heatmanagment.spring.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
@RequestMapping("/data/level")
public class CommunityController {

	@Autowired
	private CommunityService communityService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private FileUploader fileUploader;

	@Autowired
	private MapperTool tool;

	@RequestMapping(value = "/shequ/update")
	@ResponseBody
	public String saveOrUpdate(
			@RequestParam(required = false) Long cmtid,
			@RequestParam String cmtname,
			@RequestParam String briefname,
			@RequestParam String cmtaddress,
			@RequestParam(required = false) String desp,
			@RequestParam String gis,
			@RequestParam(value = "picaddress", required = false) CommonsMultipartFile file) {
		SuccessOut out = new SuccessOut();
		out.reset();
		/**
		 * store the file if it is NOT empty. name would be preferable for
		 * serving as the absolute name.
		 */
		String filePath = null;
		if (file != null && !file.isEmpty()) {
			// upload the file
			if (cmtid != null) {
				// user want to upload a new version of pic
				CommunityInfo temp = this.communityService.findById(cmtid);
				String tempPath = temp.getPicaddress();
				if (tempPath != null && !tempPath.equals("")) {
					this.fileUploader.deleteFile(tempPath);
				}
			}
			filePath = this.fileUploader.upload(file, "community");
		}

		this.communityService.saveOrUpdateCommunity(cmtid, cmtname, briefname,
				cmtaddress, desp, gis, filePath);
		return this.tool.successRetort();
	}

	@RequestMapping(value = "/shequ/del")
	@ResponseBody
	public String delete(@RequestParam Long id) {
		this.communityService.deleteCommunity(id);
		return this.tool.successRetort();
	}

	@RequestMapping("/shequ/list")
	@ResponseBody
	public String listItems(
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
		List infos = this.communityService.findPage(start, limit);
		out.setData(infos);
		out.setTotalProperty(this.communityService.count());
		return this.tool.result(out);
	}

}
