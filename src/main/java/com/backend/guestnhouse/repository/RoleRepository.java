package com.backend.guestnhouse.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.backend.guestnhouse.models.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(String name);
  
  Boolean existsByName(String name);
  
    
  @Query("{archived : ?0}")
  List<Role> findAllRoles(int archived);

   Role findRoleByName(String role);


}
