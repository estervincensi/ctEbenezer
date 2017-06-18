package br.com.ctebenezer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import br.com.ctebenezer.domain.Residente;
import br.com.ctebenezer.domain.enumerables.Dependencias;
import br.com.ctebenezer.domain.enumerables.EstadoCivil;
import br.com.ctebenezer.repository.ResidenteRepository;

@Service
public class ResidenteService {
	private final ResidenteRepository residenteRepository;

	public ResidenteService(ResidenteRepository residenteRepository){
		this.residenteRepository = residenteRepository;
	}

	public List<Dependencias> buscarTodasDependencias(){
		List<Dependencias> dependencias = new ArrayList<>();
		dependencias.add(Dependencias.ALCOOL);
		dependencias.add(Dependencias.COCAINA);
		dependencias.add(Dependencias.CRACK);
		dependencias.add(Dependencias.HEROINA);
		dependencias.add(Dependencias.MACONHA);
		dependencias.add(Dependencias.MERLA);
		dependencias.add(Dependencias.OUTRO);
		return dependencias;

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
		return residenteRepository.save(residente);
	}

	public int[] calculaVagas(){
		int[] vagas = new int [2];
		List<Residente> residentes = residenteRepository.findAll();
		int totalCadastrados = residentes.size();
		int totalVagas = 40-totalCadastrados;
		vagas[0] = totalCadastrados;
		vagas[1] = totalVagas;
		return vagas;
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
		residente.setObservacoes(residente.getObservacoes()+"\n Data de entrada:"+residente.getDataEntrada()+" | Data de saída:"+residente.getDataSaida());
		residente.setDataSaida(null);
		residente.setDataEntrada(DateTime.now().toDate());
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
		residente.setDataSaida(DateTime.now().toDate());
		return residenteRepository.save(residente);
	}
	public String calculaTempoNaCasa(Long id){
		if(id==null){
			return null;
		}
		Residente residente = residenteRepository.findOne(id);
		if(residente==null){
			return null;
		}
		long diferenca = residente.getDataSaida().getTime() - residente.getDataEntrada().getTime();
		long dias = TimeUnit.DAYS.convert(diferenca,TimeUnit.MILLISECONDS);
	    if(dias < 30){
	    	return dias+" dias";
	    }else{
	    	int meses = (int)dias/30;
	    	if(meses==1){
	    		return meses+" mes";
	    	}
	    	return meses+" meses";
	    }
	}

}
