package com.example.myapp.di;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloMain {
	public static void main(String[] args) {
//		IHelloService helloService = new HelloService();
//		HelloController controller = new HelloController(helloService);
//		HelloController controller = new HelloController();
//		controller.setHelloService(helloService);
//		controller.hello("홍길동");
		
		AbstractApplicationContext context = new GenericXmlApplicationContext("application-config.xml");
		HelloController controller = context.getBean("helloController", HelloController.class);
		controller.hello("JinKyoung");
		context.close();
	}
}