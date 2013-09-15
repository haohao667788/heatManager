package org.heatmanagment.spring.controllers;

import javax.servlet.http.HttpServletResponse;

import org.heatmanagment.hibernate.util.FileUploader;
import org.heatmanagment.spring.entity.SuccessOut;
import org.heatmanagment.spring.services.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/pic")
public class PicController {

	@Autowired
	private CommunityService communityService;

	@Autowired
	private FileUploader fileUploader;

	@RequestMapping(value = "/{file_name:.+}")
	@ResponseBody
	public FileSystemResource getPic(@PathVariable("file_name") String fileName) {
		try {
			return new FileSystemResource(this.fileUploader.getFile(fileName));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
