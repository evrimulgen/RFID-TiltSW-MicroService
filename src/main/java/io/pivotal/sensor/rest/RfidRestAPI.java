package io.pivotal.sensor.rest;

import io.pivotal.sensor.model.RFID;
import io.pivotal.sensor.model.RFIDEvent;
import io.pivotal.sensor.service.RFIDSensorService;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Sample requests:
 * http://localhost:8080/get-all-rfids
 * http://localhost:8080/get-rfid-events-by-rfid/0909200181
 * http://localhost:8080/get-rfid-by-user/1
 * http://localhost:8080/get-all-rfid-events-by-rfid/0909200181/between/2014-06-12T00:00:00.000Z/2015-11-12T00:00:00.000Z
 * 
 * @author belle
 *
 */
@RestController
public class RfidRestAPI {

	@Autowired
	private RFIDSensorService rfidSensorService;
	
	//http://localhost:8080/get-all-rfids
	@RequestMapping(value="/get-all-rfids-ms", method=RequestMethod.GET)
	public Iterable<RFID> getAllRFIDs() {	
		System.out.println("Within RFID Rest API");

		return rfidSensorService.findAllRfid();
	}
	
	//http://localhost:8080/get-rfid-events-by-rfid/0909200181
	@RequestMapping(value="/get-rfid-events-by-rfid-ms/{rfid}", method=RequestMethod.GET)
	public Iterable<RFIDEvent> getRFIDEventByRfid(@PathVariable String rfid) {
		return rfidSensorService.findAllRfidEventsByRfid(rfid);
	}
	
	//http://localhost:8080/get-rfid-by-user/3333
	@RequestMapping(value="/get-rfid-by-user-ms/{userId}", method=RequestMethod.GET)
	public RFID getRFIDByUserId(@PathVariable Long userId) {	
		return rfidSensorService.findRFIDByUserId(userId);
	}
	
	//Example http://localhost:8080/get-all-rfid-events-by-rfid/12/between/2014-06-12T00:00:00.000Z/2014-11-12T00:00:00.000Z
	@RequestMapping(value="/get-all-rfid-events-by-rfid-ms/{rfid}/between/{startDate}/{endDate}", method=RequestMethod.GET)
	public Iterable<RFIDEvent> getAllRFIDEventsByRfidBetween(@PathVariable String rfid, 
			@PathVariable  @DateTimeFormat(iso = ISO.DATE) Date startDate,@PathVariable  @DateTimeFormat(iso = ISO.DATE) Date endDate) {	
		return rfidSensorService.findAllRfidEventsByRfidBetween(rfid, startDate, endDate);
	}
	
	
}
