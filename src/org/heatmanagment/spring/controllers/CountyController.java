package org.heatmanagment.spring.controllers;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.heatmanagment.hibernate.domain.CountyInfo;
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

	private ObjectMapper mapper;

	public CountyController() {
		this.mapper = new ObjectMapper();
	}

	@RequestMapping("/quxian/list")
	@ResponseBody
	public String listCity(
			@RequestParam(required = false, defaultValue = "0") Integer start,
			@RequestParam(required = false, defaultValue = "20") Integer limit) {
		if (start == null) {
			start = new Integer(0);
		}
		if (limit == null) {
			limit = new Integer(20);
		}
		String outCome = null;
		try {
			List<CountyInfo> cty = this.countyService.findPage(start, limit);
			CountyOut out = new CountyOut();
			out.setSuccess(true);
			out.setMessage("");
			out.setData(cty);
			out.setTotalProperty(cty.size());

			outCome = this.mapper.writeValueAsString(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outCome;
	}

	@RequestMapping("/quxian/update")
	@ResponseBody
	public String saveOrUpdate(@RequestParam Long ctyid,
			@RequestParam String townname, @RequestParam String cityname,
			@RequestParam String desp) {
		this.countyService.saveOrUpdateCounty(ctyid, townname, cityname, desp);
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

	@RequestMapping(value = "/quxian/del")
	@ResponseBody
	public String delete(@RequestParam Long id) {

		SuccessOut out = new SuccessOut();
		out.reset();
		String outCome = null;
		try {
			this.countyService.deleteCounty(id);
			outCome = this.mapper.writeValueAsString(out);
		} catch (Exception e) {
			out.setSuccess(false);
			out.setMessage(e.getMessage());
			String re = null;
			try {
				re = this.mapper.writeValueAsString(out);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return re;
		}
		return outCome;
	}
}
