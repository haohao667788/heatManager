package org.heatmanagment.spring.services;

import java.util.List;

import org.heatmanagment.hibernate.domain.HeatsourceInfo;

public interface HeatsourceService {

	List<HeatsourceInfo> inquireAll();
}
