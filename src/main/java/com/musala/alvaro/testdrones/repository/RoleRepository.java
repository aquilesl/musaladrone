package com.musala.alvaro.testdrones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.musala.alvaro.testdrones.model.Role;
import com.musala.alvaro.testdrones.model.enums.RoleType;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByName(RoleType name);
	
}
