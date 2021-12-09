package com.example.springboot.controllers;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.config.JWTResponse;
import com.example.springboot.config.JWTTokenUtil;
import com.example.springboot.config.JwtRequest;
import com.example.springboot.dto.AccountDTO;
import com.example.springboot.dto.PasswordDTO;
import com.example.springboot.services.AccountService;
import com.example.springboot.services.MyUserDetailsService;

import javassist.NotFoundException;
import net.bytebuddy.utility.RandomString;

@CrossOrigin(origins = { "http://localhost:3000", "https://www.zappy-nihongo.com" })
@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTTokenUtil JwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private AccountService accountService;

	@PostMapping("/login")
	public ResponseEntity<JWTResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
		JWTResponse result = new JWTResponse();

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));

			final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

			final String token = JwtTokenUtil.generateToken(userDetails);
			result = new JWTResponse(token, authenticationRequest.getUsername(),
					userDetailsService.getRoleName(authenticationRequest.getUsername()));
			return new ResponseEntity<JWTResponse>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<JWTResponse>(result, HttpStatus.UNAUTHORIZED);
		}
	}

	// register
	@PostMapping("/register")
	public ResponseEntity<String> createAccountEntity(@RequestBody AccountDTO accountDTO) {
		return new ResponseEntity<String>(accountService.save(accountDTO), HttpStatus.OK);
	}

	// check if the token is expired
	@PostMapping("/ping")
	public ResponseEntity<Boolean> ping() {
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	// forgot password
	@PutMapping("/forgotPassword")
	public ResponseEntity<String> processForgotPassword(@RequestParam String email) {
		try {
			accountService.forgotPassword(email);
		} catch (NotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Không tìm thấy email", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Gửi mail bị lỗi", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Gửi mail thành công", HttpStatus.OK);
	}

	// check forgot password
	@GetMapping("/forgotPassword")
	public ResponseEntity<String> checkForgotPassword(@RequestParam String email, @RequestParam String token) {
		try {
			if (accountService.checkForgotPassword(email, token))
				return new ResponseEntity<String>("OK", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("NotOK", HttpStatus.OK);
	}

	// forgot password
	@PostMapping("/forgotPassword")
	public ResponseEntity<String> saveForgotPassword(@RequestParam String email,@RequestParam String token, @RequestBody PasswordDTO passwordDTO) {
		try {
			if (accountService.setNewPassword(email, token, passwordDTO))
				return new ResponseEntity<String>("Cập nhật mật khẩu mới thành công", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Cập nhật mật khẩu không thành công", HttpStatus.OK);
	}
}
