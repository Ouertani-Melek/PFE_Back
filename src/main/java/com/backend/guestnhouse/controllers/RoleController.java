package com.backend.guestnhouse.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.guestnhouse.models.Permission;
import com.backend.guestnhouse.models.Role;
import com.backend.guestnhouse.repository.PermissionRepository;
import com.backend.guestnhouse.services.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	

	/*
	 * à OPTIMISER SI POSSIBLE
	 */
	@PostMapping
	public ResponseEntity<?> addRole(@RequestBody Role role){
		role.setArchived(0);
		if(roleService.addRole(role)!=null) {
			return ResponseEntity.ok("added");
		}
		return ResponseEntity.ok("not added");
	}
	
	@GetMapping
	public ResponseEntity<?> getRoles(){
		return ResponseEntity.ok(roleService.getRoles());
	}
	
	@DeleteMapping("/{idRole}")
	public ResponseEntity<?> deleteRole(@PathVariable(value="idRole") String idRole){
		if(roleService.archiveRole(idRole)) {
			return ResponseEntity.ok("archived");
		}
		return ResponseEntity.ok("not archived");
	}
	
	/*
	 * Update Role
	 */
	@PutMapping("/{idRole}")
	public ResponseEntity<?> updateRole(@RequestBody Role role,@PathVariable(value="idRole") String idRole){
		if(roleService.updateRole(role, idRole)!=null) {
			return ResponseEntity.ok("updated");
		}
		return ResponseEntity.ok("not updated");
	}
}
