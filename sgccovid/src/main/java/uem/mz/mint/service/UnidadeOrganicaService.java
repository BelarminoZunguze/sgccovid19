package uem.mz.mint.service;

import java.util.List;

import uem.mz.mint.entity.Instituicao;
import uem.mz.mint.entity.UnidadeOrganica;

public interface UnidadeOrganicaService extends GenericService<UnidadeOrganica>{
	
	public List<UnidadeOrganica> buscarUnidadeOrganica();
	public List<UnidadeOrganica> buscarUnidadePorInst(Instituicao inst);

}
