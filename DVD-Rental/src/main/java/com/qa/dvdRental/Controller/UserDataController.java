package com.qa.dvdRental.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.Database.Model.Order;
import com.qa.dvdRental.Exception.ResourceNotFoundException;
import com.qa.dvdRental.Model.*;
import com.qa.dvdRental.Repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserDataController {

	@Autowired
	UserRepository userRepository;
	
	//Create a user
	@PostMapping("/user")
	public UserDataModel createUser(@Valid @RequestBody UserDataModel UDM) {
		return userRepository.save(UDM);
	}
	
	//Get a user
	@GetMapping("user/{id}")
	public UserDataModel getUserBy (@PathVariable(value = "id") Long userId) {
		return userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("UserDataModel", "id", userId));
	}
	
	//Get all users
	@GetMapping("/user")
	public List<UserDataModel> getAllUsers(){
		return userRepository.findAll();
	}
	
	//Update a user
	@PutMapping("/user/{id}")
	public UserDataModel updateUser(@PathVariable(value = "id") Long userId, @Valid @RequestBody UserDataModel userDetails){
		UserDataModel UDM = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		
		UDM.setName(userDetails.getName());
		UDM.setAge(userDetails.getAge());
		UDM.setPostcode(userDetails.getPostcode());
		
		UserDataModel updateData = userRepository.save(UDM);
		return updateData;
	}
	
	//Remove a user
	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId){
		UserDataModel UDM = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		
		userRepository.delete(UDM);
		return ResponseEntity.ok().build();
	}
	
	//Person to DVD relationships
	//Get all DVDs
		@GetMapping("user/{userId}/orders")
		public Page<UserDataModel> getAllDvdsByUserId(@PathVariable(value = "user_id") Long userId, Pageable pageable) {
			return UserRepository.findByUserId(userId, pageable);
		}
	
}
