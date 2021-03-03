package com.algaworks.algamoney.api.algamoneyapi.service;

import java.util.Optional;

import com.algaworks.algamoney.api.algamoneyapi.model.Category;
import com.algaworks.algamoney.api.algamoneyapi.repository.CategoryRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	public Category updateCategory(Long id, Category category) {
		Category savedCategory = findCategoryById(id);
		BeanUtils.copyProperties(category, savedCategory, "id");
		categoryRepository.save(savedCategory);
		return savedCategory;
	}
	
	public Category findCategoryById(Long id) {
		Optional<Category> optionalCategory = categoryRepository.findById(id);
		
		if (!optionalCategory.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}

		return optionalCategory.get();
	}
}
