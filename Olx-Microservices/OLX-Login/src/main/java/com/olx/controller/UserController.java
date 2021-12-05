package com.olx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.AuthenticationRequest;
import com.olx.dto.User;
import com.olx.entity.BlacklistTokenDocument;
import com.olx.repo.BlacklistTokenRepo;
import com.olx.security.JwtUtil;
import com.olx.service.LoginService;

@RestController
@RequestMapping("/olx/user")
public class UserController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	LoginService loginService;

	@Autowired
	BlacklistTokenRepo tokenRepository;
	
	//logout service.
	@DeleteMapping(value="/logout")
	public ResponseEntity<String> logout(@RequestHeader("auth-token")String authToken){
		String token = authToken.substring(authToken.indexOf(' ') + 1);
		BlacklistTokenDocument tokenDocument = new BlacklistTokenDocument();
		tokenDocument.setToken(token);
		tokenRepository.save(tokenDocument);
		return new ResponseEntity<String>("Successfully Logged Out.", HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/authenticate", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(),
					authenticationRequest.getPassword()));
		}
		catch(BadCredentialsException ex) { // if login failed
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
			
		}
		//log in successful... now return auth-token
		String jwtToken = jwtUtil.generateTokenByUsername(authenticationRequest.getUsername());
		return new ResponseEntity<String>(jwtToken, HttpStatus.OK);
	}
	
	@PostMapping(value = "/", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		User userDto = loginService.createNewUser(user);
		return new ResponseEntity<User>(userDto, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<User> getUserById(@PathVariable int id) {
		User user = loginService.getUserById(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping(value="/validate/token", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Boolean> isValidateUser(@RequestHeader("Authorization")String authToken){
		//business logic. assignment
		//1. Extract 'Bearer' word from token
		String jwtToken = authToken.substring(7,authToken.length());
		//2. Validate the token using JwtUtil.validateToken(xx) method
		String clientUsername = jwtUtil.extractUsername(jwtToken);
		String databaseUsername = userDetailsService.loadUserByUsername(clientUsername).getUsername();
		boolean isValidToken= jwtUtil.validateToken(jwtToken,databaseUsername);
		//3. Return result true/false to the client.
		if(isValidToken)
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		else
			return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
	}
	
	
	@GetMapping(value = "/username", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> getUserName(@RequestParam("auth-token") String authToken) {
		String username = loginService.getUserName(authToken);
		return new ResponseEntity<String>(username, HttpStatus.OK);
	}
}
