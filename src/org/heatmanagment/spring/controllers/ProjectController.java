package org.heatmanagment.spring.controllers;

import java.util.List;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
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
	public String delete(@RequestParam Long pjtid) {
		try {
			this.projectSerivce.deleteProject(pjtid);
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
		String outCome = null;
		try {
			List<ProjectInfo> infos = this.projectSerivce.findAllProject(start,
					limit);
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
}
