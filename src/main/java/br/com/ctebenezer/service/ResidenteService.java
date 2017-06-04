package br.com.ctebenezer.service;

import java.util.ArrayList;
import java.util.List;

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
	public void reingressar(Long id){
		Residente residente = residenteRepository.findOne(id);
		residente.setObservacoes(residente.getObservacoes()+"\n Data de entrada:"+residente.getDataEntrada()+" | Data de saída:"+residente.getDataSaida());
		residente.setDataSaida(null);
		residente.setDataEntrada(DateTime.now().toDate());
		residenteRepository.save(residente);
	}

}
