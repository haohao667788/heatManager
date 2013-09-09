package org.heatmanagment.spring.controllers;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.heatmanagment.hibernate.domain.DistrictInfo;
import org.heatmanagment.spring.entity.BoundOut;
import org.heatmanagment.spring.entity.DistrictOut;
import org.heatmanagment.spring.entity.SuccessOut;
import org.heatmanagment.spring.services.DistrictService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/data/daqu")
public class DistrictController {

	@Autowired
	private DistrictService districtService;
	private SuccessOut success;

	private ObjectMapper mapper = new ObjectMapper();

	public DistrictController() {
		this.success = new SuccessOut();
		success.setSuccess(true);
		success.setMessage("");
	}

	@RequestMapping(value = "/daqu/update")
	@ResponseBody
	public String saveOrUpdate(@RequestParam(required = false) Long dstid,
			@RequestParam String dstname, @RequestParam String desp)
			throws IOException {
		this.districtService.saveOrUpdateDistrict(dstid, dstname, desp);
		return this.mapper.writeValueAsString(this.success);
	}

	@RequestMapping(value = "/daqu/list")
	@ResponseBody
	public String inquire(
			@RequestParam(required = false, defaultValue = "0") Integer start,
			@RequestParam(required = false, defaultValue = "20") Integer limit) {

		String outCome = null;
		try {
			List<DistrictInfo> infos = this.districtService.findAllDistrict(
					start, limit);
			DistrictOut out = new DistrictOut();
			out.setSuccess(true);
			out.setMessage("");
			out.setData(infos);
			out.setTotalProperty(infos.size());
			outCome = this.mapper.writeValueAsString(out);
			// System.out.println("District outCome : " + outCome);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outCome;
	}

	@RequestMapping(value = "/daqu/del")
	@ResponseBody
	public String delete(@RequestParam Long dstid) {

		String outCome = null;
		try {
			this.districtService.deleteDistrict(dstid);
			outCome = this.mapper.writeValueAsString(this.success);
		} catch (Exception e) {
			this.success.setSuccess(false);
			this.success.setMessage(e.getMessage());
			String re = null;
			try {
				re = this.mapper.writeValueAsString(this.success);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			this.success.reset();
			return re;
		}
		return outCome;
	}
}
