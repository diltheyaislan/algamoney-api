package com.algaworks.algamoney.api.algamoneyapi.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.algaworks.algamoney.api.algamoneyapi.event.CreatedResourceEvent;
import com.algaworks.algamoney.api.algamoneyapi.exceptionhandler.AlgamoneyExceptionHandler;
import com.algaworks.algamoney.api.algamoneyapi.model.Posting;
import com.algaworks.algamoney.api.algamoneyapi.repository.PostingRepository;
import com.algaworks.algamoney.api.algamoneyapi.repository.filter.PostingFilter;
import com.algaworks.algamoney.api.algamoneyapi.service.PostingService;
import com.algaworks.algamoney.api.algamoneyapi.service.exception.NonexistentOrInactivePersonException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/postings")
public class PostingResource {
	
	@Autowired
	private PostingRepository postingRepository;

	@Autowired
	private PostingService postingService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private MessageSource messageSource;

	@GetMapping
	public List<Posting> search(PostingFilter filter) {
		return postingRepository.filter(filter);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Posting> create(@Valid @RequestBody Posting posting, HttpServletResponse response) {
		Posting newPosting = postingService.save(posting);
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

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		postingRepository.deleteById(id);
	}

	@ExceptionHandler({ NonexistentOrInactivePersonException.class })
	public ResponseEntity<Object> handleNonexistentOrInactivePersonException(NonexistentOrInactivePersonException ex, 
		WebRequest request) {
			String message = messageSource.getMessage("message.person.noexistentOrInactive", null, LocaleContextHolder.getLocale());
			String details = ex.toString();
			List<AlgamoneyExceptionHandler.Error> errors = Arrays.asList(new AlgamoneyExceptionHandler.Error(message, details));
			return ResponseEntity.badRequest().body(errors);
	}
}
