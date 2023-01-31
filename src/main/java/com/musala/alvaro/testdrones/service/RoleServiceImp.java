package com.musala.alvaro.testdrones.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.musala.alvaro.testdrones.model.Role;
import com.musala.alvaro.testdrones.model.enums.RoleType;
import com.musala.alvaro.testdrones.repository.RoleRepository;

@Service
public class RoleServiceImp implements IRoleService{

	private RoleRepository roleRepository;
	
	
	public RoleServiceImp(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}

	@Override
	public Role getRoleById(long id) {
		return roleRepository.getReferenceById(id);
	}

	@Override
	public Role createRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Role updateRole(long id, Role role) {
		Role temp = roleRepository.getReferenceById(id);
		temp.setName(role.getName());
		return roleRepository.save(temp);
	}

	@Override
	public void deleteRole(long id) {
		roleRepository.deleteById(id);
	}

	@Override
	public Role findByName(RoleType name) {
		return roleRepository.findByName(null);
	}

}
