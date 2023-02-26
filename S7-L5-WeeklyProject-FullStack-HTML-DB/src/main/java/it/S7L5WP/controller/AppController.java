package it.S7L5WP.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

	@GetMapping("")
	public String index() {
		return "index";
	}
	
	@GetMapping("/crea")
	public String crea() {
	    return "crea";
	}
	
	@GetMapping("/crea_successo")
	public String successo() {
		return "creato";
	}
	
	@GetMapping("/allarme")
	public String allarme() {
	    return "allarme";
	}
	
	@GetMapping("/404")
	public String ErroreNotFound() {
	    return "404";
	}
}
