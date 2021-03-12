package com.algaworks.algamoney.api.algamoneyapi.service;

import java.util.Optional;

import com.algaworks.algamoney.api.algamoneyapi.model.Person;
import com.algaworks.algamoney.api.algamoneyapi.model.Posting;
import com.algaworks.algamoney.api.algamoneyapi.repository.PersonRepository;
import com.algaworks.algamoney.api.algamoneyapi.repository.PostingRepository;
import com.algaworks.algamoney.api.algamoneyapi.service.exception.NonexistentOrInactivePersonException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
	
	public Posting findPostingById(Long id) {
		Optional<Posting> optionalPosting = postingRepository.findById(id);
		
		if (!optionalPosting.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}

		return optionalPosting.get();
	}

	public Posting updatePosting(Long id, Posting posting) {
		Posting savedPosting = findPostingById(id);
		BeanUtils.copyProperties(posting, savedPosting, "id");
		postingRepository.save(savedPosting);
		return savedPosting;
	}
}
