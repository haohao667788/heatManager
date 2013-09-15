package org.heatmanagment.spring.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.heatmanagment.hibernate.domain.BuildingInfo;
import org.heatmanagment.hibernate.domain.CommunityInfo;
import org.heatmanagment.hibernate.domain.CountyInfo;
import org.heatmanagment.hibernate.domain.DistrictInfo;
import org.heatmanagment.hibernate.domain.MachinesetInfo;
import org.heatmanagment.hibernate.domain.ProjectInfo;
import org.heatmanagment.hibernate.domain.StaffInfo;

import org.heatmanagment.hibernate.util.DateConverter;
import org.heatmanagment.hibernate.util.MapperTool;
import org.heatmanagment.hibernate.util.ResultHolder;

import org.heatmanagment.spring.entity.BuildingOut;
import org.heatmanagment.spring.entity.ProjectOut;
import org.heatmanagment.spring.entity.StaffOut;
import org.heatmanagment.spring.entity.SuccessOut;
import org.heatmanagment.spring.services.CountyService;
import org.heatmanagment.spring.services.DistrictService;
import org.heatmanagment.spring.services.ProjectService;
import org.heatmanagment.spring.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/data/daqu")
public class ProjectController {

	@Autowired
	private ProjectService projectSerivce;
	@Autowired
	private DistrictService districtService;

	@Autowired
	private CountyService countyService;

	@Autowired
	private StaffService staffService;

	@Autowired
	private MapperTool tool;

	@RequestMapping("/project/update")
	@ResponseBody
	public String saveOrUpdate(@RequestParam(required = false) Long pjtid,
			@RequestParam String pjtnum, @RequestParam String pjtname,
			@RequestParam Long ctyid, @RequestParam Long dstid,
			@RequestParam String middle,
			@RequestParam(required = false) String desp,
			@RequestParam String startDate) {

		this.projectSerivce.saveOrUpdateProject(pjtid, pjtname, pjtnum, ctyid,
				dstid, middle, desp, DateConverter.convert(startDate));
		return this.tool.successRetort();
	}

	@RequestMapping("/project/del")
	@ResponseBody
	public String delete(@RequestParam Long id) {
		this.projectSerivce.deleteProject(id);
		return this.tool.successRetort();
	}

	@RequestMapping("/project/list")
	@ResponseBody
	public String listItems(
			@RequestParam(required = false, defaultValue = "0") Integer start,
			@RequestParam(required = false, defaultValue = "20") Integer limit,
			@RequestParam(required = false) Long dstid) {
		SuccessOut out = new SuccessOut();
		out.reset();
		if (start == null) {
			start = new Integer(0);
		}
		if (limit == null) {
			limit = new Integer(20);
		}

		List<ProjectInfo> infos = this.projectSerivce.findPage(start, limit);
		List<Object> data = new ArrayList<Object>();

		/**
		 * Need re-write
		 */
		for (ProjectInfo i : infos) {
			ProjectOut temp = new ProjectOut();
			temp.setPjtid(i.getPjtid());
			temp.setPjtname(i.getPjtname());
			temp.setPjtnum(i.getPjtnum());
			temp.setDepartmentname(i.getDepartmentname());
			temp.setDesp(i.getDesp());
			temp.setMiddle(i.getMiddle());
			temp.setStartDate(i.getStartDate());
			DistrictInfo dst;
			CountyInfo cty;
			if ((dst = i.getDistrictInfo()) != null) {
				temp.setDstid(dst.getDstid());
				temp.setDstname(dst.getDstname());
			}
			if ((cty = i.getCountyInfo()) != null) {
				temp.setCtyid(cty.getCtyid());
				temp.setCtyname(cty.getCityname());
			}
			data.add(temp);
		}
		out.setData(data);
		out.setTotalProperty(this.projectSerivce.count());
		return this.tool.result(out);
	}

	@RequestMapping("/project/queryDaqu")
	@ResponseBody
	public String queryDaqu() {
		ResultHolder holder = new ResultHolder();
		List<DistrictInfo> dst = this.districtService.findAll();

		for (DistrictInfo i : dst) {
			holder.add(i.getDstid(), i.getDstname());
		}
		return this.tool.result(holder.getData());
	}

	@RequestMapping("/project/queryQuxian")
	@ResponseBody
	public String queryCty() {
		ResultHolder holder = new ResultHolder();
		List<CountyInfo> cty = this.countyService.findAll();

		for (CountyInfo i : cty) {
			holder.add(i.getCtyid(), i.getTownname());
		}
		return this.tool.result(holder.getData());
	}

	@RequestMapping("/project/assignPjt")
	@ResponseBody
	public String assignPjt(@RequestParam Long pjtid, @RequestParam Long stfid,
			@RequestParam(required = false) String desp) {
		this.projectSerivce.addMapping(pjtid, stfid, desp);
		return this.tool.successRetort();
	}

	@RequestMapping("/project/queryStaff")
	@ResponseBody
	public String queryStf() {
		List<StaffInfo> stf = this.staffService.findAll();
		ResultHolder holder = new ResultHolder();
		for (StaffInfo i : stf) {
			holder.add(i.getStfid(), i.getStfnumber() + "/" + i.getStfname());
		}
		return this.tool.result(holder.getData());
	}

	@RequestMapping("/project/relateEmp")
	@ResponseBody
	public String relateEmp(@RequestParam Long pjtid) {
		ResultHolder holder = new ResultHolder();
		List<StaffOut> out = this.staffService.findByPjtid(pjtid);
		for (StaffOut i : out) {
			holder.add(i.getStfid(), i.getStfnumber() + "/" + i.getStfname());
		}
		return this.tool.result(holder.getData());
	}
}
