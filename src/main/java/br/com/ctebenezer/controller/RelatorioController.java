package br.com.ctebenezer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.ctebenezer.domain.Residente;
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
	
	@GetMapping("/grafico")
	public String grafico() {
		return "/relatorios/grafico";
	}
	
	@RequestMapping(value = "/chart", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String showChart(HttpServletRequest request) {
		
		//TODO alterar Json para enviar somente informações importantes para o gráfico
		//retornar p/grafico1 json: anoX{AltaVoltou:X, DesitenciaVoltou:X}
		//retornar p/grafico2 json: anoX{NumeroDeAltas:X, NumeroDeDesistencias:X}
		//retornar p/grafico3 json: anoX{NumeroDeAtendimentos:X}
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json2 = gson.toJson(residenteService.listarTodos());

        return json2;
    }

}
