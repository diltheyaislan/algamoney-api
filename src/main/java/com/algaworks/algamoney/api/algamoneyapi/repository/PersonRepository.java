package com.algaworks.algamoney.api.algamoneyapi.repository;

import com.algaworks.algamoney.api.algamoneyapi.model.Person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
	
}
