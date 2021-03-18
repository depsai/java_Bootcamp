package com;



import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;



@RestController

public class Calculator {

	@GetMapping("/add")

	public int add() {

		int a=11, b=5;

		return a+b;

	}


	@GetMapping("/div")

	public int div() {

		int a=10, b=2;

		return a/b;

	}

}