package com.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	/*
	 * GetMapping makes this api to Get api (returns data)
	 */
	@GetMapping("/hello")
	public String helloWorldApi() {
		return "hello boot";
	}
}
