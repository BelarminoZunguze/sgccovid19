package uem.mz.sgccovid19.dao;

import java.util.List;

import uem.mz.sgccovid19.entity.Instituicao;
import uem.mz.sgccovid19.entity.UnidadeOrganica;

public interface UnidadeOrganicaDao extends GenericDao<UnidadeOrganica>{
	
	public List<UnidadeOrganica> buscarUnidadeOrganica();
	
	public List<UnidadeOrganica> buscarUnidadePorInst(Instituicao inst);
	
	public List<UnidadeOrganica> buscarUnidadePorId(Long userId);

}
