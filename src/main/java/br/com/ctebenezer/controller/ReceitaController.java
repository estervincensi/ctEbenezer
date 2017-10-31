package br.com.ctebenezer.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ctebenezer.domain.Receita;
import br.com.ctebenezer.service.ConsultaService;
import br.com.ctebenezer.service.ReceitaService;

@Controller
@RequestMapping("/receita")
public class ReceitaController {
	private final ConsultaService consultaService;
	private final ReceitaService receitaService;

	public ReceitaController(ConsultaService consultaService, ReceitaService receitaService) {
		this.consultaService = consultaService;
		this.receitaService = receitaService;
	}

	@GetMapping("/addReceita/{id}")
	public String addReceita(@PathVariable Long id, Model model) {
		Receita receita = new Receita();
		receita.setConsulta(consultaService.buscarPorId(id));
		model.addAttribute("receita",receita);
		return "/receita/addReceita";
	}
	
	@PostMapping("/salvarReceita")
	public String salvarReceita(@Valid Receita receita){
		receitaService.salvarReceita(receita);
		return "/consulta/listar";
	}
	
	@GetMapping("/listar")
	public String listarReceita(Model model) {
		model.addAttribute("receitas",receitaService.buscarTodas());
		return "/receita/listar";
	}

}
