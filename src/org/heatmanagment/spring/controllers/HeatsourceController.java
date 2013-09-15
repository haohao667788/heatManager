package org.heatmanagment.spring.controllers;

import java.util.ArrayList;
import java.util.List;

import org.heatmanagment.hibernate.domain.HeatsourceInfo;
import org.heatmanagment.hibernate.util.MapperTool;
import org.heatmanagment.spring.entity.HeatOut;
import org.heatmanagment.spring.entity.SuccessOut;
import org.heatmanagment.spring.services.HeatsourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/data/level")
public class HeatsourceController {

	@Autowired
	private HeatsourceService heatsourceService;

	@Autowired
	private MapperTool tool;

	@RequestMapping("/heat/list")
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
		List<HeatsourceInfo> src = this.heatsourceService
				.findPage(start, limit);
		List<Object> data = new ArrayList<Object>();
		for (HeatsourceInfo i : src) {
			HeatOut temp = new HeatOut();
			temp.setSrcid(i.getSrcid());
			temp.setSrcname(i.getSrcname());
			temp.setDstid(i.getDistrictInfo().getDstid());
			temp.setDstname(i.getDistrictInfo().getDstname());
			temp.setDesp(i.getDesp());
			temp.setSrcaddress(i.getAddress());
			temp.setHeattype(i.getHeattype());
			data.add(temp);
		}
		out.setData(data);
		out.setTotalProperty(this.heatsourceService.count());

		return this.tool.result(out);
	}

	@RequestMapping("/heat/update")
	@ResponseBody
	public String saveOrUpdate(@RequestParam(required = false) Long srcid,
			@RequestParam String srcname,
			@RequestParam(value = "srcaddress") String address,
			@RequestParam String heattype,
			@RequestParam(required = false) String desp,
			@RequestParam Long dstid) {

		this.heatsourceService.saveOrUpdate(srcid, srcname, address, heattype,
				desp, dstid);
		return this.tool.successRetort();
	}

	@RequestMapping("/heat/del")
	@ResponseBody
	public String delete(@RequestParam Long id) {
		this.heatsourceService.delete(id);
		return this.tool.successRetort();
	}
}
