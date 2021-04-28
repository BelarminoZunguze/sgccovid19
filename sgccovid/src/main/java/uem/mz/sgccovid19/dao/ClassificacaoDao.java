package uem.mz.sgccovid19.dao;

import java.util.List;

import uem.mz.sgccovid19.entity.Classificacao;

public interface ClassificacaoDao extends GenericDao<Classificacao>{
	
	public List<Classificacao> buscarClassificacao();

}
