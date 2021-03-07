package com.algaworks.algamoney.api.algamoneyapi.repository.posting;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.algaworks.algamoney.api.algamoneyapi.model.Posting;
import com.algaworks.algamoney.api.algamoneyapi.repository.filter.PostingFilter;

import org.apache.commons.lang3.StringUtils;

public class PostingRepositoryImpl implements PostingRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Posting> filter(PostingFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Posting> criteria = builder.createQuery(Posting.class);

		Root<Posting> root = criteria.from(Posting.class);
		Predicate[] predicates = createRestrictions(filter, builder, root);
		criteria.where(predicates);

		TypedQuery<Posting> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] createRestrictions(PostingFilter filter, CriteriaBuilder builder, Root<Posting> root) {

		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(filter.getDescription())) {
			predicates.add(builder.like(
				builder.lower(root.get("description")),
				"%" + filter.getDescription().toLowerCase() + "%"));
		}
		
		if (filter.getDueDateFrom() != null) {
			predicates.add(
				builder.greaterThanOrEqualTo(root.get("dueDate"), filter.getDueDateFrom())
			);
		} 
		
		if (filter.getDueDateTo() != null) {
			predicates.add(
				builder.lessThanOrEqualTo(root.get("dueDate"), filter.getDueDateTo())
			);
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}
}
