package org.heatmanagment.spring.controllers;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.heatmanagment.hibernate.domain.BuildingInfo;
import org.heatmanagment.hibernate.domain.CommunityInfo;
import org.heatmanagment.hibernate.domain.HeatsourceInfo;
import org.heatmanagment.spring.entity.BuildingOut;
import org.heatmanagment.spring.entity.SuccessOut;
import org.heatmanagment.spring.services.BuildingService;
import org.heatmanagment.spring.services.CommunityService;
import org.heatmanagment.spring.services.HeatsourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.net.httpserver.Authenticator.Success;

@Controller
@RequestMapping("/data/level")
public class BuildingController {

	@Autowired
	private BuildingService buildingService;

	@Autowired
	private CommunityService communityService;

	@Autowired
	private HeatsourceService heatsourceService;

	private ObjectMapper mapper;

	public BuildingController() {
		this.mapper = new ObjectMapper();
	}

	@RequestMapping("/loudong/update")
	@ResponseBody
	public String saveOrUpdate(@RequestParam(required = false) Long bldid,
			@RequestParam String bldname, @RequestParam Long cmtid,
			@RequestParam Long srcid, @RequestParam String heattype,
			@RequestParam String gis, @RequestParam String picaddress,
			@RequestParam String desp) {
		return "";

	}

	@RequestMapping("/loudong/del")
	@ResponseBody
	public String delete(@RequestParam Long bldid) {
		this.buildingService.deleteBuilding(bldid);
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

	@RequestMapping("/loudong/list")
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
		List<BuildingInfo> infos = this.buildingService.findPage(start, limit);

		BuildingOut out = new BuildingOut();
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

	@RequestMapping("/loudong/queryShequ")
	@ResponseBody
	public String inquireCmt(@RequestParam(required = false) String query) {
		/**
		 * lack of parameter, no need to add extra item in the first place.
		 */
		ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
		List<CommunityInfo> cmt = this.communityService.findAll();
		if (query != null) {
			ArrayList<Object> begin = new ArrayList<Object>();
			begin.add(new Long(0));
			begin.add("全部社区");
			data.add(begin);
		}

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

	@RequestMapping("/loudong/queryHeat")
	@ResponseBody
	public String inquireHeat() {

		List<HeatsourceInfo> heat = this.heatsourceService.inquireAll();
		ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();

		for (HeatsourceInfo i : heat) {
			ArrayList<Object> temp = new ArrayList<Object>(2);
			temp.add(i.getSrcid());
			temp.add(i.getSrcname());
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
}
