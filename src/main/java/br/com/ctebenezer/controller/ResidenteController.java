package br.com.ctebenezer.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ctebenezer.domain.Residente;

@Controller
@RequestMapping("/residente")
public class ResidenteController {
	
	@GetMapping("/cadastrar")
	public String cadastrarResidente(Model model){
		model.addAttribute("residente", new Residente());
		return "/residentes/cadastrar";
	}
	
	@PostMapping("/salvarNovo")
	public String salvarNovo(@Valid Residente residente){
		return "/index";
	}
}
