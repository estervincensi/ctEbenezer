package br.com.ctebenezer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ctebenezer.domain.Pia;
import br.com.ctebenezer.domain.enumerables.Dependencias;
import br.com.ctebenezer.repository.PiaRepository;
import br.com.ctebenezer.repository.ResidenteRepository;

@Service
public class PiaService {
	private final ResidenteRepository residenteRepository;
	private final PiaRepository piaRepository;

	public PiaService(ResidenteRepository residenteRepository, PiaRepository piaRepository){
		this.residenteRepository = residenteRepository;
		this.piaRepository = piaRepository;
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
	
	public void salvar(Pia pia) {
		piaRepository.save(pia);
	}
}
