package br.com.ctebenezer.controller;

import javax.validation.Valid;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String cadastrarUsuario(Model model){
		model.addAttribute("account",new Account());
		model.addAttribute("roles", accountUserDetailsService.getAllRoles());
		return "/usuario/cadastrar";
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/salvar")
	public String salvar(@Valid Account account,BindingResult bindingResult, Model model) {
		String newPassword = accountUserDetailsService.passwordEncoder().encode(account.getPassword());
		account.setPassword(newPassword);
		accountUserDetailsService.salvarUsuario(account);
		return "redirect:/home";
	}

}
