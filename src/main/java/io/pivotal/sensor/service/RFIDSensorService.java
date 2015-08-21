package io.pivotal.sensor.service;

import java.util.Date;

import io.pivotal.sensor.model.RFID;
import io.pivotal.sensor.model.RFIDEvent;

public interface RFIDSensorService {

	void saveRFIDEvent(RFIDEvent event);
	
	RFID findByRFID(String rfid);
	
	Iterable<RFIDEvent> findAllRfidEventsByRfid(String rfid);
	
	Iterable<RFIDEvent> findAllRfidEventsByRfidBetween(String rfid, Date startTime, Date endTime);
	
	Iterable<RFID> findAllRfid();
	
	RFID findRFIDByUserId(Long userId);
	
}
