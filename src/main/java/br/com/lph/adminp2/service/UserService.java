package br.com.lph.adminp2.service;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.lph.adminp2.security.UserSS;

public class UserService {
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}
