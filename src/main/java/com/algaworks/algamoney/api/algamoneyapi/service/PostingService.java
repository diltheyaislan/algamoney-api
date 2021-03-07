package com.algaworks.algamoney.api.algamoneyapi.service;

import java.util.Optional;

import com.algaworks.algamoney.api.algamoneyapi.model.Person;
import com.algaworks.algamoney.api.algamoneyapi.model.Posting;
import com.algaworks.algamoney.api.algamoneyapi.repository.PersonRepository;
import com.algaworks.algamoney.api.algamoneyapi.repository.PostingRepository;
import com.algaworks.algamoney.api.algamoneyapi.service.exception.NonexistentOrInactivePersonException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostingService {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private PostingRepository postingRepository;

	public Posting save(Posting posting) {
		Optional<Person> person = personRepository.findById(posting.getPerson().getId()); 
 
		if (!person.isPresent() || person.get().isInactive()) {
			throw new NonexistentOrInactivePersonException();
		}

		return postingRepository.save(posting);
	}
	
}
