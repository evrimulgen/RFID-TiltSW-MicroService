package io.pivotal.sensor.service;

import java.util.Date;

import io.pivotal.sensor.dao.TiltSwitchEventSensorRepository;
import io.pivotal.sensor.dao.TiltSwitchSensorRepository;
import io.pivotal.sensor.model.TiltSwitch;
import io.pivotal.sensor.model.TiltSwitchEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TiltSwitchServiceImpl implements TiltSwitchSensorService {

	@Autowired
	private TiltSwitchEventSensorRepository eventRepository;
	@Autowired
	private TiltSwitchSensorRepository tiltSwitchSensorRepository;
	
	@Override
	public void saveTiltSwitchEvent(TiltSwitchEvent event) {
		eventRepository.save(event);
	}

	@Override
	public TiltSwitch getTiltSwitchByTiltSwitchID(String tiltSwitchId) {
		return tiltSwitchSensorRepository.findByTiltSwitchId(tiltSwitchId);
	}
	
	@Autowired
	public void setTiltSwitchEventRepository(TiltSwitchEventSensorRepository tiltSwitchEventRepository) {
		this.eventRepository = tiltSwitchEventRepository;
	}

	@Autowired
	public void setTiltSwitchSensorRepository(TiltSwitchSensorRepository tiltSwitchSensorRepository) {
		this.tiltSwitchSensorRepository = tiltSwitchSensorRepository;
	}

	@Override
	public Iterable<TiltSwitch> getAllTiltSwitches() {
		return tiltSwitchSensorRepository.findAll();

	}

	@Override
	public Iterable<TiltSwitchEvent> getAllTiltSwitchEventsByTiltSwitchID(
			String tiltSwitchId) {
		return eventRepository.findByTiltSwitchTiltSwitchId(tiltSwitchId);

	}

	@Override
	public Iterable<TiltSwitchEvent> getAllTiltSwitchEventsByTiltSwitchIDBetween(
			String tiltSwitchId, Date startDate, Date endDate) {
		return eventRepository.findByTiltSwitchTiltSwitchIdAndEventTimeBetween(tiltSwitchId, startDate, endDate);

	}

	
}
