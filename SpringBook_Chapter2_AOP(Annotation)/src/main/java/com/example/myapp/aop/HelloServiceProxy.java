package com.example.myapp.aop;

public class HelloServiceProxy extends HelloService {

	@Override
	public String sayHello(String name) {
		HelloLog.log();			//공통코드를 실행합니다.
		String result = super.sayHello(name); 	//핵심코드를 실행합니다.
		return result;
	}
}