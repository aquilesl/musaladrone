package com.musala.alvaro.testdrones.service;

import java.util.List;
import com.musala.alvaro.testdrones.model.User;

public interface IUserService {

	List<User> getAllUsers();
	User getUserById(long id);
	User createUser(User user);
	User updateUser(long id, User user);
	void deleteUser(long id);
	User findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
}
