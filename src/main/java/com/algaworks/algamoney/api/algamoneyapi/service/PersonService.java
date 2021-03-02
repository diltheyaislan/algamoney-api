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
		Optional<Person> optionalPerson = personRepository.findById(id);
		
		if (!optionalPerson.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}

		Person savedPerson = optionalPerson.get();
		BeanUtils.copyProperties(person, savedPerson, "id");
		personRepository.save(savedPerson);
		return savedPerson;
	}
}
