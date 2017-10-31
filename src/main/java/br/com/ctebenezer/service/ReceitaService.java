package br.com.ctebenezer.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import br.com.ctebenezer.domain.Receita;
import br.com.ctebenezer.repository.ReceitaRepository;

@Service
public class ReceitaService {
	private final ReceitaRepository receitaRepository;
	
	public ReceitaService(ReceitaRepository receitaRepository) {
		super();
		this.receitaRepository = receitaRepository;
	}

	public void salvarReceita(Receita receita){
		Date vencimento = receita.getConsulta().getData();
		receita.setVencimento(DateUtils.addMonths(vencimento, 3));
		receitaRepository.save(receita);
	}
	
	public List<Receita> buscarTodas(){
		return receitaRepository.buscarTodos();
	}

}
