package com.backend.guestnhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.guestnhouse.models.Permission;
import com.backend.guestnhouse.models.Role;
import com.backend.guestnhouse.repository.PermissionRepository;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

	@Autowired
	private PermissionRepository permissionRepository;
	
	@GetMapping
	public List<Permission> getPermissions() {
		return permissionRepository.findAll();
	}
}
