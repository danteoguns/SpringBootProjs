package com.qa.dvdRental.Controller;

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

import com.qa.dvdRental.Exception.ResourceNotFoundException;
import com.qa.dvdRental.Model.*;
import com.qa.dvdRental.Repository.DvdRentalRepository;

@RestController
@RequestMapping("/api")
public class DvdRentalDataController {

	@Autowired
	DvdRentalRepository dvdRepository;
	
	//Create a DVD
	@PostMapping("/dvd")
	public DvdRentalDataModel createDvd(@Valid @RequestBody DvdRentalDataModel DRDM) {
		return dvdRepository.save(DRDM);
	}
	
	//Get a DVD
	@GetMapping("dvd/{id}")
	public DvdRentalDataModel getDvdByID(@PathVariable(value = "id") Long dvdID) {
		return dvdRepository.findById(dvdID).orElseThrow(()-> new ResourceNotFoundException("DvdRentalDataModel", "id", dvdID));
	}
	
	//Get all DVDs
	@GetMapping("/dvd")
	public List<DvdRentalDataModel> getAllDvds(){
		return dvdRepository.findAll();
	}
	
	//Update a DVD
	@PutMapping("/dvd/{id}")
	public DvdRentalDataModel updateDvd(@PathVariable(value = "id") Long dvdID, @Valid @RequestBody DvdRentalDataModel dvdDetails){
		DvdRentalDataModel DRDM = dvdRepository.findById(dvdID).orElseThrow(() -> new ResourceNotFoundException("Dvd", "id", dvdID));
		
		DRDM.setTitle(dvdDetails.getTitle());
		DRDM.setGenre(dvdDetails.getGenre());
		DRDM.setReleaseYear(dvdDetails.getReleaseYear());
		
		DvdRentalDataModel updateData = dvdRepository.save(DRDM);
		return updateData;
	}
	
	//Remove a DVD
	@DeleteMapping("/dvd/{id}")
	public ResponseEntity<?> deleteDvd(@PathVariable(value = "id") Long dvdID){
		DvdRentalDataModel DRDM = dvdRepository.findById(dvdID).orElseThrow(() -> new ResourceNotFoundException("Dvd", "id", dvdID));
		
		dvdRepository.delete(DRDM);
		return ResponseEntity.ok().build();
	}
	
}
