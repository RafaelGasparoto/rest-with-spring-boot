package br.com.rafael.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import br.com.rafael.controller.PersonController;
import br.com.rafael.exceptions.ResourceNotFoundException;
import br.com.rafael.models.Person;
import br.com.rafael.repository.PersonRepository;

@Service
public class PersonServices {

	@Autowired
	PersonRepository personRepository;

	public Person findById(Long id) {
		Person person = this.personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found"));
		try {
			person.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return person;
	}

	public Person create(Person person) {
		return this.personRepository.save(person);
	}

	public void deletPerson(Long id) {
		this.personRepository.deleteById(id);;
		return;
	}

	public List<Person> finAll() {
		return this.personRepository.findAll();
	}

}
