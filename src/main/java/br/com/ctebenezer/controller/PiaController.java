package br.com.ctebenezer.controller;

import javax.validation.Valid;

import org.joda.time.DateTime;
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

	public PiaController(ResidenteService residenteService, PiaService piaService){
		this.residenteService = residenteService;
		this.piaService = piaService;
	}
	
	
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
	
	@GetMapping("/cadastrar")
	public String cadastarPIA(Model model) {
		model.addAttribute("residentes", residenteService.buscarAtivosSemPia());
		return "/pias/listarSemPia";
	}
	
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
	
	@PostMapping("/finalizar")
	public String finalizar(@Valid Pia pia, BindingResult bindingResult){
		Pia piaSalvar = piaService.buscarPorId(pia.getId());
		piaSalvar.setDesistiu(pia.isDesistiu());
		piaSalvar.setAvaliacaoFinal(pia.getAvaliacaoFinal());
		piaSalvar.setAtivo(false);
		Residente residente = residenteService.buscar(piaSalvar.getResidente().getId());
		residente.setPiaAtivo(false);
		residente.setAtivo(false);
		piaSalvar.setDataSaida(DateTime.now().toDate());
		String obs = residente.getObservacoes();
		obs+="\nData de entrada = "+piaSalvar.getDataEntrada()+"/Data de Saída:"+piaSalvar.getDataSaida();
		residente.setObservacoes(obs);		
		residenteService.salvar(residente);
		piaService.salvar(piaSalvar);
		if(pia.isDesistiu()) {
			return "redirect: /home";
		}else {
			return "redirect:/residente/gerarAtestadoAlta/"+pia.getId();
		}
		
	}
	
	@GetMapping("/confirma/{id}")
	public String confirma(@PathVariable Long id, Model model) {
		model.addAttribute("id", id);
		return "/pias/confirma";
	}
	
	@GetMapping("/editar")
	public String listarTodos(Model model){
		model.addAttribute("residentes", residenteService.buscarAtivosComPia());
		return "/pias/listarComPia";
	}
	
	@GetMapping("/editar/{id}")
	public String editarPiaComID(@PathVariable Long id, Model model) {
		Pia pia = piaService.buscarPorResidenteId(id);
		model.addAttribute("pia",pia);
		model.addAttribute("dependencias1", piaService.buscarTodasDependencias());
		return "/pias/cadastrar";
	}
}

