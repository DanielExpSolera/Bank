package com.bank.AppBankBootcamp.User;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource {
	private UserDaoService service;

	public UserResource(UserDaoService service) {
		super();
		this.service = service;
	}
}
