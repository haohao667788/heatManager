package org.heatmanagment.spring.services;

import java.util.List;

import org.heatmanagment.hibernate.domain.ChargeRecord;

public interface ChargeRecordService {

	List<ChargeRecord> listItems(int start, int limit);

}
