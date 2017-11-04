package br.com.ctebenezer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.ctebenezer.domain.Relatorio;
import br.com.ctebenezer.service.PiaService;
import br.com.ctebenezer.service.ResidenteService;

@Controller
@RequestMapping("/relatorio")
public class RelatorioController {
	
	private final ResidenteService residenteService;
	private final PiaService piaService;

	public RelatorioController(ResidenteService residenteService, PiaService piaService){
		this.residenteService = residenteService;
		this.piaService = piaService;
	}
	
	@Secured({"ROLE_PRESIDENTE","ROLE_ADMIN"})
	@GetMapping("/graficoBaixas")
	public String graficoBaixas() {
		return "/relatorios/grafico";
	}
	
	@Secured({"ROLE_PRESIDENTE","ROLE_ADMIN"})
	@GetMapping("/graficoAtendimentos")
	public String graficoAtendimentos() {
		return "/relatorios/graficoAtendimento";
	}
	
	@Secured({"ROLE_PRESIDENTE","ROLE_ADMIN"})
	@GetMapping("/graficoDependencias")
	public String graficoDependencias() {
		return "/relatorios/graficoDependencias";
	}
	
	@Secured({"ROLE_PRESIDENTE","ROLE_ADMIN"})
	@RequestMapping(value = "/chart", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String showChart(HttpServletRequest request) {		
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json2 = gson.toJson(piaService.baixasPorAno()); 
        return json2;
    }
	@Secured({"ROLE_PRESIDENTE","ROLE_ADMIN"})
	@RequestMapping(value = "/chartAtendimento", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String showChart1(HttpServletRequest request) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json2 = gson.toJson(piaService.atendimentosPorAno()); 
        return json2;
    }
	@Secured({"ROLE_PRESIDENTE","ROLE_ADMIN"})
	@RequestMapping(value = "/chartDependencia", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String showChart2(HttpServletRequest request) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json2 = gson.toJson(piaService.dependencias()); 
        return json2;
    }

}
