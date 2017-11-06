package br.com.ctebenezer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.ctebenezer.domain.Role;
import br.com.ctebenezer.domain.UserImpl;
import br.com.ctebenezer.service.ResidenteService;


@Controller
public class HomeController {
	private final ResidenteService residenteService;
	
	@Autowired
	public HomeController(ResidenteService residenteService){
		this.residenteService = residenteService;
	}
	@GetMapping("/")
	public String home(@AuthenticationPrincipal UserImpl activeUser, Model model) {
		boolean isRolePresent = false;
		for (GrantedAuthority grantedAuthority : activeUser.getAuthorities()) {
		      isRolePresent = grantedAuthority.getAuthority().equals("ROLE_MEDICO");
		      if (isRolePresent) break;
		}
		if(isRolePresent) {
			return "redirect:/consulta/listar";
		}
		return "redirect:/residente/listar";
	}

	@GetMapping("/login")
	public String login(){
		return "/auth/login";
	}

}