package com.spannering.spanneringapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class SpanneringAppApplication {

	public static void main(String[] args) {
		ApplicationContext ctx =  SpringApplication.run(SpanneringAppApplication.class, args);
//
//		System.out.println("Let's start by inspecting the beans provided by Spring Boot:");
//
//		String[] beanNames = ctx.getBeanDefinitionNames();
//		Arrays.sort(beanNames);
//		for (String beanName : beanNames) {
//			System.out.println("\t" + beanName);
//		}
//
//		System.out.println("---------------------------------- end -------------------------------");

	}
}
