package br.com.ctebenezer.controller;


import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ctebenezer.domain.Residente;
import br.com.ctebenezer.service.ResidenteService;

@Controller
@RequestMapping("/residente")
public class ResidenteController {
	private final ResidenteService residenteService;

	public ResidenteController(ResidenteService residenteService){
		this.residenteService = residenteService;
	}
	@GetMapping("/cadastrar")
	public String cadastrarResidente(Model model){
		model.addAttribute("residente", new Residente());
		model.addAttribute("dependencias1", residenteService.buscarTodasDependencias());
		model.addAttribute("estadosCivis", residenteService.buscarTodosEstadosCivis());
		return "/residentes/cadastrar";
	}

	@PostMapping("/salvarNovo")
	public String salvarNovo(@Valid Residente residente,BindingResult bindingResult, Model model){
		residenteService.salvar(residente);
		return "redirect:/home";
	}
}
