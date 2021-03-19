   

package com;





import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit.jupiter.SpringExtension;



@ExtendWith(SpringExtension.class)

@SpringBootTest

public class TestMessageService {

	

	@Test

	public void testgit() {

		MessageService messageService = new MessageService();

		//System.out.println("hello");

		Assertions.assertEquals(messageService.soft_git(), "Hello git users");

	}

	

	@Test

	public void testmaven() {

		MessageService ms = new MessageService();

		Assertions.assertEquals("Hello Maven Users!!!", ms.soft_maven());

	}
	
	public void testeclipse() {

		MessageService messageService = new MessageService();

		//System.out.println("hello");

		Assertions.assertEquals(messageService.soft_eclipse(), "Hello eclipse users");

	}

	

	@Test

	public void testjenkins() {

		MessageService ms = new MessageService();

		Assertions.assertEquals("Hello jenkins Users!!!", ms.soft_jenkins());

	}

	

}