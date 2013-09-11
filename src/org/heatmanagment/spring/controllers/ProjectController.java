package org.heatmanagment.spring.controllers;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.heatmanagment.hibernate.domain.CommunityInfo;
import org.heatmanagment.hibernate.domain.CountyInfo;
import org.heatmanagment.hibernate.domain.DistrictInfo;
import org.heatmanagment.hibernate.domain.ProjectInfo;
import org.heatmanagment.spring.entity.BoundOut;
import org.heatmanagment.spring.entity.DistrictOut;
import org.heatmanagment.spring.entity.ProjectOut;
import org.heatmanagment.spring.entity.SuccessOut;
import org.heatmanagment.spring.services.DistrictService;
import org.heatmanagment.spring.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
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

	private ObjectMapper mapper;
	private SuccessOut success;

	public ProjectController() {
		this.mapper = new ObjectMapper();
		this.success = new SuccessOut();
		this.success.reset();
	}

	@RequestMapping("/project/update")
	@ResponseBody
	public String saveOrUpdate(@RequestParam(required = false) Long pjtid,
			@RequestParam String pjtname, @RequestParam Long ctyid,
			@RequestParam Long dstid, @RequestParam String middle,
			@RequestParam String desp) {
		try {
			this.projectSerivce.saveOrUpdateProject(pjtid, pjtname, ctyid,
					dstid, middle, desp);
			return this.mapper.writeValueAsString(this.success);
		} catch (Exception e) {
			this.success.setSuccess(false);
			this.success.setMessage(e.getMessage());
			String re = null;
			try {
				re = this.mapper.writeValueAsString(this.success);
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				this.success.reset();
			}
			return re;
		}
	}

	@RequestMapping("/project/del")
	@ResponseBody
	public String delete(@RequestParam Long id) {
		try {
			this.projectSerivce.deleteProject(id);
			return this.mapper.writeValueAsString(this.success);
		} catch (Exception e) {
			this.success.setSuccess(false);
			this.success.setMessage(e.getMessage());
			String re = null;
			try {
				re = this.mapper.writeValueAsString(this.success);
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				this.success.reset();
			}
			return re;
		}
	}

	@RequestMapping("/project/list")
	@ResponseBody
	public String inquire(
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
			List<ProjectInfo> infos = this.projectSerivce
					.findPage(start, limit);
			if (infos == null) {
				SuccessOut t = new SuccessOut();
				t.setSuccess(false);
				t.setMessage("没有任何项目记录");
				return this.mapper.writeValueAsString(t);
			}
			ProjectOut out = new ProjectOut();
			out.setSuccess(true);
			out.setMessage("");
			out.setData(infos);
			out.setTotalProperty(infos.size());
			outCome = this.mapper.writeValueAsString(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outCome;
	}

	@RequestMapping("/project/queryDaqu")
	@ResponseBody
	public String queryDaqu() {
		List<DistrictInfo> dst = this.districtService.findAll();
		ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();

		for (DistrictInfo i : dst) {
			ArrayList<Object> temp = new ArrayList<Object>(2);
			temp.add(i.getDstid());
			temp.add(i.getDstname());
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
