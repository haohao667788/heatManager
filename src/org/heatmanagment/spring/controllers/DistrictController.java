package org.heatmanagment.spring.controllers;

import java.util.List;

import org.heatmanagment.hibernate.util.MapperTool;
import org.heatmanagment.spring.entity.SuccessOut;
import org.heatmanagment.spring.services.DistrictService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/data/daqu")
public class DistrictController {

	@Autowired
	private DistrictService districtService;

	@Autowired
	private MapperTool tool;

	@RequestMapping(value = "/daqu/update")
	@ResponseBody
	public String saveOrUpdate(@RequestParam(required = false) Long dstid,
			@RequestParam String dstname, @RequestParam String desp) {
		this.districtService.saveOrUpdateDistrict(dstid, dstname, desp);
		return this.tool.successRetort();
	}

	@RequestMapping(value = "/daqu/list")
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
		String outCome = null;
		try {
			List infos = this.districtService.findPage(start, limit);
			out.setData(infos);
			out.setTotalProperty(this.districtService.count());
			outCome = this.tool.result(out);
			// System.out.println("District outCome : " + outCome);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outCome;
	}

	@RequestMapping(value = "/daqu/del")
	@ResponseBody
	public String delete(@RequestParam Long id) {
		this.districtService.deleteDistrict(id);
		return this.tool.successRetort();
	}
}
