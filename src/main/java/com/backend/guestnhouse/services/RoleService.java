package com.backend.guestnhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.guestnhouse.models.Permission;
import com.backend.guestnhouse.models.Role;
import com.backend.guestnhouse.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	
	public Role addRole(Role role) {
		if(!roleRepository.existsByName(role.getName())) {
			roleRepository.save(role);
			return role;
		}
		return null;
	}
	
	public List<Role> getRoles() {
		return roleRepository.findAllRoles(0);
	}
	
	public Boolean archiveRole(String idRole) {
		Role role=roleRepository.findById(idRole).orElse(null);
		if(!role.getName().equals("ROLE_HOST") && !role.getName().equals("ROLE_USER") && !role.getName().equals("ROLE_ADMIN")) {
			role.setArchived(1);
			roleRepository.save(role);
			return true;
		}
		return false;
	}
	
	
	public Role updateRole(Role role,String idRole) {
		Role updatedRole=roleRepository.findById(idRole).orElse(null);
		if(!updatedRole.getName().equals("ROLE_HOST") && !updatedRole.getName().equals("ROLE_USER") && !updatedRole.getName().equals("ROLE_ADMIN")) {
		updatedRole.setName(role.getName());
		updatedRole.setPermissions(role.getPermissions());
		roleRepository.save(updatedRole);
		return updatedRole;
		}
		return null;
	}
}
