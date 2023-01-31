package com.musala.alvaro.testdrones.controller;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.musala.alvaro.testdrones.service.IUserService;
import com.musala.alvaro.testdrones.service.IRoleService;
import com.musala.alvaro.testdrones.request.LoginRequest;
import com.musala.alvaro.testdrones.request.SignupRequest;
import com.musala.alvaro.testdrones.response.JwtResponse;
import com.musala.alvaro.testdrones.response.MessageResponse;
import com.musala.alvaro.testdrones.configuration.security.JwtUtils;
import com.musala.alvaro.testdrones.configuration.security.UserDetailsImp;
import com.musala.alvaro.testdrones.model.Role;
import com.musala.alvaro.testdrones.model.User;
import com.musala.alvaro.testdrones.model.enums.RoleType;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

	  private final AuthenticationManager authenticationManager;
	  private final IUserService userService;
	  private final IRoleService roleService;
	  private final PasswordEncoder encoder;
	  private final JwtUtils jwtUtils;
	  
	  @Autowired
	  public AuthController(AuthenticationManager authenticationManager, IUserService userService,
			IRoleService roleService, PasswordEncoder encoder, JwtUtils jwtUtils) {
		super();
		this.authenticationManager = authenticationManager;
		this.userService = userService;
		this.roleService = roleService;
		this.encoder = encoder;
		this.jwtUtils = jwtUtils;
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
	    Authentication authentication = authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    String jwt = jwtUtils.generateJwtToken(authentication);
	    UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();    
	    List<String> roles = userDetails.getAuthorities().stream()
	        .map(item -> item.getAuthority())
	        .collect(Collectors.toList());

	    return ResponseEntity.ok(new JwtResponse(jwt, 
	                         userDetails.getId(), 
	                         userDetails.getUsername(), 
	                         userDetails.getEmail(), 
	                         roles));
	}

	  @PostMapping("/signup")
	  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		    if (userService.existsByUsername(signUpRequest.getUsername())) {
		      return ResponseEntity
		          .badRequest()
		          .body(new MessageResponse("Error: Username is already taken!"));
		    }
		
			if (userService.existsByEmail(signUpRequest.getEmail())) {
			  return ResponseEntity
			      .badRequest()
			      .body(new MessageResponse("Error: Email is already in use!"));
			}
		

			User user = new User(signUpRequest.getUsername(), 
			           signUpRequest.getEmail(),
			           encoder.encode(signUpRequest.getPassword()));
			
			Set<String> strRoles = signUpRequest.getRole();
			Set<Role> roles = new HashSet<>();
			
			if (strRoles == null || strRoles.isEmpty()) {
				Role modRole = roleService.findByName(RoleType.NO_ROLE);
				roles.add(modRole);

			} else {
				
			  strRoles.forEach(role -> {
				  Optional<Role> modRole = Optional.ofNullable(roleService.findByName(RoleType.valueOf(role)));
			      if(modRole.isEmpty()) {
			    	  roles.add(roleService.findByName(RoleType.NO_ROLE));
			      }else {
			    	  roles.add(modRole.get());
			      }
				    
			  });
			
			}
	
			user.setRoles(roles);
			userService.createUser(user);
			
			return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	  }
	
}
