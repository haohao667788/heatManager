package org.heatmanagment.spring.services;

import java.util.List;

import org.heatmanagment.hibernate.domain.HeatsourceInfo;
import org.heatmanagment.hibernate.domain.HeatsourceInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("heatsourceService")
@SuppressWarnings("unchecked")
@Transactional
public class HeatsourceServiceImpl implements HeatsourceService {

	@Autowired
	private HeatsourceInfoDAO dao;

	@Override
	public List<HeatsourceInfo> inquireAll() {
		return this.dao.findAll();
	}
}
