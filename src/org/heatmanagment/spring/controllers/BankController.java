package org.heatmanagment.spring.controllers;

import java.util.ArrayList;
import java.util.List;

import org.heatmanagment.hibernate.domain.BankInfo;

import org.heatmanagment.hibernate.domain.CourseInfo;
import org.heatmanagment.hibernate.util.MapperTool;
import org.heatmanagment.hibernate.util.ResultHolder;
import org.heatmanagment.spring.entity.BankOut;
import org.heatmanagment.spring.entity.SuccessOut;
import org.heatmanagment.spring.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/data/financespace")
public class BankController {

	@Autowired
	private BankService bankService;

	@Autowired
	private MapperTool tool;

	@RequestMapping("/bank/update")
	@ResponseBody
	public String saveOrUpdate(@RequestParam(required = false) Long bnkid,
			@RequestParam String bnknum, @RequestParam String bnkname,
			@RequestParam String accountnum, @RequestParam Long crsid,
			@RequestParam(required = false) String desp) {
		SuccessOut out = new SuccessOut();
		out.reset();

		this.bankService.saveOrUpdate(bnkid, bnknum, bnkname, accountnum, desp,
				crsid);
		return this.tool.successRetort();
	}

	@RequestMapping("/bank/del")
	@ResponseBody
	public String delete(@RequestParam Long id) {
		this.bankService.delete(id);
		return this.tool.successRetort();
	}

	@RequestMapping("/bank/list")
	@ResponseBody
	public String listItems(
			@RequestParam(required = false, defaultValue = "0") Integer start,
			@RequestParam(required = false, defaultValue = "20") Integer limit) {
		SuccessOut out = new SuccessOut();
		out.reset();
		if (start == null) {
			start = 0;
		}
		if (limit == null) {
			limit = 20;
		}
		List<BankInfo> infos = this.bankService.listItems(start, limit);
		ArrayList<Object> data = new ArrayList<Object>();
		out.setTotalProperty(this.bankService.count());

		for (BankInfo i : infos) {
			BankOut temp = new BankOut();
			temp.setBnkid(i.getBnkid());
			temp.setBnknum(i.getBnknum());
			temp.setBnkname(i.getBnkname());
			temp.setAccountnum(i.getAccountnum());
			temp.setDesp(i.getDesp());
			CourseInfo c;
			if ((c = i.getCourseInfo()) != null) {
				temp.setCrsid(c.getCrsid());
				temp.setCrsname(c.getCrsname());
			}
			data.add(temp);
		}
		out.setData(data);
		return this.tool.result(out);
	}

	@RequestMapping("/bank/queryCrs")
	@ResponseBody
	public String queryCrs() {
		ResultHolder holder = new ResultHolder();
		List<CourseInfo> crs = this.bankService.queryCourse();
		for (CourseInfo i : crs) {
			holder.add(i.getCrsid(), i.getCrsnum() + "/" + i.getCrsname());
		}
		return this.tool.result(holder.getData());
	}
}
