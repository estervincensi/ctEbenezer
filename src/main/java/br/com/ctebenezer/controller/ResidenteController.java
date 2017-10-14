package br.com.ctebenezer.controller;


import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ctebenezer.domain.Pia;
import br.com.ctebenezer.domain.Residente;
import br.com.ctebenezer.service.PiaService;
import br.com.ctebenezer.service.ResidenteService;

@Controller
@RequestMapping("/residente")
public class ResidenteController {
	private final ResidenteService residenteService;
	private final PiaService piaService;

	public ResidenteController(ResidenteService residenteService, PiaService piaService){
		this.residenteService = residenteService;
		this.piaService = piaService;
	}
	@GetMapping("/cadastrar")
	public String cadastrarResidente(Model model){
		model.addAttribute("residente", new Residente());
		model.addAttribute("estadosCivis", residenteService.buscarTodosEstadosCivis());
		return "/residentes/cadastrar";
	}

	@PostMapping("/salvarNovo")
	public String salvarNovo(@Valid Residente residente,BindingResult bindingResult, Model model){
		residenteService.salvar(residente);
		return "redirect:/pia/confirma/"+residente.getId();
	}
	@GetMapping("/listar")
	public String listarResidentes(Model model){
		model.addAttribute("residentes", residenteService.listarTodos());
		return "/residentes/listar";
	}
	@GetMapping("/reingressar/{id}")
	public String reingressar(@PathVariable Long id){
		residenteService.reingressar(id);
		return "redirect:/pia/cadastrar/"+id;
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Long id, Model model){
		model.addAttribute("residente",residenteService.buscar(id));
		model.addAttribute("estadosCivis", residenteService.buscarTodosEstadosCivis());
		return "/residentes/editar";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Residente residente, BindingResult bindingResult){
		residenteService.salvar(residente);
		return "redirect:/residente/listar";
	}

	@GetMapping("/desligar/{id}")
	public String desligar(@PathVariable Long id, Model model){
		model.addAttribute("pia",piaService.buscarPorResidenteId(id));
		Pia pia = piaService.buscarPorResidenteId(id);
		return "/pias/finalizar";
	}
	@GetMapping("/gerarAtestadoAlta/{id}")
	public String gerarAtestadoAlta(@PathVariable Long id, Model model) {
		Pia pia = piaService.buscarPorId(id);
		model.addAttribute("residente", pia.getResidente());
		model.addAttribute("pia", pia);
		model.addAttribute("tempo", piaService.calculaTempoNaCasa(id));
		return "/residentes/atestadoAlta";
	}
	
	@GetMapping("/listarAtivos")
	public String listarAtivos(Model model) {
		model.addAttribute("residentes", residenteService.buscarAtivosComPia());
		return "/atestados/buscar";
	}
	
	@GetMapping("/gerarAtestadoPermanencia/{id}")
	public String gerarAtestadoPermanencia(@PathVariable Long id, Model model) {
		model.addAttribute("residente", residenteService.buscar(id));
		model.addAttribute("pia", piaService.buscarPorResidenteId(id));
		return "/atestados/atestadoDePermanencia";
	}
}
