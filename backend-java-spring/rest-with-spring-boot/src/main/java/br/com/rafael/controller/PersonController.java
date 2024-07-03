package br.com.rafael.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.rafael.models.Person;
import br.com.rafael.services.PersonServices;
import br.com.rafael.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Enpoints for Managing People")
public class PersonController {

	@Autowired
	private PersonServices service;

	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
	@Operation(summary = "Finds all people", description = "", tags = { "People" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Person.class)))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
	})
	public ResponseEntity<Page<Person>> findAll(@RequestParam(value = "page", defaultValue = "12") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) throws Exception {
		Direction sortDirection = direction.equalsIgnoreCase("desc") ? Direction.DESC : Direction.ASC;

		Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "firstName"));
		return ResponseEntity.ok(service.finAll(pageable));
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

	@PatchMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML })
	public Person disablePerson(@PathVariable(value = "id") Long id) throws Exception {
		return service.disablePerson(id);
	}

	@DeleteMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML })
	public ResponseEntity<?> deletPerson(@PathVariable(value = "id") Long id) throws Exception {
		service.deletPerson(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/findPersonByName/{firstName}")
	public ResponseEntity<Page<Person>> findPersonByName(
		@PathVariable(value = "firstName") String firstName,
		@RequestParam(value = "page", defaultValue = "0") Integer page, 
		@RequestParam(value = "size", defaultValue = "10") Integer size,
		@RequestParam(value = "direction", defaultValue = "asc") String direction
	) {
		var sortDirection = direction.equalsIgnoreCase("desc") ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "firstName"));
		return ResponseEntity.ok(this.service.findByPersonName(firstName, pageable));
	}
}
