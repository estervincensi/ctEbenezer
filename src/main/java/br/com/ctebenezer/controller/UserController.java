package br.com.ctebenezer.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.ctebenezer.domain.Account;
import br.com.ctebenezer.domain.Role;
import br.com.ctebenezer.service.AccountUserDetailsService;



@Controller
@RequestMapping("/usuario")
public class UserController {
	@Autowired
	private final AccountUserDetailsService accountUserDetailsService;
	
	
	public UserController(AccountUserDetailsService accountUserDetailsService) {
		this.accountUserDetailsService = accountUserDetailsService;
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/cadastrar")
	public String cadastrarUsuario(Model model, @RequestParam(value="user",  required=false)String user){
		model.addAttribute("account",new Account());
		List<Role> roles= accountUserDetailsService.getAllRoles();
		model.addAttribute("roles", accountUserDetailsService.getAllRoles());
		model.addAttribute("usuarioExiste",user);
		return "/usuario/cadastrar";
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/salvar")
	public String salvar(@Valid Account account,BindingResult bindingResult, Model model) {
		if(accountUserDetailsService.salvarUsuario(account)==true) {
			return "redirect:/home";
		}else {
			return "redirect:/usuario/cadastrar?user=invalido";
		}
		
		
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/listar")
	public String listarUsuario(Model model) {
		model.addAttribute("usuarios",accountUserDetailsService.listarUsuarios());
		return "/usuario/listar";
		
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/ativar/{id}")
	public String ativarUsuario(Model model, @PathVariable Long id) {
		accountUserDetailsService.ativarUsuario(id);
		return "redirect:/usuario/listar";
		
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/desativar/{id}")
	public String desativarUsuario(Model model, @PathVariable Long id) {
		accountUserDetailsService.desativarUsuario(id);
		return "redirect:/usuario/listar";
		
	}
	

}
