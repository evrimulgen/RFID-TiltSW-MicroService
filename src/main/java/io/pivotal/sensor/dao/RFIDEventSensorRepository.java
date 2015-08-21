package io.pivotal.sensor.dao;

import java.util.Date;

import io.pivotal.sensor.model.RFIDEvent;

import org.springframework.data.repository.CrudRepository;


public interface RFIDEventSensorRepository extends CrudRepository<RFIDEvent, Long> {


	Iterable<RFIDEvent> findByRfidRfid(String rfid);
	
	Iterable<RFIDEvent> findByRfidRfidAndEventTimeBetween(String rfid, Date startDate, Date endDate);
}
