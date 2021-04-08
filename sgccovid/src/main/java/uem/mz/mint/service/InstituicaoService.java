package uem.mz.mint.service;

import java.util.List;

import uem.mz.mint.entity.Instituicao;

public interface InstituicaoService extends GenericService<Instituicao>{
	
	public List<Instituicao> buscarInstituicao();

}
