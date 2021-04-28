package uem.mz.sgccovid19.service;

import java.util.List;

import uem.mz.sgccovid19.entity.Classificacao;

public interface ClassificacaoService extends GenericService<Classificacao>{
	
	public List<Classificacao> buscarClassificacao();

}
