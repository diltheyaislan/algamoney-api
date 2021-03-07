package com.algaworks.algamoney.api.algamoneyapi.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.algaworks.algamoney.api.algamoneyapi.event.CreatedResourceEvent;
import com.algaworks.algamoney.api.algamoneyapi.model.Posting;
import com.algaworks.algamoney.api.algamoneyapi.repository.PostingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postings")
public class PostingResource {
	
	@Autowired
	private PostingRepository postingRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Posting> list() {
		return postingRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Posting> create(@Valid @RequestBody Posting posting, HttpServletResponse response) {
		Posting newPosting = postingRepository.save(posting);
		publisher.publishEvent(new CreatedResourceEvent(this, response, newPosting.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(newPosting);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Posting> findById(@PathVariable Long id) {
		return postingRepository
			.findById(id)
			.map(categoria -> ResponseEntity.ok(categoria))
			.orElse(ResponseEntity.notFound().build());
	}
}
