package uem.mz.mint.dao;

import java.util.List; 

import uem.mz.mint.entity.Instituicao;

public interface InstituicaoDao extends GenericDao<Instituicao>{
	
	public List<Instituicao> buscarInstituicao();
	
	//public List<Instituicao> buscarInstituicaoPor(TipoTabelaSalarial tipTabSal);

}
