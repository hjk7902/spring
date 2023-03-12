package com.example.myapp;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.example.myapp.service.ISampleService;

public class SampleMain {

	public static void main(String[] args) {
        AbstractApplicationContext context = new GenericXmlApplicationContext("application-config.xml");
        ISampleService sampleService = context.getBean(ISampleService.class);
        System.out.println(sampleService.selectSampleData());
        context.close();
	}

}
