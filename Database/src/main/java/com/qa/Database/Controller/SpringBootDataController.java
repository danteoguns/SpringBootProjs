package com.qa.Database.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.Database.Repository.SpringBootRepository;
import com.qa.Database.Exception.ResourceNotFoundException;
import com.qa.Database.Model.*;

@RestController
@RequestMapping("/api")
public class SpringBootDataController {

	@Autowired
	SpringBootRepository repository;
	
	//Create a person
	@PostMapping("/person")
	public SpringBootDataModel createPerson(@Valid @RequestBody SpringBootDataModel SBDM) {
		return repository.save(SBDM);
	}
	
	//Get a person
	@GetMapping("person/{id}")
	public SpringBootDataModel getPersonByID(@PathVariable(value = "id") Long personID) {
		return repository.findById(personID).orElseThrow(()-> new ResourceNotFoundException("SpringBootDataModel", "id", personID));
	}
	
	//Get all people
	@GetMapping("/person")
	public List<SpringBootDataModel> getAllPeople(){
		return repository.findAll();
	}
	
	//Update a person
	@PutMapping("/person/{id}")
	public SpringBootDataModel updatePerson(@PathVariable(value = "id") Long personID, @Valid @RequestBody SpringBootDataModel personDetails){
		SpringBootDataModel SBDM = repository.findById(personID).orElseThrow(() -> new ResourceNotFoundException("Person", "id", personID));
		
		SBDM.setName(personDetails.getName());
		SBDM.setAddress(personDetails.getAddress());
		SBDM.setAge(personDetails.getAge());
		
		SpringBootDataModel updateData = repository.save(SBDM);
		return updateData;
	}
	
	//Remove a person
	@DeleteMapping("/person/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable(value = "id") Long personID){
		SpringBootDataModel SBDM = repository.findById(personID).orElseThrow(() -> new ResourceNotFoundException("Person", "id", personID));
		
		repository.delete(SBDM);
		return ResponseEntity.ok().build();
	}
	
}
