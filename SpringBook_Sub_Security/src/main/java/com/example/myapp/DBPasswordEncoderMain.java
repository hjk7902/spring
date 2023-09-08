package com.example.myapp;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class DBPasswordEncoderMain {

	public static void main(String[] args) {
		PasswordEncoder pwEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String encodedPw = pwEncoder.encode("mypassword");
		System.out.println(encodedPw);
	}

}
