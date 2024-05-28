package br.com.rafael.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rafael.models.Person;
import br.com.rafael.services.PersonServices;
import br.com.rafael.util.MediaType;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonServices service;

	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
	public List<Person> findAll() throws Exception {
		return service.finAll();
	}

	@PostMapping(consumes = {
			MediaType.APPLICATION_JSON,
			MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML }, produces = {
					MediaType.APPLICATION_JSON,
					MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML })
	public Person create(@RequestBody Person person) throws Exception {
		
		return service.create(person);
	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML })
	public Person findById(@PathVariable(value = "id") Long id) throws Exception {
		return service.findById(id);
	}

	@DeleteMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML })
	public ResponseEntity<?> deletPerson(@PathVariable(value = "id") Long id) throws Exception {
		service.deletPerson(id);
		return ResponseEntity.noContent().build();
	}
}
