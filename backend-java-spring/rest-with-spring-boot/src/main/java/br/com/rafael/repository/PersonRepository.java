package br.com.rafael.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rafael.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {}
