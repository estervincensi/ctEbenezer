package br.com.ctebenezer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.ctebenezer.domain.Consulta;
import br.com.ctebenezer.domain.Residente;
import br.com.ctebenezer.service.AccountUserDetailsService;
import br.com.ctebenezer.service.ConsultaService;
import br.com.ctebenezer.service.ResidenteService;

@Controller
@RequestMapping("/consulta")
public class ConsultaController {
	private final AccountUserDetailsService accountUserDetailsService;
	private final ResidenteService residenteService;
	private final ConsultaService consultaService;
	
	
	@Autowired
	public ConsultaController(AccountUserDetailsService accountUserDetailsService, ResidenteService residenteService, ConsultaService consultaService) {
		this.accountUserDetailsService = accountUserDetailsService;
		this.consultaService = consultaService;
		this.residenteService = residenteService;
		
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_PRESIDENTE"})
	@GetMapping("/selecionarResidente")
	public String selecionar(Model model,@RequestParam(value="horario",  required=false)String horario) {
		model.addAttribute("residentes",residenteService.buscarAtivos());
		model.addAttribute("consulta", new Consulta());

		model.addAttribute("horario", horario);
		return "/consulta/selecionar";
	}
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_PRESIDENTE"})
	@PostMapping("/agendar")
	public String agendar(@Valid Consulta consulta, BindingResult bindingResult, Model model) {
		Residente residente = residenteService.buscarPorRg(consulta.getResidente().getRg());
		consulta.setResidente(residente);
		model.addAttribute("consulta",consulta);
		model.addAttribute("horas", consultaService.buscarHoras());
		model.addAttribute("medicos", accountUserDetailsService.buscarTodosMedicos());
		return "/consulta/agendar";
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_PRESIDENTE"})
	@PostMapping("/salvar")
	public String salvar(@Valid Consulta consulta, BindingResult bindingResult) {
		if(consultaService.salvar(consulta)) {
			return "redirect:/home";
		}
		return "redirect:/consulta/selecionarResidente?horario=horarioInvalido";
	}
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_PRESIDENTE"})
	@GetMapping("/editar/{id}")
	public String editarConsulta(@PathVariable Long id, Model model) {
		model.addAttribute("consulta",consultaService.buscarPorId(id));
		model.addAttribute("horas", consultaService.buscarHoras());
		model.addAttribute("medicos", accountUserDetailsService.buscarTodosMedicos());
		return "/consulta/editar";
	}
	@GetMapping("/cancelar/{id}")
	public String cancelarConsulta(@PathVariable Long id, Model model) {
		model.addAttribute("id", id);
		return "/consulta/confirma";
		
	}
	@GetMapping("/confirmar/{id}")
	public String confirmarCancelamento(@PathVariable Long id, Model model) {
		consultaService.cancelar(id);
		return "redirect:/consulta/listar";
		
	}
	
	@GetMapping("/listar")
	public String buscarConsultas(Model model) {
		model.addAttribute("consultas", consultaService.buscarTodas());
		return "/consulta/listar";
	}
	
	@Secured("ROLE_MEDICO")
	@GetMapping("adicionarInfo/{id}")
	public String addInfo(@PathVariable Long id, Model model) {
		
		model.addAttribute("consulta",consultaService.buscarPorId(id));
		return "/consulta/addInfo";
	}
	
	@Secured("ROLE_MEDICO")
	@PostMapping("/salvarInfo")
	public String salvarInfo(@Valid Consulta consulta, Model model) {
		Consulta c = consultaService.buscarPorId(consulta.getId());
		c.setObservacoes(consulta.getObservacoes());
		consultaService.salvarInfo(c);
		model.addAttribute("consulta",c);
		return "/consulta/confirmaReceita";
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_PRESIDENTE"})
	@GetMapping("/visualizar/{id}")
	public String visualizarInfo(@PathVariable Long id, Model model) {
		model.addAttribute("consulta", consultaService.buscarPorId(id));
		return "/consulta/visualizar";
	}
	
	

}
