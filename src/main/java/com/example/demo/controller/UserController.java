package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.User;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	@PostMapping("/user")
	public User createUser(@RequestBody User user) {
		try {
			return userService.createNewUser(user);
		} catch (UserAlreadyExistsException e) {
			throw new  ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
			
			
		}
	}
	@GetMapping("/user/{id}")
	public Optional<User> getUserById(@PathVariable(value = "id") Long id) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@GetMapping("/user/byUserName/{username}")
	public User getUserByUserName(@PathVariable(value = "username") String username) {
		return userService.getUserByUserName(username);
	}
}
