package org.heatmanagment.spring.controllers;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.heatmanagment.hibernate.domain.CommunityInfo;
import org.heatmanagment.hibernate.util.FileUploader;
import org.heatmanagment.spring.entity.CommunityOut;
import org.heatmanagment.spring.entity.SuccessOut;
import org.heatmanagment.spring.services.CommunityService;
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
	private FileUploader fileUploader;

	private ObjectMapper mapper;

	public CommunityController() {
		this.mapper = new ObjectMapper();
	}

	@RequestMapping(value = "/shequ/update")
	@ResponseBody
	public String saveOrUpdate(@RequestParam(required = false) Long cmtid,
			@RequestParam String cmtname, @RequestParam String briefname,
			@RequestParam String cmtaddress, @RequestParam String desp,
			@RequestParam String gis,
			@RequestParam(value = "picaddress") CommonsMultipartFile file) {
		SuccessOut out = new SuccessOut();
		out.reset();
		/**
		 * store the file if it is NOT empty. name would be preferable for
		 * serving as the absolute name.
		 */
		String filePath = null;
		if (!file.isEmpty()) {
			// upload the file
			if (cmtid != null) {
				// user want to upload a new version of pic
				CommunityInfo temp = this.communityService.findById(cmtid);
				String tempPath = temp.getPicaddress();
				if (tempPath != null && !tempPath.equals("")) {
					this.fileUploader.deleteFile(tempPath);
				}
			}
			filePath = this.fileUploader.upload(file, "community", cmtname);
		}

		// } else if(cmtid == null){
		// out.setSuccess(false);
		// out.setMessage("File is empty,upload failed");
		// try {
		// return this.mapper.writeValueAsString(out);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }

		this.communityService.saveOrUpdateCommunity(cmtid, cmtname, briefname,
				cmtaddress, desp, gis, filePath);
		String outCome = null;
		try {
			outCome = this.mapper.writeValueAsString(out);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				out.setSuccess(false);
				out.setMessage(e.getMessage());
				return this.mapper.writeValueAsString(out);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return outCome;
	}

	@RequestMapping(value = "/shequ/del")
	@ResponseBody
	public String delete(@RequestParam Long id) {
		this.communityService.deleteCommunity(id);
		SuccessOut out = new SuccessOut();
		out.reset();
		String outCome = null;
		try {
			outCome = this.mapper.writeValueAsString(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outCome;
	}

	@RequestMapping("/shequ/list")
	@ResponseBody
	public String inquire(
			@RequestParam(required = false, defaultValue = "0") Integer start,
			@RequestParam(required = false, defaultValue = "20") Integer limit) {
		if (start == null) {
			start = new Integer(0);
		}
		if (limit == null) {
			limit = new Integer(20);
		}
		List<CommunityInfo> infos = this.communityService
				.findPage(start, limit);
		String outCome = null;
		try {
			if (infos == null) {
				SuccessOut t = new SuccessOut();
				t.setSuccess(false);
				t.setMessage("没有任何社区记录");
				return this.mapper.writeValueAsString(t);
			}

			CommunityOut out = new CommunityOut();
			out.setSuccess(true);
			out.setMessage("");
			out.setData(infos);
			out.setTotalProperty(infos.size());
			outCome = this.mapper.writeValueAsString(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outCome;
	}
}
