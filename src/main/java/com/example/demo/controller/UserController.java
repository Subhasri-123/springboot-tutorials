package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
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
		return userService.createNewUser(user);
	}
	@GetMapping("/user/{id}")
	public Optional<User> getUserById(@PathVariable(value = "id") Long id) {
		return userService.getUserById(id);
	}
	@PutMapping("/user/{id}")
	public User updateUserById(@PathVariable(value = "id") Long id, @RequestBody User user) {
		return userService.updateUserById(id, user);
	}
	@DeleteMapping("/user/{id}")
	public void deleteUserById(@PathVariable(value = "id") Long id) {
		userService.deleteUserById(id);
	}
	@GetMapping("/user/byUserName/{username}")
	public User getUserByUserName(@PathVariable(value = "username") String username) {
		return userService.getUserByUserName(username);
	}
}
