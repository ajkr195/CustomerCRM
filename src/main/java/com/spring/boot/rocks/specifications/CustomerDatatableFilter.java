package com.spring.boot.rocks.specifications;

import javax.persistence.criteria.*;

import com.spring.boot.rocks.entities.Customer;

import java.util.ArrayList;

public class CustomerDatatableFilter implements org.springframework.data.jpa.domain.Specification<Customer> {
	
	private static final long serialVersionUID = 1L;
	
	String userQuery;

	public CustomerDatatableFilter(String queryString) {
		this.userQuery = queryString;
	}

	@Override
	public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		ArrayList<Predicate> predicates = new ArrayList<>();

		if (userQuery != null && userQuery != "") {
			predicates.add(criteriaBuilder.like(root.get("firstName"), '%' + userQuery + '%'));
			predicates.add(criteriaBuilder.like(root.get("lastName"), '%' + userQuery + '%'));
			predicates.add(criteriaBuilder.like(root.get("city"), '%' + userQuery + '%'));
			predicates.add(criteriaBuilder.like(root.get("emailAddress"), '%' + userQuery + '%'));
			predicates.add(criteriaBuilder.like(root.get("phoneNumber"), '%' + userQuery + '%'));
			predicates.add(criteriaBuilder.like(root.get("country"), '%' + userQuery + '%'));
		}

		return (!predicates.isEmpty() ? criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]))
				: null);
	}
}
