package com.backend.guestnhouse.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
  @Id
  private String id;

  @NotBlank
  @Size(max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(max = 120)
  private String password;
  
  private String number;
	
	
  private String userImage;
	
  private Date created_date;

  @DBRef
  private Set<Role> roles = new HashSet<>();

  public User() {
  }

  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }
  
  

  public User(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
		@NotBlank @Size(max = 120) String password, String number, String userImage, Date created_date) {
	super();
	this.username = username;
	this.email = email;
	this.password = password;
	this.number = number;
	this.userImage = userImage;
	this.created_date = created_date;
}

public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

public String getNumber() {
	return number;
}

public void setNumber(String number) {
	this.number = number;
}

public String getUserImage() {
	return userImage;
}

public void setUserImage(String userImage) {
	this.userImage = userImage;
}

public Date getCreated_date() {
	return created_date;
}

public void setCreated_date(Date created_date) {
	this.created_date = created_date;
}
  
  
  
}
