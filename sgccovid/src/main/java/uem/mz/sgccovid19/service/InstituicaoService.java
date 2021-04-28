package uem.mz.sgccovid19.service;

import java.util.List;

import uem.mz.sgccovid19.entity.Instituicao;

public interface InstituicaoService extends GenericService<Instituicao>{
	
	public List<Instituicao> buscarInstituicao();

}
