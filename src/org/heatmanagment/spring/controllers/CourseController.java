package org.heatmanagment.spring.controllers;

import java.util.ArrayList;
import java.util.List;

import org.heatmanagment.hibernate.domain.Config;
import org.heatmanagment.hibernate.domain.CourseInfo;
import org.heatmanagment.hibernate.util.MapperTool;
import org.heatmanagment.hibernate.util.ResultHolder;
import org.heatmanagment.spring.entity.CourseOut;
import org.heatmanagment.spring.entity.SuccessOut;
import org.heatmanagment.spring.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/data/financespace")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private MapperTool tool;

	@RequestMapping(value = "/course/update")
	@ResponseBody
	public String saveOrUpdate(@RequestParam(required = false) Long crsid,
			@RequestParam String crsnum, @RequestParam String crsname,
			@RequestParam(required = false) String desp,
			@RequestParam String dealname) {
		this.courseService.saveOrUpdate(crsid, crsnum, crsname, desp, dealname);
		return this.tool.successRetort();
	}

	@RequestMapping(value = "/course/list")
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
		List<CourseInfo> infos = this.courseService.listItems(start, limit);
		List<Object> crs = new ArrayList<Object>();
		for (CourseInfo i : infos) {
			CourseOut temp = new CourseOut();
			temp.setCrsid(i.getCrsid());
			temp.setCrsname(i.getCrsname());
			temp.setCrsnum(i.getCrsnum());
			temp.setDealname(i.getDealname());
			temp.setDesp(i.getDesp());
			crs.add(temp);
		}
		out.setData(crs);
		out.setTotalProperty(this.courseService.count());
		return this.tool.result(out);
	}

	@RequestMapping(value = "/course/del")
	@ResponseBody
	public String delete(@RequestParam Long id) {
		this.courseService.delete(id);
		return this.tool.successRetort();
	}

	@RequestMapping("/course/queryDealname")
	@ResponseBody
	public String queryDealname() {
		ResultHolder holder = new ResultHolder();
		List<Config> cfg = this.courseService.listConfig();
		for (Config i : cfg) {
			holder.add(i.getDealname(), i.getDealname());
		}
		return this.tool.result(holder.getData());
	}
}
