package com.example.myapp;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DBPasswordEncoderMain {

	public static void main(String[] args) {
		PasswordEncoder pwEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String encodedPw = pwEncoder.encode("Admin123");
		log.info(encodedPw);
	}

}
