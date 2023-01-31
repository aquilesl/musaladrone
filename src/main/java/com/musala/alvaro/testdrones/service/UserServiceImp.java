package com.musala.alvaro.testdrones.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.musala.alvaro.testdrones.model.User;
import com.musala.alvaro.testdrones.repository.UserRepository;

@Service
public class UserServiceImp implements IUserService{

	private UserRepository userRepo;
	
	@Autowired
	public UserServiceImp(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User getUserById(long id) {
		return userRepo.getReferenceById(id);
	}

	@Override
	public User createUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public User updateUser(long id, User user) {
		User old = userRepo.getReferenceById(id);
		old.setEmail(user.getEmail());
		old.setPassword(user.getPassword());
		old.setUsername(user.getUsername());
		old.setRoles(user.getRoles());
		return userRepo.save(old);
	}

	@Override
	public void deleteUser(long id) {
		userRepo.deleteById(id);
	}

	@Override
	public User findByUsername(String username) {
		return userRepo.findFirstByUsername(username);
	}

	@Override
	public Boolean existsByUsername(String username) {
		return userRepo.existsByUsername(username);
	}

	@Override
	public Boolean existsByEmail(String email) {
		return userRepo.existsByEmail(email);
	}

}
