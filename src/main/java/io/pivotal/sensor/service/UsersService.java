package io.pivotal.sensor.service;

import io.pivotal.sensor.model.User;

public interface UsersService {

	Iterable<User> findAllUsers();
}
