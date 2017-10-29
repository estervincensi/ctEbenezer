package br.com.ctebenezer.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import br.com.ctebenezer.domain.Consulta;
import br.com.ctebenezer.repository.ConsultaRepository;

@Service
public class ConsultaService {
	private final ConsultaRepository consultaRepository;
	private final ResidenteService residenteService;

	public ConsultaService(ConsultaRepository consultaRepository, ResidenteService residenteService) {
		this.consultaRepository = consultaRepository;
		this.residenteService = residenteService;
	}
	
	public boolean salvar(Consulta consulta) {
		if(estaDisponivel(consulta)) {
			if(consulta.getResidente().getId()==null) {
				consulta.setResidente(residenteService.buscarPorRg(consulta.getResidente().getRg()));
			}
			Date data = consulta.getData();
			Date agora = DateTime.now().toDate();
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
	public boolean estaDisponivel(Consulta consulta) {
		if(consultaRepository.findByDataAndHoraAndAtivo(consulta.getData(), consulta.getHora(),true)!=null) {
			return false;
		}
		return true;
	}
	
	public List<Consulta> buscarTodas(){
		return consultaRepository.buscarRecentes();
	}
	
	public Consulta buscarPorId(Long id) {
		return consultaRepository.findOne(id);
	}
	public void cancelar(Long id) {
		Consulta consulta = consultaRepository.findOne(id);
		consulta.setAtivo(false);
		consultaRepository.save(consulta);
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
	
}
