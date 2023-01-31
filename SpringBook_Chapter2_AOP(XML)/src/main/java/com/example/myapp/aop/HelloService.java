package com.example.myapp.aop;

public class HelloService implements IHelloService {

	@Override
	public String sayHello(String name) {
//		HelloLog.log();		//공통코드를 호출하는 코드가 포함됨
		String message = "Hello~" + name;
		System.out.println("HelloService.sayHello 메서드 실행");
		return message;
	}

	@Override
	public String sayGoodbye(String name) {
		String message = "Goodbye~" + name;
		return message;
	}
}