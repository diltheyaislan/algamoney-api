package com.algaworks.algamoney.api.algamoneyapi.service;

import java.util.Optional;

import com.algaworks.algamoney.api.algamoneyapi.model.Person;
import com.algaworks.algamoney.api.algamoneyapi.repository.PersonRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;

	public Person updatePerson(Long id, Person person) {
		Person savedPerson = findPersonById(id);
		BeanUtils.copyProperties(person, savedPerson, "id");
		personRepository.save(savedPerson);
		return savedPerson;
	}

	public void updateActiveProperty(Long id, Boolean active) {
		Person person = findPersonById(id);
		person.setActive(active);
		personRepository.save(person);
	}

	public Person findPersonById(Long id) {
		Optional<Person> optionalPerson = personRepository.findById(id);
		
		if (!optionalPerson.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}

		return optionalPerson.get();
	}
}
