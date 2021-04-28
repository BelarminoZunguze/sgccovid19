package uem.mz.sgccovid19.dao;

import java.util.List;

import uem.mz.sgccovid19.entity.Instituicao;

public interface InstituicaoDao extends GenericDao<Instituicao>{
	
	public List<Instituicao> buscarInstituicao();
	
	//public List<Instituicao> buscarInstituicaoPor(TipoTabelaSalarial tipTabSal);

}
