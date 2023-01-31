package com.musala.alvaro.testdrones.service;

import java.util.List;
import com.musala.alvaro.testdrones.model.Role;
import com.musala.alvaro.testdrones.model.enums.RoleType;

public interface IRoleService {

	List<Role> getAllRoles();
	Role getRoleById(long id);
	Role createRole(Role role);
	Role updateRole(long id, Role role);
	void deleteRole(long id);
	Role findByName(RoleType name);
	
}
