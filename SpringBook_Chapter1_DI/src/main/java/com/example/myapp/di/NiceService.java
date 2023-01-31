package com.example.myapp.di;

import org.springframework.stereotype.Service;

@Service
public class NiceService implements IHelloService {

	@Override
	public String sayHello(String name) {
		System.out.println("NiceService.sayHello() 메서드 실행");
		String message = "Hello~~~" + name;
		return message;
	}
}