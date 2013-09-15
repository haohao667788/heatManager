package org.heatmanagment.spring.controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.heatmanagment.hibernate.domain.StaffInfo;
import org.heatmanagment.hibernate.util.MapperTool;
import org.heatmanagment.spring.entity.StaffOut;
import org.heatmanagment.spring.entity.SuccessOut;
import org.heatmanagment.spring.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/data/employee")
public class StaffController {

	@Autowired
	private StaffService staffService;

	@Autowired
	private MapperTool tool;

	@RequestMapping(value = "/employee/update")
	@ResponseBody
	public String saveOrUpdate(@RequestParam(required = false) Long stfid,
			@RequestParam String stfname, @RequestParam String stfnumber,
			@RequestParam String phone, @RequestParam String loginname,
			@RequestParam String pwd, @RequestParam String department,
			@RequestParam Long verifytype) {
		Timestamp startdate = new Timestamp(System.currentTimeMillis());
		this.staffService.saveOrUpdateCounty(stfid, stfname, stfnumber,
				startdate, phone, loginname, pwd, department, verifytype);
		return this.tool.successRetort();
	}

	@RequestMapping(value = "/employee/list")
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

		List<StaffInfo> infos = this.staffService.findPage(start, limit);
		List<Object> data = new ArrayList<Object>();
		for (StaffInfo i : infos) {
			StaffOut temp = new StaffOut();
			temp.setStfid(i.getStfid());
			temp.setStfname(i.getStfname());
			temp.setStfnumber(i.getStfnumber());
			temp.setStartdate(i.getStartdate());
			temp.setPhone(i.getPhone());
			temp.setPwd(i.getPwd());
			temp.setLoginname(i.getLoginname());
			temp.setVerifytype(i.getVerifytype());
			temp.setDepartment(i.getDepartment());
			data.add(temp);
		}
		out.setData(data);
		out.setTotalProperty(this.staffService.count());
		return this.tool.result(out);
	}

	@RequestMapping(value = "/employee/del")
	@ResponseBody
	public String delete(@RequestParam Long id) {
		this.staffService.delete(id);
		return this.tool.successRetort();
	}
}
