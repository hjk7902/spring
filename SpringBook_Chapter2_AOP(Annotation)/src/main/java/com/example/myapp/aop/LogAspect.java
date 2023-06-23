package com.example.myapp.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {

	@Pointcut(value="execution(* com.example..*.sayHello(..))")
	private void helloPointcut() {}

	@Pointcut(value="execution(* com.example..*.sayGoodbye(..))")
	private void goodbyePointcut() {}

	@Before("helloPointcut()")
	public void beforeLog(JoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		System.out.println("[Log: Before] 메서드 이름 : " + signature.getName());
	}

	@After("helloPointcut()")
	public void afterLog(JoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		System.out.println("[Log: After] 메서드 이름 : " + signature.getName());
	}

	@AfterReturning(pointcut="helloPointcut()", returning="message")
	public Object afterReturningLog(JoinPoint joinPoint, String message) {
		Signature signature = joinPoint.getSignature();
		System.out.println("[Log: AfterReturning] 메서드 이름 : " + signature.getName());
		System.out.println("[Log: AfterReturning] 메서드 리턴 값 : " + message);
		return message;
	}

	@AfterThrowing(pointcut="goodbyePointcut()", throwing="exception")
	public void afterThrowingLog(JoinPoint joinPoint, Exception exception) {
		Signature signature = joinPoint.getSignature();
		System.out.println("[Log: AfterThrowing] 메서드 이름 : " + signature.getName());
		System.out.println("[Log: AfterThrowing] 예외 발생 : " + exception.getMessage());
	}

	@Around("execution(* com.example..HelloService.*(..))")
	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable{
		Signature s= joinPoint.getSignature();
		String methodName = s.getName();
		System.out.println("[Log: Around]Before: " + methodName + " time check start");

		long startTime = System.nanoTime();        

		Object result = null;
		try{
			result = joinPoint.proceed();
		}catch(Exception e){
			System.out.println("[Log: Around]Exception: " + methodName);
		}finally {
			System.out.println("[Log: Around]Finally: " + methodName);
		}

		long endTime = System.nanoTime();
		System.out.println("[Log: Around]After: " + methodName + " time check end");
		System.out.println("[Log: Around]" + methodName + " Processing time is " + (endTime - startTime) + "ns");
		return result;
	}   
}