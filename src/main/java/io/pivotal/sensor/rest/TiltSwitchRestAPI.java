package io.pivotal.sensor.rest;

import io.pivotal.sensor.model.TiltSwitch;
import io.pivotal.sensor.model.TiltSwitchEvent;
import io.pivotal.sensor.service.TiltSwitchSensorService;

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
 * http://localhost:8080/get-all-tilt-switches
 * http://localhost:8080/get-tilt-switches-by-tiltId/need-a-sensor-id
 * http://localhost:8080/get-tilt-events-by-tiltId/need-a-sensor-id
 * http://localhost:8080/get-all-tilt-events-by-tiltId/need-a-sensor-id/between/2014-06-12T00:00:00.000Z/2015-11-12T00:00:00.000Z
 * 
 * @author belle
 *
 */
@RestController
public class TiltSwitchRestAPI {

	@Autowired
	private TiltSwitchSensorService tiltSwitchSensorService;
	
	//http://localhost:8080/get-tilt-switches-by-tiltId/33
	@RequestMapping(value="/get-tilt-switches-by-tiltId-ms/{tiltSwitchId}", method=RequestMethod.GET)
	public TiltSwitch getTiltSwitchByTiltSwitchID(@PathVariable String tiltSwitchId) {
		return tiltSwitchSensorService.getTiltSwitchByTiltSwitchID(tiltSwitchId);
	}
	
	//http://localhost:8080/get-all-tilt-switches
	@RequestMapping(value="/get-all-tilt-switches-ms", method=RequestMethod.GET)
	public Iterable<TiltSwitch> getAllTiltSwitches() {	
		return tiltSwitchSensorService.getAllTiltSwitches();
	}
	
	//http://localhost:8080/get-tilt-events-by-tiltId/34
	@RequestMapping(value="/get-tilt-events-by-tiltId-ms/{tiltSwitchId}", method=RequestMethod.GET)
	public Iterable<TiltSwitchEvent> getAllTiltSwitchEventsByTiltSwitchID(@PathVariable String tiltSwitchId) {	
		return tiltSwitchSensorService.getAllTiltSwitchEventsByTiltSwitchID(tiltSwitchId);
	}
	
	//http://localhost:8080/get-all-tilt-events-by-tiltId/33/between/2014-06-12T00:00:00.000Z/2014-11-12T00:00:00.000Z
	@RequestMapping(value="/get-all-tilt-events-by-tiltId-ms/{tiltSwitchId}/between/{startDate}/{endDate}", method=RequestMethod.GET)
	public Iterable<TiltSwitchEvent> getAllTiltSwitchEventsByTiltSwitchIDBetween(@PathVariable String tiltSwitchId, 
			@PathVariable  @DateTimeFormat(iso = ISO.DATE) Date startDate, @PathVariable  @DateTimeFormat(iso = ISO.DATE) Date endDate) {	
		return tiltSwitchSensorService.getAllTiltSwitchEventsByTiltSwitchIDBetween(tiltSwitchId, startDate, endDate);
	}


}
