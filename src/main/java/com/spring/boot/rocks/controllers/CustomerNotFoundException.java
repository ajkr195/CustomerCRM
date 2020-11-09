package com.spring.boot.rocks.controllers;

public class CustomerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException(String exception) {
		super(exception);
	}

}