package org.heatmanagment.spring.controllers;

import org.heatmanagment.spring.services.CountyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quxian")
public class CountyController {

	@Autowired
	private CountyService service;

	public void setCountyService(CountyService service) {
		this.service = service;
	}

	@RequestMapping("/tiangai")
	public void insertOrUpdate() {

	}

}
