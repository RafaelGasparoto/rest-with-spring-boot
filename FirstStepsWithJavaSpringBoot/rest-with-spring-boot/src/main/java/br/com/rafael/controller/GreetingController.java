package br.com.rafael.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.rafael.Greeting;

@RestController
public class GreetingController {
	private static final String template = "Hello, %s!";
	private static final AtomicLong counter = new AtomicLong();
	
	@RequestMapping("/greeting")
	public Greeting gretting(@RequestParam(value = "name", defaultValue= "world") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}
