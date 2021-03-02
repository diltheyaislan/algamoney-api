package com.algaworks.algamoney.api.algamoneyapi.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AlgamoneyExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
				String message = messageSource.getMessage("message.invalid", null, LocaleContextHolder.getLocale());
				String details = ex.getCause().toString();
				List<Error> errors = Arrays.asList(new Error(message, details));
				return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
		HttpHeaders headers, HttpStatus status, WebRequest request) {
			List<Error> errors = createErrorsList(ex.getBindingResult());
			return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, 
		WebRequest request) {
			String message = messageSource.getMessage("message.resourceNotFound", null, LocaleContextHolder.getLocale());
			String details = ex.toString();
			List<Error> errors = Arrays.asList(new Error(message, details));
			return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	private List<Error> createErrorsList(BindingResult bindingResult){
		List<Error> errors = new ArrayList<>();

		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String details = fieldError.toString();
			errors.add(new Error(message, details));
		}

		return errors;
	}

	private static class Error {

		private String message;
		private String detailS;

		public Error(String message, String detailS) {
			this.message = message;
			this.detailS = detailS;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getDetailS() {
			return detailS;
		}

		public void setDetailS(String detailS) {
			this.detailS = detailS;
		}
	}
}
