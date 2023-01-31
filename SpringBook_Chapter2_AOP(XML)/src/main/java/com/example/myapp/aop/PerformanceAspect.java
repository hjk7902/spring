package com.example.myapp.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

public class PerformanceAspect {

	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable{

		Signature s= joinPoint.getSignature();
		String methodName = s.getName();
		System.out.println("[Log]Before: " + methodName + " time check start");

		long startTime = System.nanoTime();        

		Object result = null;
		try{
			result = joinPoint.proceed();
		}catch(Exception e){
			System.out.println("[Log]Exception: " + methodName);
		}finally {
			System.out.println("[Log]Finally: " + methodName);
		}

		long endTime = System.nanoTime();
		System.out.println("[Log]After: " + methodName + " time check end");
		System.out.println("[Log]" + methodName + ": " + (endTime-startTime) + "ns");
		return result;
	}   
}