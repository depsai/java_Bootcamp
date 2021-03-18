package com;



import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit.jupiter.SpringExtension;



@ExtendWith(SpringExtension.class)

@SpringBootTest

public class TestcalculatorService {



	int a=11, b=20;

	

	@Test

	public void testsum() {

	Calculator cal = new Calculator();

	Assertions.assertEquals(16, cal.add());

	System.out.println(" Addition test case");

	

	}
	

	@Test

	public void testdiv() {

		Calculator cal = new Calculator();

	Assertions.assertEquals(5, cal.div());

	System.out.println("Division test case");

	

	}

	

	

}