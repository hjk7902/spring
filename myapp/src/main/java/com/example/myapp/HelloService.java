package com.example.myapp;

public class HelloService implements IHelloService {
    public String sayHello(String name) {
        return "Hello~~" + name;
    }
}
