package com.backend.guestnhouse.controllers;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.validation.Valid;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.guestnhouse.models.Role;
import com.backend.guestnhouse.models.User;
import com.backend.guestnhouse.payload.request.LoginRequest;
import com.backend.guestnhouse.payload.request.SignupRequest;
import com.backend.guestnhouse.payload.response.JwtResponse;
import com.backend.guestnhouse.payload.response.MessageResponse;
import com.backend.guestnhouse.repository.RoleRepository;
import com.backend.guestnhouse.repository.UserRepository;
import com.backend.guestnhouse.security.jwt.JwtUtils;
import com.backend.guestnhouse.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	/**
	 * 
	 * @params (login , password)
	 * @return Token
	 * 
	 */
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt));
	}

	/**
	 * 
	 * @params (User)
	 * Roles is an array and database must contain (ROLE_USER,ROLE_ADMIN,ROLE_HOST) in table roles
	 * permissions is an array and table permissions must contain data in database
	 * @return ResponseEntity
	 * 
	 */
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		Date now = new Date();
		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
				 signUpRequest.getEmail(),
				 encoder.encode(signUpRequest.getPassword()),
				 signUpRequest.getNumber(),
				 signUpRequest.getUserImage(),
				 now);

		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName("ROLE_USER")
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "ROLE_ADMIN":
					Role adminRole = roleRepository.findByName("ROLE_ADMIN")
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "ROLE_HOST":
					Role hostRole = roleRepository.findByName("ROLE_HOST")
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(hostRole);
					break;
				default:
					Role userRole = roleRepository.findByName(role)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	
	@PostMapping("/uploadUserImage")
	private String handleuserImageFileUpload(@RequestParam("userImage") MultipartFile userImage) {
		try {
			String fileName = System.getProperty("user.dir");
			Path path = Paths.get(fileName + "/uploads");

			if (!Files.exists(path)) {
				Files.createDirectory(path);
				File fileToSave = new File(
				path + "/" + userImage.getOriginalFilename());
				userImage.transferTo(fileToSave);
			} else {

				File fileToSave = new File(
				path + "/" + userImage.getOriginalFilename());
				userImage.transferTo(fileToSave);
			}

		} catch (IOException ioe) {
			// if something went bad, we need to inform client about it
			return "error";
		}
		// everything was OK, return HTTP OK status (200) to the client
		return userImage.getOriginalFilename();
	}
}
