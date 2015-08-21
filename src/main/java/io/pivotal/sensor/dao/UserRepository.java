package io.pivotal.sensor.dao;

import io.pivotal.sensor.model.User;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {


	//User findByUseRfidRfid(String rfid);
	
	
}
