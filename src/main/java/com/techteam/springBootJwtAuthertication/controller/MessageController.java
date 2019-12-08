package com.techteam.springBootJwtAuthertication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techteam.springBootJwtAuthertication.modules.AuthenticationRequest;
import com.techteam.springBootJwtAuthertication.modules.AuthenticationResponse;
import com.techteam.springBootJwtAuthertication.util.JwtTokenUtil;

@RestController
public class MessageController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetaiService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@GetMapping("/hello")
	public String getMessage() {
		return "Hello world";
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<Object> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)throws Exception{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword()));
			
		}catch(BadCredentialsException ee) {
			throw new Exception("Incorrect username and password", ee);
		}
		final UserDetails userDetails = userDetaiService.loadUserByUsername(authenticationRequest.getUserName());
		
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

}
