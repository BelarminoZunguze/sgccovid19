package uem.mz.sgccovid19.service;

import java.util.List;

import uem.mz.sgccovid19.entity.Instituicao;
import uem.mz.sgccovid19.entity.UnidadeOrganica;

public interface UnidadeOrganicaService extends GenericService<UnidadeOrganica>{
	
	public List<UnidadeOrganica> buscarUnidadeOrganica();
	public List<UnidadeOrganica> buscarUnidadePorInst(Instituicao inst);

}
