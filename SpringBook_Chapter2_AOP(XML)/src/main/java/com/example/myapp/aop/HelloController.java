package com.example.myapp.aop;

public class HelloController {
	
	IHelloService helloService;
	
	public void setHelloService(IHelloService helloService) {
		this.helloService = helloService;
	}

	public void hello(String name) {
		String result = helloService.sayHello(name);
		System.out.println(result);
	}
}