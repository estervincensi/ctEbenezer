package br.com.ctebenezer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
	
	@Autowired
	public ReceitaController(ConsultaService consultaService, ReceitaService receitaService) {
		this.consultaService = consultaService;
		this.receitaService = receitaService;
	}
	
	@Secured({"ROLE_ADMIN","ROLE_MEDICO"})
	@GetMapping("/addReceita/{id}")
	public String addReceita(@PathVariable Long id, Model model) {
		Receita receita = new Receita();
		receita.setConsulta(consultaService.buscarPorId(id));
		model.addAttribute("receita",receita);
		return "/receita/addReceita";
	}
	
	@Secured({"ROLE_ADMIN","ROLE_MEDICO"})
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
	
	@Secured({"ROLE_ADMIN","ROLE_MEDICO"})
	@GetMapping("/editar/{id}")
	public String editarReceita(@PathVariable Long id, Model model) {
		Receita receita = receitaService.buscarPorId(id);
		model.addAttribute("receita",receita);
		return "/receita/addReceita";
	}
	
	@Secured({"ROLE_ADMIN","ROLE_MEDICO"})
	@GetMapping("/excluir/{id}")
	public String cancelarReceita(@PathVariable Long id, Model model) {
		model.addAttribute("id",id);
		return "/receita/confirmaExclusao";
	}
	
	@Secured({"ROLE_ADMIN","ROLE_MEDICO"})
	@GetMapping("/confirmaExcluir/{id}")
	public String visualizarInfo(@PathVariable Long id) {
		receitaService.excluir(id);
		return "redirect:/receita/listar";
	}

}
