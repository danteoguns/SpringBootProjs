package com.qa.Database.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.Database.Exception.ResourceNotFoundException;
import com.qa.Database.Model.*;
import com.qa.Database.Repository.*;

@RestController
public class PersonController {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private SpringBootRepository sbRepository;
	
	//Get a person
	@GetMapping("person/{personId}/orders")
	public Page<Order> getAllOrdersByPersonID(@PathVariable(value = "person_id") Long personId, Pageable pageable) {
		return orderRepository.findByPersonId(personId, pageable);
	}
	
	@PostMapping("/person/{personId}/orders")
	public Order createComment(@PathVariable(value = "person_id") Long personId, @Valid @RequestBody Order order) {
		return sbRepository.findById(personId).map(SpringBootDataModel -> {order.setPerson(SpringBootDataModel);
		return orderRepository.save(order);
		}).orElseThrow(() -> new ResourceNotFoundException("Person", "id", order));
	}
	
	@PutMapping("/person/{personId}/orders/{orderId}")
	public Order updateOrder(@PathVariable(value = "personId") Long personId, @PathVariable(value = "orderId") Long orderId, @Valid @RequestBody Order orderRequest){
		if(!SpringBootRepository.existsById(personId)) {
			throw new ResourceNotFoundException("Person", "id", orderRequest);
		}
		
		return orderRepository.findById(orderId).map(order -> {
			order.setTitle(orderRequest.getTitle());
			return orderRepository.save(order);
		}).orElseThrow(() -> new ResourceNotFoundException("OrderId", "id", orderRequest));
	}
}
