package com.algaworks.algamoney.api.algamoneyapi.repository.posting;

import java.util.List;

import com.algaworks.algamoney.api.algamoneyapi.model.Posting;
import com.algaworks.algamoney.api.algamoneyapi.repository.filter.PostingFilter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostingRepositoryQuery {
	
	public Page<Posting> filter(PostingFilter filter, Pageable pageable);
}
