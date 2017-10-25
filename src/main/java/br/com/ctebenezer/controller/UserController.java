package br.com.ctebenezer.controller;

import javax.validation.Valid;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.ctebenezer.domain.Account;
import br.com.ctebenezer.service.AccountUserDetailsService;



@Controller
@RequestMapping("/usuario")
public class UserController {
	private final AccountUserDetailsService accountUserDetailsService;
	
	public UserController(AccountUserDetailsService accountUserDetailsService) {
		this.accountUserDetailsService = accountUserDetailsService;
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/cadastrar")
	public String cadastrarUsuario(Model model, @RequestParam(value="user",  required=false)String user){
		model.addAttribute("account",new Account());
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

}
