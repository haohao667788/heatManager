package org.heatmanagment.spring.controllers;

import java.util.ArrayList;
import java.util.List;

import org.heatmanagment.hibernate.domain.HeatsourceInfo;
import org.heatmanagment.hibernate.domain.MachinesetInfo;
import org.heatmanagment.hibernate.util.MapperTool;
import org.heatmanagment.hibernate.util.ResultHolder;
import org.heatmanagment.spring.entity.MachineSetOut;
import org.heatmanagment.spring.entity.SuccessOut;
import org.heatmanagment.spring.services.HeatsourceService;
import org.heatmanagment.spring.services.MachineSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/data/level")
public class MachineSetController {

	@Autowired
	private MachineSetService machineSetService;

	@Autowired
	private HeatsourceService heatsourceService;

	@Autowired
	private MapperTool tool;

	@RequestMapping("/mch/update")
	@ResponseBody
	public String update(@RequestParam(required = false) Long mchid,
			@RequestParam String mchname, @RequestParam String gis,
			@RequestParam(required = false) String desp,
			@RequestParam Long srcid, @RequestParam(required = false) Long clsid) {

		this.machineSetService.saveOrUpdate(mchid, mchname, gis, desp, srcid,
				clsid);
		return this.tool.successRetort();
	}

	@RequestMapping("/mch/list")
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
		List<MachinesetInfo> src = this.machineSetService
				.findPage(start, limit);
		List<Object> data = new ArrayList<Object>();
		for (MachinesetInfo i : src) {
			MachineSetOut temp = new MachineSetOut();
			if (i.getHeatsourceInfo() != null) {
				temp.setSrcid(i.getHeatsourceInfo().getSrcid());
				temp.setSrcname(i.getHeatsourceInfo().getSrcname());
			}
			if (i.getClassInfo() != null) {
				temp.setClsid(i.getClassInfo().getClsid());
				temp.setClsname(i.getClassInfo().getClsname());
			}
			temp.setMchid(i.getMchid());
			temp.setMchname(i.getMchname());
			temp.setGis(i.getGis());
			temp.setDesp(i.getDesp());
			data.add(temp);
		}
		out.setData(data);
		out.setTotalProperty(this.machineSetService.count());
		return this.tool.result(out);
	}

	@RequestMapping("/mch/del")
	@ResponseBody
	public String delete(@RequestParam Long id) {
		this.machineSetService.delete(id);
		return this.tool.successRetort();
	}

	@RequestMapping("/mch/querySrc")
	@ResponseBody
	public String querySrc() {
		ResultHolder holder = new ResultHolder();
		List<HeatsourceInfo> src = this.heatsourceService.listAll();
		for (HeatsourceInfo i : src) {
			holder.add(i.getSrcid(), i.getSrcname());
		}
		return this.tool.result(holder.getData());
	}
}
