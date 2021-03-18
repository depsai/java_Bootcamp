package com;



import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;



@RestController
public class MessageService{

	
	@GetMapping("/git")
	public String soft_git() {

		return "Hello git users";

	}

	@GetMapping("/maven")
	public String soft_maven() {

		return "Hello Maven Users!!!";

	}
	
	@GetMapping("/eclipse")
	public String soft_eclipse() {

		return "Hello eclipse users";

	}
	
	@GetMapping("/jenkins")
	public String soft_jenkins() {

		return "Hello jenkins Users!!!";

	}
	

}