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
	public String update(@RequestBody String dstBody, Writer writer)
			throws IOException {
		DistrictInfo info = this.mapper.readValue(dstBody, DistrictInfo.class);
		this.districtService.saveOrUpdateDistrict(info.getDstid(),
				info.getDstname(), info.getComm());
		return this.mapper.writeValueAsString(this.success);
	}

	@RequestMapping(value = "/daqu/list")
	@ResponseBody
	public String inquire(@RequestBody String bound) {
		String outCome = null;
		try {
			BoundOut in = this.mapper.readValue(bound, BoundOut.class);
			List<DistrictInfo> infos = this.districtService.findAllDistrict(
					in.getStart(), in.getLimit());
			DistrictOut out = new DistrictOut();
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

	@RequestMapping(value = "/daqu/del")
	@ResponseBody
	public String delete(@RequestBody String id) {

		String outCome = null;
		try {
			DistrictInfo info = this.mapper.readValue(id, DistrictInfo.class);
			this.districtService.deleteDistrict(info.getDstid());
			outCome = this.mapper.writeValueAsString(this.success);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outCome;
	}
}
