package com.algaworks.algamoney.api.algamoneyapi.resource;

import java.util.List;

import com.algaworks.algamoney.api.algamoneyapi.model.Posting;
import com.algaworks.algamoney.api.algamoneyapi.repository.PostingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postings")
public class PostingResource {
	
	@Autowired
	private PostingRepository postingRepository;

	@GetMapping
	public List<Posting> list() {
		return postingRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Posting> findById(@PathVariable Long id) {
		return postingRepository
			.findById(id)
			.map(categoria -> ResponseEntity.ok(categoria))
			.orElse(ResponseEntity.notFound().build());
	}
}
