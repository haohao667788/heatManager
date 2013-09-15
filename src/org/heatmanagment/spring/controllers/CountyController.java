package org.heatmanagment.spring.controllers;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.heatmanagment.hibernate.domain.CountyInfo;
import org.heatmanagment.hibernate.util.MapperTool;
import org.heatmanagment.spring.entity.CountyOut;
import org.heatmanagment.spring.entity.SuccessOut;
import org.heatmanagment.spring.services.CountyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/data/level")
public class CountyController {

	@Autowired
	private CountyService countyService;

	@Autowired
	private MapperTool tool;

	@RequestMapping(value = "/quxian/list")
	@ResponseBody
	public String listCity(
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

		List cty = this.countyService.findPage(start, limit);
		out.setData(cty);
		out.setTotalProperty(this.countyService.count());

		return this.tool.result(out);
	}

	@RequestMapping(value = "/quxian/update")
	@ResponseBody
	public String saveOrUpdate(@RequestParam(required = false) Long ctyid,
			@RequestParam String townname, @RequestParam String cityname,
			@RequestParam(required = false) String desp) {
		this.countyService.saveOrUpdateCounty(ctyid, townname, cityname, desp);
		SuccessOut out = new SuccessOut();
		out.reset();
		return this.tool.successRetort();
	}

	@RequestMapping(value = "/quxian/del")
	@ResponseBody
	public String delete(@RequestParam Long id) {
		this.countyService.deleteCounty(id);
		return this.tool.successRetort();
	}
}
