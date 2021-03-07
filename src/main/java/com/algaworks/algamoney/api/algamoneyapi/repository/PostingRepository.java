package com.algaworks.algamoney.api.algamoneyapi.repository;

import com.algaworks.algamoney.api.algamoneyapi.model.Posting;
import com.algaworks.algamoney.api.algamoneyapi.repository.posting.PostingRepositoryQuery;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostingRepository extends JpaRepository<Posting, Long>, PostingRepositoryQuery {
	
}
