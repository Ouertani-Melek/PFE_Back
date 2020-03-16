package com.backend.guestnhouse.models;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
public class Role {
  @Id
  private String id;

  
  private String name;
  
  private int archived;
  
  @DBRef
  private Set<Permission> permissions = new HashSet<>();

  public Role() {

  }

  public Role(String name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

public Set<Permission> getPermissions() {
	return permissions;
}

public void setPermissions(Set<Permission> permissions) {
	this.permissions = permissions;
}

public int getArchived() {
	return archived;
}

public void setArchived(int archived) {
	this.archived = archived;
}
  
  
}
