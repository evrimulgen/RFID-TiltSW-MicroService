package io.pivotal.sensor.service;

import java.util.Date;

import io.pivotal.sensor.dao.RFIDEventSensorRepository;
//import io.pivotal.sensor.dao.RFIDRepository;
import io.pivotal.sensor.dao.RFIDSensorRepository;
import io.pivotal.sensor.dao.UserRepository;
import io.pivotal.sensor.model.RFID;
import io.pivotal.sensor.model.RFIDEvent;
import io.pivotal.sensor.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RFIDServiceImpl implements RFIDSensorService {

	@Autowired
	private RFIDEventSensorRepository rfidEventRepository;
	
	@Autowired
	private RFIDSensorRepository rfidRepository;
	
	private UserRepository userRepository;

	@Autowired
	public void setRfidEventRepository(RFIDEventSensorRepository rfidEventRepository) {
		this.rfidEventRepository = rfidEventRepository;
	}

	@Autowired
	public void setRfidRepository(RFIDSensorRepository rfidRepository) {
		this.rfidRepository = rfidRepository;
	}

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public void saveRFIDEvent(RFIDEvent event) {
		rfidEventRepository.save(event);
	}

	@Override
	public RFID findByRFID(String rfid) {
		return rfidRepository.findByRfid(rfid);
	}
	

	@Override
	public Iterable<RFIDEvent> findAllRfidEventsByRfid(String rfid) {
		// TODO Auto-generated method stub
		return rfidEventRepository.findByRfidRfid(rfid);
	}

	@Override
	public Iterable<RFIDEvent> findAllRfidEventsByRfidBetween(String rfid,
			Date startTime, Date endTime) {
		// TODO Auto-generated method stub
		return rfidEventRepository.findByRfidRfidAndEventTimeBetween(rfid, startTime, endTime);
	}

	@Override
	public Iterable<RFID> findAllRfid() {
		// TODO Auto-generated method stub
		return rfidRepository.findAll();
	}

	@Override
	public RFID findRFIDByUserId(Long userId) {
		// TODO Auto-generated method stub
		User u = userRepository.findOne(userId);
		if (u != null){
			return u.getRfid();
		}
		else{
			return null;

		}
	}
	
}
