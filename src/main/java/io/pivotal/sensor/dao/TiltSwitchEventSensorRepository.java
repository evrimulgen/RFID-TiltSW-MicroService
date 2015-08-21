package io.pivotal.sensor.dao;

import java.util.Date;

import io.pivotal.sensor.model.TiltSwitchEvent;

import org.springframework.data.repository.CrudRepository;


public interface TiltSwitchEventSensorRepository extends CrudRepository<TiltSwitchEvent, String> {

	Iterable<TiltSwitchEvent> findByTiltSwitchTiltSwitchId(String tiltSwitchId);
	
	Iterable<TiltSwitchEvent> findByTiltSwitchTiltSwitchIdAndEventTimeBetween(String tiltSwitchId, Date startDate, Date endDate);
	
}
