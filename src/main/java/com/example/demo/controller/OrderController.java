package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class OrderController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderRepository orderRepository;
	
	//get All Orders For a user
	@GetMapping("/{userId}/orders")
	public List<Order>  getAllOrders(@PathVariable Long userId) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(userId);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("user not found");
		}
		return userOptional.get().getOrder();
	}
	
@PostMapping("/{userId}/orders")
public Order createOrder(@RequestBody Order order, @PathVariable Long userId) throws UserNotFoundException{
	Optional<User> userOptional = userRepository.findById(userId);
	if(!userOptional.isPresent()) {
		throw new UserNotFoundException("user not found");
	}
	User user = userOptional.get();
	order.setUser(user);
	return orderRepository.save(order);
	
	
	
	}
}
