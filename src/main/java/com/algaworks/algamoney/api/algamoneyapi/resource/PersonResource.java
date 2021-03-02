package com.algaworks.algamoney.api.algamoneyapi.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.algaworks.algamoney.api.algamoneyapi.event.CreatedResourceEvent;
import com.algaworks.algamoney.api.algamoneyapi.model.Person;
import com.algaworks.algamoney.api.algamoneyapi.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/people")
public class PersonResource {
	
	@Autowired
	private PersonRepository PersonRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Person> list() {
		return PersonRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Person> create(
		@Valid
		@RequestBody 
		Person person,
		HttpServletResponse response) {
		Person newPerson = PersonRepository.save(person);	
		publisher.publishEvent(new CreatedResourceEvent(this, response, newPerson.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(newPerson);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Person> findById(@PathVariable Long id) {
		return PersonRepository
			.findById(id)
			.map(categoria -> ResponseEntity.ok(categoria))
			.orElse(ResponseEntity.notFound().build());
	}
}
