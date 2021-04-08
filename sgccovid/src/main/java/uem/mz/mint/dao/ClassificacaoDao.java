package uem.mz.mint.dao;

import java.util.List;

import uem.mz.mint.entity.Classificacao;

public interface ClassificacaoDao extends GenericDao<Classificacao>{
	
	public List<Classificacao> buscarClassificacao();

}
