package com.example.myapp;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloMain {
    public static void main(String[] args) {
        AbstractApplicationContext context = new GenericXmlApplicationContext("application-config.xml");
        IHelloService helloService = context.getBean(IHelloService.class);
        System.out.println(helloService.sayHello("KilDong"));
        context.close();
    }
}
