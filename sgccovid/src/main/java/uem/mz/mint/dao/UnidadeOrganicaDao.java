package uem.mz.mint.dao;

import java.util.List;

import uem.mz.mint.entity.Instituicao;
import uem.mz.mint.entity.UnidadeOrganica;

public interface UnidadeOrganicaDao extends GenericDao<UnidadeOrganica>{
	
	public List<UnidadeOrganica> buscarUnidadeOrganica();
	
	public List<UnidadeOrganica> buscarUnidadePorInst(Instituicao inst);

}
