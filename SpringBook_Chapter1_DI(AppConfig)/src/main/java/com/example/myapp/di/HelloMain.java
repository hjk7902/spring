package com.example.myapp.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class HelloMain {
	public static void main(String[] args) {
		AbstractApplicationContext context =
				new AnnotationConfigApplicationContext(AppConfig.class);
		HelloController controller = context.getBean("helloController", HelloController.class);
		controller.hello("JinKyoung");
		context.close();
	}
}
