package com.algaworks.algamoney.api.algamoneyapi.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.algaworks.algamoney.api.algamoneyapi.model.Category;
import com.algaworks.algamoney.api.algamoneyapi.repository.CategoryRepository;

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
@RequestMapping("/categories")
public class CategoryResource {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping
	public List<Category> list() {
		return categoryRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Category> create(
		@Valid
		@RequestBody 
		Category category) {
		Category newCategory = categoryRepository.save(category);
		
		URI uri = ServletUriComponentsBuilder
			.fromCurrentRequestUri()
			.path("/{id}")
			.buildAndExpand(newCategory.getId())
			.toUri();

		return ResponseEntity.created(uri).body(newCategory);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		return categoryRepository
			.findById(id)
			.map(categoria -> ResponseEntity.ok(categoria))
			.orElse(ResponseEntity.notFound().build());
	}
}
