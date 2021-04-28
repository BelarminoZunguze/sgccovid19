package uem.mz.sgccovid19.service;

import java.util.List;

import uem.mz.sgccovid19.entity.Actividade;

public interface ActividadeService extends GenericService<Actividade>{
	
	public List<Actividade> buscarActividade();

}
