package com.adam.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdamBusinessLogicController {
	
	@GetMapping("/")
	public String businessLogic() {
		return "<h1 style=color:red><marquee behavior=alternate>"
				+ "You have successfully entered into the businessLogic</marquee></h1>";
	}

}
