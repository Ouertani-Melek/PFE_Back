package com.backend.guestnhouse.security.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.backend.guestnhouse.models.Permission;
import com.backend.guestnhouse.models.Role;
import com.backend.guestnhouse.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private String id;

	private String username;

	private String email;

	@JsonIgnore
	private String password;
	
	private Set<String> permissions;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(String id, String username, String email, 
			Collection<? extends GrantedAuthority> authorities, String password,Set<String> permissions) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password=password;
		this.authorities = authorities;
		this.permissions=permissions;
	}

	public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities= new ArrayList<>();
		List<String> permissions = new ArrayList<>();
	    for (Role role: user.getRoles()) {
	    	authorities.add(new SimpleGrantedAuthority(role.getName()));
	        for(Permission permission : role.getPermissions()) {
	        	permissions.add(permission.getName());
	        }
	    }
	    Set<String> rolesPermissions = new LinkedHashSet<String>(permissions);

		return new UserDetailsImpl(
				user.getId(), 
				user.getUsername(), 
				user.getEmail(),
				authorities,
				user.getPassword(),
				rolesPermissions);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	

	public Set<String> getPermissions() {
		return permissions;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
}
