package uem.mz.mint.service;

import java.util.List;

import uem.mz.mint.entity.Classificacao;

public interface ClassificacaoService extends GenericService<Classificacao>{
	
	public List<Classificacao> buscarClassificacao();

}
