package br.com.ctebenezer.controller;

import javax.validation.Valid;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ctebenezer.domain.Pia;
import br.com.ctebenezer.domain.Residente;
import br.com.ctebenezer.repository.PiaRepository;
import br.com.ctebenezer.service.PiaService;
import br.com.ctebenezer.service.ResidenteService;

@Controller
@RequestMapping("/pia")
public class PiaController {
	
	private final ResidenteService residenteService;
	private final PiaService piaService;
	
	@Autowired
	public PiaController(ResidenteService residenteService, PiaService piaService){
		this.residenteService = residenteService;
		this.piaService = piaService;
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_PRESIDENTE"})
	@GetMapping("/cadastrar/{id}")
	public String cadastrarPiaComID(@PathVariable Long id, Model model) {
		Pia pia = new Pia();
		Residente residente = residenteService.buscar(id);
		if(residente.isPiaAtivo()) {
			return "redirect:/error";
		}
		pia.setResidente(residente);
		model.addAttribute("pia",pia);
		model.addAttribute("dependencias1", piaService.buscarTodasDependencias());
		return "/pias/cadastrar";
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_PRESIDENTE"})
	@PostMapping("/salvarNovo")
	public String salvar(@Valid Pia pia, BindingResult bindingResult){
		Residente residente = residenteService.buscar(pia.getResidente().getId());
		residente.setPiaAtivo(true);
		pia.setAtivo(true);
		residenteService.salvar(residente);
		if(pia.getId()==null) {
			pia.setDataEntrada(DateTime.now().toDate());
		}
		piaService.salvar(pia);
		return "redirect:/residente/listar";
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_PRESIDENTE"})
	@PostMapping("/finalizar")
	public String finalizar(@Valid Pia pia, BindingResult bindingResult){
		piaService.desligar(pia);
		if(pia.isDesistiu()) {
			return "redirect:/home";
		}else {
			return "redirect:/residente/gerarAtestadoAlta/"+pia.getId();
		}
		
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_PRESIDENTE"})
	@GetMapping("/confirma/{id}")
	public String confirma(@PathVariable Long id, Model model) {
		model.addAttribute("id", id);
		return "/pias/confirma";
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_PRESIDENTE"})
	@GetMapping("/editar/{id}")
	public String editarPiaComID(@PathVariable Long id, Model model) {
		Pia pia = piaService.buscarPorResidenteId(id);
		model.addAttribute("pia",pia);
		model.addAttribute("dependencias1", piaService.buscarTodasDependencias());
		return "/pias/cadastrar";
	}
}

