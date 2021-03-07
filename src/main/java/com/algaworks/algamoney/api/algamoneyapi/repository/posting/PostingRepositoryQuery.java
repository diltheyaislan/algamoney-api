package com.algaworks.algamoney.api.algamoneyapi.repository.posting;

import java.util.List;

import com.algaworks.algamoney.api.algamoneyapi.model.Posting;
import com.algaworks.algamoney.api.algamoneyapi.repository.filter.PostingFilter;

public interface PostingRepositoryQuery {
	
	public List<Posting> filter(PostingFilter filter);
}
