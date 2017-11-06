package br.com.ctebenezer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ctebenezer.domain.Pia;
import br.com.ctebenezer.domain.Residente;
import br.com.ctebenezer.domain.enumerables.Dependencias;
import br.com.ctebenezer.domain.enumerables.EstadoCivil;
import br.com.ctebenezer.repository.PiaRepository;
import br.com.ctebenezer.repository.ResidenteRepository;

@Service
public class ResidenteService {
	private final ResidenteRepository residenteRepository;
	private final PiaRepository piaRepository;

	@Autowired
	public ResidenteService(ResidenteRepository residenteRepository, PiaRepository piaRepository){
		this.residenteRepository = residenteRepository;
		this.piaRepository = piaRepository;
	}
	public List<EstadoCivil> buscarTodosEstadosCivis(){
		List<EstadoCivil> estadosCivis = new ArrayList<>();
		estadosCivis.add(EstadoCivil.CASADO);
		estadosCivis.add(EstadoCivil.DIVORCIADO);
		estadosCivis.add(EstadoCivil.SEPARADO);
		estadosCivis.add(EstadoCivil.SOLTEIRO);
		estadosCivis.add(EstadoCivil.VIUVO);
		return estadosCivis;

	}

	public Residente salvar(Residente residente){
		if(residente==null){
			return null;
		}if(residente.getNome()==null||residente.getDataNascimento()==null||residente.getEndereco()==null||residente.getNaturalidade()==null||residente.getEstadoCivil()==null||residente.getResponsavel()==null){
			return null;
		}
		residente.setAtivo(true);
		return residenteRepository.save(residente);
	}

	public List<Residente> listarTodos(){
		return residenteRepository.findAll();
	}
	public Residente reingressar(Long id){
		if(id==null){
			return null;
		}
		Residente residente = residenteRepository.findOne(id);
		if(residente==null){
			return null;
		}
		residente.setObservacoes(residente.getObservacoes()+"\n Data de entrada:"+DateTime.now().toDate());
		residente.setAtivo(true);
		return residenteRepository.save(residente);
	}
	public Residente buscar(Long id){
		if(id==null){
			return null;
		}
		return residenteRepository.findOne(id);
	}
	public Residente desligar(Long id){
		if(id==null){
			return null;
		}
		Residente residente = residenteRepository.findOne(id);
		if(residente==null){
			return null;
		}
		residente.setObservacoes(residente.getObservacoes()+"|Data de saída:"+DateTime.now().toDate());
		residente.setAtivo(false);
		residente.setPiaAtivo(false);
		Pia pia = piaRepository.findByResidenteId(residente.getId());
		pia.setDataSaida(DateTime.now().toDate());
		piaRepository.save(pia);
		return residenteRepository.save(residente);
	}
	
	public List<Residente> buscarAtivos(){
		return residenteRepository.findAtivos();
	}
	public List<Residente> buscarAtivosComPia(){
		return residenteRepository.findWithPIA();
	}
	
	public Residente buscarPorRg(String rg) {
		return residenteRepository.findByRg(rg);
	}

}
