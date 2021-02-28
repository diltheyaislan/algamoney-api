package com.algaworks.algamoney.api.algamoneyapi.repository;

import com.algaworks.algamoney.api.algamoneyapi.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
}
