package com.musala.alvaro.testdrones.configuration.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.musala.alvaro.testdrones.model.User;

public class UserDetailsImp implements UserDetails{

	private static final long serialVersionUID = 1L;

	  private Long id;

	  private String username;

	  private String email;

	  @JsonIgnore
	  private String password;
	  
	  private Boolean enabled;
	  
	  private Boolean accountNonExpired;
	  
	  private Boolean accountNonLocked;
	  
	  private Boolean credentialsNonExpired;

	  private Collection<? extends GrantedAuthority> authorities;

	  public UserDetailsImp(Long id, String username, String email, String password,
			  Boolean enabled, Boolean accountNonExpired, Boolean accountNonLocked, 
			  Boolean credentialsNonExpired, Collection<? extends GrantedAuthority> authorities) {
	    this.id = id;
	    this.username = username;
	    this.email = email;
	    this.password = password;
	    this.authorities = authorities;
	    this.accountNonExpired = accountNonExpired;
	    this.accountNonLocked = accountNonLocked;
	    this.credentialsNonExpired = credentialsNonExpired;
	    this.enabled = enabled;
	    
	  }

	  public static UserDetailsImp build(User user) {
	    List<GrantedAuthority> authorities = user.getRoles().stream()
	        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
	        .collect(Collectors.toList());

	    return new UserDetailsImp(
	        user.getId(), 
	        user.getUsername(), 
	        user.getEmail(),
	        user.getPassword(), 
	        user.getEnabled(),
	        user.getAccountNonExpired(),
	        user.getAccountNonLocked(),
	        user.getCredentialsNonExpired(),
	        authorities);
	  }

	  @Override
	  public Collection<? extends GrantedAuthority> getAuthorities() {
	    return authorities;
	  }

	  public Long getId() {
	    return id;
	  }

	  public String getEmail() {
	    return email;
	  }

	  @Override
	  public String getPassword() {
	    return password;
	  }

	  @Override
	  public String getUsername() {
	    return username;
	  }

	  @Override
	  public boolean isAccountNonExpired() {
	    return accountNonExpired;
	  }

	  @Override
	  public boolean isAccountNonLocked() {
	    return accountNonLocked;
	  }

	  @Override
	  public boolean isCredentialsNonExpired() {
	    return credentialsNonExpired;
	  }

	  @Override
	  public boolean isEnabled() {
	    return enabled;
	  }
	  
	  public void setEnabled(Boolean enabled) {
		  this.enabled = enabled;
	  }
	  
	  public void setAccountNonExpired(Boolean accountNonExpired) {
		  this.accountNonExpired = accountNonExpired;
	  }
	  
	  public void setAccountNonLocked(Boolean accountNonLocked) {
		  this.accountNonLocked = accountNonLocked;
	  }
	  
	  public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		  this.credentialsNonExpired = credentialsNonExpired;
	  }
	  
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDetailsImp other = (UserDetailsImp) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
}
