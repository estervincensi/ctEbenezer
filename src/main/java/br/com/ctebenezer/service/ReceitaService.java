package br.com.ctebenezer.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ctebenezer.domain.Receita;
import br.com.ctebenezer.repository.ReceitaRepository;

@Service
public class ReceitaService {
	private final ReceitaRepository receitaRepository;
	
	@Autowired
	public ReceitaService(ReceitaRepository receitaRepository) {
		super();
		this.receitaRepository = receitaRepository;
	}

	public boolean salvarReceita(Receita receita){
		if(receita.getDescricaoDaReceita()==null||receita.getConsulta()==null||receita.getConsulta().getData()==null) {
			return false;
		}
		Date vencimento = receita.getConsulta().getData();
		receita.setAtivo(true);
		receita.setVencimento(DateUtils.addMonths(vencimento, 3));
		receitaRepository.save(receita);
		return true;
	}
	
	public List<Receita> buscarTodas(){
		verificarVencidas();
		return receitaRepository.buscarTodos();
	}
	
	public Receita buscarPorId(Long id) {
		if(id==null) {
			return null;
		}
		return receitaRepository.findOne(id);
	}
	
	public boolean excluir(Long id) {
		if(id==null) {
			return false;
		}
		Receita receita = receitaRepository.findOne(id);
		if(receita!=null) {
			receita.setAtivo(false);
			receitaRepository.save(receita);
			return true;
		}
		return false;
	}
	private void verificarVencidas(){
		List<Receita> receitas = receitaRepository.buscarTodos();
		for(Receita receita : receitas) {
			if(receita.getVencimento().equals(DateTime.now().toDate())|| receita.getVencimento().before(DateTime.now().toDate())) {
				receita.setAtivo(false);
				receitaRepository.save(receita);
			}
		}
	}

}
