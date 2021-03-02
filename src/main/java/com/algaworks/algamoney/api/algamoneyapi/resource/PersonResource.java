package com.algaworks.algamoney.api.algamoneyapi.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.algaworks.algamoney.api.algamoneyapi.event.CreatedResourceEvent;
import com.algaworks.algamoney.api.algamoneyapi.model.Person;
import com.algaworks.algamoney.api.algamoneyapi.repository.PersonRepository;
import com.algaworks.algamoney.api.algamoneyapi.service.PersonService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/people")
public class PersonResource {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private PersonService personService;

	@GetMapping
	public List<Person> list() {
		return personRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Person> create(@Valid @RequestBody Person person, HttpServletResponse response) {
		Person newPerson = personRepository.save(person);
		publisher.publishEvent(new CreatedResourceEvent(this, response, newPerson.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(newPerson);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Person> findById(@PathVariable Long id) {
		return personRepository.findById(id).map(categoria -> ResponseEntity.ok(categoria))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		personRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Person> update(@PathVariable Long id, @RequestBody Person person) {
		return ResponseEntity.ok(personService.updatePerson(id, person));
	}

	@PutMapping("/{id}/active")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateActiveProperty(@PathVariable Long id, @RequestBody Boolean active) {
		personService.updateActiveProperty(id, active);
	}
}
