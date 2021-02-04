package com.backend.guestnhouse.controllers;

import java.util.*;
import java.util.stream.Collectors;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.validation.Valid;

import com.backend.guestnhouse.security.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.backend.guestnhouse.models.ConfirmationToken;
import com.backend.guestnhouse.models.Role;
import com.backend.guestnhouse.models.User;
import com.backend.guestnhouse.payload.request.EmailConfig;
import com.backend.guestnhouse.payload.request.LoginRequest;
import com.backend.guestnhouse.payload.request.SignupRequest;
import com.backend.guestnhouse.payload.response.JwtResponse;
import com.backend.guestnhouse.payload.response.MessageResponse;
import com.backend.guestnhouse.repository.ConfirmationTokenRepository;
import com.backend.guestnhouse.repository.RoleRepository;
import com.backend.guestnhouse.repository.UserRepository;
import com.backend.guestnhouse.security.jwt.JwtUtils;
import com.backend.guestnhouse.security.services.UserDetailsImpl;
import com.backend.guestnhouse.services.EmailSenderService;

import static org.springframework.http.ResponseEntity.ok;

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
	JwtTokenProvider jwtTokenProvider;

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	EmailConfig emailConfig;
	
	@Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;

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
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody AuthBody data){
		try {
			String username = data.getEmail();
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
			User user = this.userRepository.findByEmail(username);
			if (user.getArchived()==1) {
				return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
			}
			if (!user.isEnabled()) {
				return new ResponseEntity(HttpStatus.FORBIDDEN);
			}
			String token = jwtTokenProvider.createToken(username, this.userRepository.findByEmail(username).getRoles());
			Map<Object, Object> model = new HashMap<>();
			model.put("email", username);
			model.put("token", token);
			model.put("_id", user.getId());
			model.put("firstName", user.getFirstName());
			model.put("lastName", user.getLastName());
			model.put("createdDate", user.getCreated_date());
			model.put("number",user.getNumber());
			model.put("roles", user.getRoles());
			model.put("archived", user.getArchived());
			model.put("owner", user.isOwner());
			model.put("enabled", user.isEnabled());
			model.put("password", user.getPassword());
			return ok(model);
		} catch (AuthenticationException e) {
			throw e;
		}
	}
	
	
	@GetMapping("/getuserById/{idUser}")
	public ResponseEntity<?>  getuserById(@PathVariable(value="idUser") String idUser) {
		User user =userRepository.findById(idUser).orElse(null);
		if (user!=null) {
			return ResponseEntity.ok(user);
		}
		return ResponseEntity.badRequest()
				.body(new MessageResponse("Error: User not Found"));
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
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		if (userRepository.existsByEmail(user.getEmail())) {
			return new ResponseEntity(HttpStatus.FOUND);
		}
		Date now = new Date();
		// Create new user's account


		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByName("ROLE_USER").orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(userRole);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setUsername(user.getEmail());
		user.setRoles(roles);
		userRepository.save(user);
		ConfirmationToken confirmationToken = new ConfirmationToken(user);

        confirmationTokenRepository.save(confirmationToken);
		// send email TO BE OPTIMISED
				try {
					SimpleMailMessage mailMessage = new SimpleMailMessage();
			        mailMessage.setTo(user.getEmail());
					mailMessage.setFrom("guestnnhouse@gmail.com");
			        mailMessage.setSubject("Complete Registration!");
			        mailMessage.setText("To confirm your account, please click here : "
			        +"http://localhost:8081/api/auth/confirm-account/"+confirmationToken.getConfirmationToken());

			        emailSenderService.sendEmail(mailMessage);
				}catch( Exception e ){
					// catch error
					System.out.println("error");
				}
		
		return ResponseEntity.ok(user);
	}
	
	
	@RequestMapping(value="/confirm-account/{token}", method= {RequestMethod.GET})
    public String confirmUserAccount(@PathVariable("token")String confirmationToken)
    {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            User user = userRepository.findByEmail(token.getUser().getEmail());
            user.setEnabled(true);
            userRepository.save(user);
			return htmlContent("Account activated, you can log in with your account ...");
        }
        else
        {
			return htmlContent("Confirmation failed");
        }
    }


	private String htmlContent(String content){

		return  "<html>\n" +
				"  <head>\n" +
				"   <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">\n" +
				"   <style>\n" +
				"      a,a:focus,a:hover {\n" +
				"        color: #fff;\n" +
				"      }\n" +
				"      .btn-secondary,\n" +
				"      .btn-secondary:hover,\n" +
				"      .btn-secondary:focus {\n" +
				"        color: #333;\n" +
				"        text-shadow: none; /* Prevent inheritance from `body` */\n" +
				"        background-color: #fff;\n" +
				"        border: .05rem solid #fff;\n" +
				"      }\n" +
				"      body {\n" +
				"        display: -ms-flexbox;\n" +
				"        display: flex;\n" +
				"        color: #fff;\n" +
				"        text-shadow: 0 .05rem .1rem rgba(0, 0, 0, .5);\n" +
				"        box-shadow: inset 0 0 5rem rgba(0, 0, 0, .5);\n" +
				"        background-image: url('https://i.ibb.co/Ydgm28N/emailvalide.jpg');\n" +
				"        background-size: 100% 100%;\n" +
				"      }\n" +
				"      .cover-container {\n" +
				"        max-width: 42em;\n" +
				"      }\n" +
				"      .masthead {\n" +
				"        margin-bottom: 2rem;\n" +
				"      }\n" +
				"      .masthead-brand {\n" +
				"        margin-bottom: 0;\n" +
				"      }\n" +
				"      .nav-masthead .nav-link {\n" +
				"        padding: .25rem 0;\n" +
				"        font-weight: 700;\n" +
				"        color: rgba(255, 255, 255, .5);\n" +
				"        background-color: transparent;\n" +
				"        border-bottom: .25rem solid transparent;\n" +
				"      }\n" +
				"      .nav-masthead .nav-link:hover,\n" +
				"      .nav-masthead .nav-link:focus {\n" +
				"        border-bottom-color: rgba(255, 255, 255, .25);\n" +
				"      }\n" +
				"      .nav-masthead .nav-link + .nav-link {\n" +
				"        margin-left: 1rem;\n" +
				"      }\n" +
				"      .nav-masthead .active {\n" +
				"        color: #fff;\n" +
				"        border-bottom-color: #fff;\n" +
				"      }\n" +
				"      @media (min-width: 48em) {\n" +
				"        .masthead-brand {\n" +
				"          float: left;\n" +
				"        }\n" +
				"        .nav-masthead {\n" +
				"          float: right;\n" +
				"        }\n" +
				"      }\n" +
				"      .cover {\n" +
				"        padding: 0 1.5rem;\n" +
				"      }\n" +
				"      .cover .btn-lg {\n" +
				"        padding: .75rem 1.25rem;\n" +
				"        font-weight: 700;\n" +
				"      }\n" +
				"      .mastfoot {\n" +
				"        color: rgba(255, 255, 255, .5);\n" +
				"      }\n" +
				"          </style>\n" +
				"  </head>\n" +
				"<body>\n" +
				"  <div class=\"cover-container d-flex w-100 h-100 p-3 mx-auto flex-column\">\n" +
				"    <header class=\"masthead mb-auto\">\n" +
				"      <div class=\"inner\">\n" +
				"        <img src=\"https://i.ibb.co/k5qMhLb/logo1.png\" class=\"img-fluid masthead-brand\" style=\" width:200px;height: 101px\"/>\n" +
				"  \n" +
				"      </div>\n" +
				"    </header>\n" +
				"  \n" +
				"    <main role=\"main\" class=\"inner cover\" style=\"margin-top : 150px\">\n" +
				"      <h1 class=\"cover-heading\">Validation de compte</h1>\n" +
				"      <p class=\"lead\">"+content+"</p>\n" +
				"      <p class=\"lead\" style=\"margin-top : 150px\">\n" +
				"          <a href=\"http://localhost:4200/login\" class=\"btn btn-lg btn-secondary\">Login</a>\n" +
				"      </p>\n" +
				"    </main>\n" +
				"  \n" +
				"    <footer class=\"mastfoot mt-auto\">\n" +
				"      <div class=\"inner\">\n" +
				"  \n" +
				"      </div>\n" +
				"    </footer>\n" +
				"  </div>\n" +
				"  </body>\n" +
				"</html>";
	}
	
	
	
}
