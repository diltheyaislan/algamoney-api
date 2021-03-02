package com.algaworks.algamoney.api.algamoneyapi.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.algaworks.algamoney.api.algamoneyapi.model.Person;
import com.algaworks.algamoney.api.algamoneyapi.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/people")
public class PersonResource {
	
	@Autowired
	private PersonRepository PersonRepository;

	@GetMapping
	public List<Person> list() {
		return PersonRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Person> create(
		@Valid
		@RequestBody 
		Person Person) {
		Person newPerson = PersonRepository.save(Person);
		
		URI uri = ServletUriComponentsBuilder
			.fromCurrentRequestUri()
			.path("/{id}")
			.buildAndExpand(newPerson.getId())
			.toUri();

		return ResponseEntity.created(uri).body(newPerson);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Person> findById(@PathVariable Long id) {
		return PersonRepository
			.findById(id)
			.map(categoria -> ResponseEntity.ok(categoria))
			.orElse(ResponseEntity.notFound().build());
	}
}
