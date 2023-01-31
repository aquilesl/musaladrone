package com.musala.alvaro.testdrones.configuration.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.musala.alvaro.testdrones.model.User;
import com.musala.alvaro.testdrones.service.IUserService;

@Service
public class UserDetailsServiceImp implements UserDetailsService{

	  @Autowired
	  IUserService userService;

	  @Override
	  @Transactional
	  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    Optional<User> user = Optional.ofNullable(userService.findByUsername(username));
	    if(user.isEmpty())
	    	throw new UsernameNotFoundException("User Not Found with username: " + username);

	    return UserDetailsImp.build(user.get());
	  }
	
}
