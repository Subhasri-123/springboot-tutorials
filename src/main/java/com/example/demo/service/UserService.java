package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.User;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	public User createNewUser(User user) throws UserAlreadyExistsException{
		User existingUser = userRepository.findByuserName(user.getUserName());
		if(existingUser != null) {
			throw new UserAlreadyExistsException("User alreay present");
		}
		return userRepository.save(user);
	}
	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("User Not Found");
		}
		return user;
	}
	public User updateUserById(Long id, User user) throws UserNotFoundException {
		Optional<User> optinalUser = userRepository.findById(id);
		if(!optinalUser.isPresent()) {
			throw new UserNotFoundException("User Not Found, provide the correct user id");
		}
		user.setId(id);
		return userRepository.save(user);
	}
	public void deleteUserById(Long id) throws UserNotFoundException {
		Optional<User> optinalUser = userRepository.findById(id);
		if(!optinalUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Not Found, provide the correct user id");
		}
		userRepository.deleteById(id);
	}
	public User getUserByUserName(String username) {
		return userRepository.findByuserName(username);
	}
}
