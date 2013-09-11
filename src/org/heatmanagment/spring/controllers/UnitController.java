package org.heatmanagment.spring.controllers;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.heatmanagment.hibernate.domain.BuildingInfo;
import org.heatmanagment.hibernate.domain.CommunityInfo;
import org.heatmanagment.hibernate.domain.UnitInfo;
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

@Controller
@RequestMapping("/data/level")
public class UnitController {

	@Autowired
	private UnitService unitService;

	@Autowired
	private CommunityService communityService;

	@Autowired
	private BuildingService buildingService;

	private ObjectMapper mapper;

	public UnitController() {
		this.mapper = new ObjectMapper();
	}

	@RequestMapping("/danyuan/del")
	@ResponseBody
	public String delete(@RequestParam Long id) {
		this.unitService.deleteUnit(id);
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

	@RequestMapping("/danyuan/list")
	@ResponseBody
	public String inquireAll(
			@RequestParam(required = false, defaultValue = "0") Integer start,
			@RequestParam(required = false, defaultValue = "20") Integer limit) {
		if (start == null) {
			start = new Integer(0);
		}
		if (limit == null) {
			limit = new Integer(20);
		}
		List<UnitInfo> infos = this.unitService.findPage(start, limit);

		UnitOut out = new UnitOut();
		out.setSuccess(true);
		out.setMessage("");
		out.setData(infos);
		out.setTotalProperty(infos.size());
		String outCome = null;
		try {
			outCome = this.mapper.writeValueAsString(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outCome;
	}

	@RequestMapping("/danyuan/queryShequ")
	@ResponseBody
	public String inquireCmt() {
		ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
		List<CommunityInfo> cmt = this.communityService.findAll();

		ArrayList<Object> begin = new ArrayList<Object>();
		begin.add(new Long(0));
		begin.add("全部社区");
		data.add(begin);

		for (CommunityInfo i : cmt) {
			ArrayList<Object> temp = new ArrayList<Object>(2);
			temp.add(i.getCmtid());
			temp.add(i.getCmtname());
			data.add(temp);
		}
		String outCome = null;
		try {
			outCome = this.mapper.writeValueAsString(data);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return outCome;
	}

	@RequestMapping("/danyuan/queryLoudong")
	@ResponseBody
	public String inquireBld(@RequestParam Long query) {

		/**
		 * if query is 0, return all the building info. else return the selected
		 * ones.
		 */
		ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
		if (query.equals(0)) {
			List<BuildingInfo> bld = this.buildingService.findAll();

			ArrayList<Object> begin = new ArrayList<Object>();
			begin.add(new Long(0));
			begin.add("全部楼栋");
			data.add(begin);

			for (BuildingInfo i : bld) {
				ArrayList<Object> temp = new ArrayList<Object>(2);
				temp.add(i.getBldid());
				temp.add(i.getBldname());
				data.add(temp);
			}
		} else {
			List<BuildingInfo> bld = this.buildingService.findByCmtid(query);
			for (BuildingInfo i : bld) {
				ArrayList<Object> temp = new ArrayList<Object>(2);
				temp.add(i.getBldid());
				temp.add(i.getBldname());
				data.add(temp);
			}
		}
		String outCome = null;
		try {
			outCome = this.mapper.writeValueAsString(data);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return outCome;
	}

	/**
	 * @RequestMapping("/danyuan/queryMachine")
	 * @ResponseBody
	 */

}
