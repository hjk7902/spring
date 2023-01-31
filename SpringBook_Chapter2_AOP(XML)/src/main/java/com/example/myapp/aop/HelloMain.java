package com.example.myapp.aop;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloMain {
	public static void main(String[] args) {
		AbstractApplicationContext context =
				new GenericXmlApplicationContext("application-config.xml");
		HelloController controller = context.getBean(HelloController.class);
		controller.hello("홍길동");
		context.close();
	}
}