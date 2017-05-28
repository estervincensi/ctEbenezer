package br.com.ctebenezer.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.ctebenezer.domain.*;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;


@Controller
public class HomeController {

	@GetMapping("/")
	public String home(@AuthenticationPrincipal UserImpl activeUser) {
		return "/index";
	}

	@GetMapping("/hi")
	public Map<String, String> greetings(Principal p) {
		return Collections.singletonMap("content", "Hello, " + p.getName());
	}
	@GetMapping("/login")
	public String login(){
		return "/auth/login";
	}

}