package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.entity.User;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.exception.UsernameNotFoundException;
import com.example.demo.service.UserService;


@RestController
@Validated
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	@PostMapping("/user")
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder builder) {
		try {
			 userService.createNewUser(user);
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(builder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);
		} catch (UserAlreadyExistsException e) {
			throw new  ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
			
			
		}
	}
	@GetMapping("/user/{id}")
	public Optional<User> getUserById(@PathVariable(value = "id") @Min(1) Long id) {
		try {
			return userService.getUserById(id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	@PutMapping("/user/{id}")
	public User updateUserById(@PathVariable(value = "id") Long id, @RequestBody User user) {
		try {
			return userService.updateUserById(id, user);
		} catch (UserNotFoundException e) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	@DeleteMapping("/user/{id}")
	public void deleteUserById(@PathVariable(value = "id") Long id) {
		try {
			userService.deleteUserById(id);
		} catch (UserNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	@GetMapping("/user/byUserName/{username}")
	public User getUserByUserName(@PathVariable(value = "username") String username) throws UsernameNotFoundException {
		User user = userService.getUserByUserName(username);
		if(user == null) {
			throw new UsernameNotFoundException("Username" +username+ "is not found");
		
		}
		return user;
	}
}
