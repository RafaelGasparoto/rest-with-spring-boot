package br.com.rafael.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rafael.exceptions.ResourceNotFoundException;
import br.com.rafael.models.Person;
import br.com.rafael.repository.PersonRepository;

@Service
public class PersonServices {
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository personRepository;
	
	public Person findById(Long id) {
		logger.info("Finding one person");
		
		return this.personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found"));
	}
	
	public Person create(Person person) {
		return this.personRepository.save(person);
	}

	public List<Person> finAll() {
		return this.personRepository.findAll();
	}
	
}
