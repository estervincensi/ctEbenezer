package br.com.ctebenezer.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ctebenezer.domain.Consulta;
import br.com.ctebenezer.domain.Receita;
import br.com.ctebenezer.repository.ConsultaRepository;
import br.com.ctebenezer.repository.ReceitaRepository;

@Service
public class ConsultaService {
	private final ConsultaRepository consultaRepository;
	private final ResidenteService residenteService;
	
	@Autowired
	public ConsultaService(ConsultaRepository consultaRepository, ResidenteService residenteService) {
		this.consultaRepository = consultaRepository;
		this.residenteService = residenteService;
	}
	
	public boolean salvar(Consulta consulta) {
		if(estaDisponivel(consulta)) {
			if(consulta.getResidente().getId()==null) {
				if(consulta.getResidente().getRg()==null){
					return false;
				}
				consulta.setResidente(residenteService.buscarPorRg(consulta.getResidente().getRg()));
			}
			if(consulta.getData().after(DateTime.now().toDate())) {
				consulta.setAtivo(true);
				consultaRepository.save(consulta);
				return true;
			}else {
				return false;
			}
			
		}else{
			return false;
		}
	}
	private boolean estaDisponivel(Consulta consulta) {
		if(consultaRepository.findByDataAndHoraAndAtivo(consulta.getData(), consulta.getHora(),true)!=null) {
			return false;
		}
		return true;
	}
	
	public List<Consulta> buscarTodas(){
		return consultaRepository.buscarRecentes();
	}
	
	public Consulta buscarPorId(Long id) {
		if(id==null){
			return null;
		}
		return consultaRepository.findOne(id);
	}
	public boolean cancelar(Long id) {
		if(id==null){
			return false;
		}
		Consulta consulta = consultaRepository.findOne(id);
		if(consulta==null){
			return false;
		}
		consulta.setAtivo(false);
		consultaRepository.save(consulta);
		return true;
	}
	public List<String> buscarHoras(){
		List<String> horas = new ArrayList<>();
		horas.add("08:00");
		horas.add("09:00");
		horas.add("10:00");
		horas.add("11:00");
		horas.add("13:00");
		horas.add("14:00");
		horas.add("15:00");
		horas.add("16:00");
		horas.add("17:00");
		horas.add("18:00");
		return horas;
		
	}
	public Consulta salvarInfo(Consulta consulta){
		if(consulta.getObservacoes()==null || consulta.getObservacoes().isEmpty()){
			return null;
		}
		return consultaRepository.save(consulta);
	}
	
	
}
