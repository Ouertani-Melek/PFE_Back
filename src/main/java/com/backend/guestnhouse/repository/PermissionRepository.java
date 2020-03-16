package com.backend.guestnhouse.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.backend.guestnhouse.models.Permission;

public interface PermissionRepository extends MongoRepository<Permission, String> {

	Optional<Permission> findByName(String name);
}
