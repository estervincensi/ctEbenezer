package br.com.ctebenezer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.ctebenezer.domain.UserImpl;
import br.com.ctebenezer.service.ResidenteService;


@Controller
public class HomeController {
	private final ResidenteService residenteService;
	
	@Autowired
	public HomeController(ResidenteService residenteService){
		this.residenteService = residenteService;
	}
	@GetMapping("/")
	public String home(@AuthenticationPrincipal UserImpl activeUser, Model model) {
		model.addAttribute("vagas",residenteService.calculaVagas());
		model.addAttribute("usuario",activeUser);
		return "/index";
	}

	@GetMapping("/home")
	public String home1(Model model) {
		model.addAttribute("vagas",residenteService.calculaVagas());
		return "/index";
	}
	@GetMapping("/login")
	public String login(){
		return "/auth/login";
	}

}