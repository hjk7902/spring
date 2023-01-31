package com.example.myapp.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class HelloController {
	
	@Autowired
	@Qualifier("helloService")
	IHelloService helloService;
		
	public void hello(String name) {
		System.out.println("HelloController : " + helloService.sayHello(name));
	}

	public void goodbye(String name) {
		System.out.println("HelloController : " + helloService.sayGoodbye(name));
	}
}