package br.com.ctebenezer.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ctebenezer.domain.Pia;
import br.com.ctebenezer.domain.Relatorio;
import br.com.ctebenezer.domain.Residente;
import br.com.ctebenezer.domain.enumerables.Dependencias;
import br.com.ctebenezer.repository.PiaRepository;
import br.com.ctebenezer.repository.ResidenteRepository;

@Service
public class PiaService {
	private final ResidenteRepository residenteRepository;
	private final ResidenteService residenteService;
	private final PiaRepository piaRepository;
	
	@Autowired
	public PiaService(ResidenteRepository residenteRepository, PiaRepository piaRepository,ResidenteService residenteService){
		this.residenteRepository = residenteRepository;
		this.piaRepository = piaRepository;
		this.residenteService = residenteService;
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
	
	public void desligar(Pia pia) {
		Pia piaSalvar = buscarPorId(pia.getId());
		piaSalvar.setDesistiu(pia.isDesistiu());
		piaSalvar.setAvaliacaoFinal(pia.getAvaliacaoFinal());
		piaSalvar.setAtivo(false);
		Residente residente = residenteService.buscar(piaSalvar.getResidente().getId());
		residente.setPiaAtivo(false);
		residente.setAtivo(false);
		piaSalvar.setDataSaida(DateTime.now().toDate());
		String obs = residente.getObservacoes();
		obs+="\nData de entrada = "+piaSalvar.getDataEntrada()+"/Data de Saída:"+piaSalvar.getDataSaida();
		residente.setObservacoes(obs);		
		residenteRepository.save(residente);
		piaRepository.save(piaSalvar);
	}
	
	public void salvar(Pia pia) {
		piaRepository.save(pia);
	}
	
	public Pia buscarPorResidenteId(Long id) {
		Pia pia = piaRepository.findByResidenteId(id);
		return piaRepository.findByResidenteId(id);
	}
	
	public Pia buscarPorId(Long id) {
		return piaRepository.findOne(id);
	}
	public String calculaTempoNaCasa(Long id){
		if(id==null){
			return null;
		}
		Pia pia = piaRepository.findOne(id);
		if(pia==null){
			return null;
		}
		long diferenca = pia.getDataSaida().getTime() - pia.getDataEntrada().getTime();
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
	
	public List<Object> baixasPorAno(){
		List<Object> teste = piaRepository.baixasPorAno();
		/*List<Relatorio> teste = new ArrayList<>();
		for(Object[] obj : retorno) {
			Relatorio r = new Relatorio();
			r.setAno((Integer)obj[0]);
			r.setDesistiu((boolean)obj[1]);
			r.setNumero((BigInteger)obj[2]);
			teste.add(r);
		}
		//return piaRepository.baixasPorAno();*/
		return teste;
	}
}
