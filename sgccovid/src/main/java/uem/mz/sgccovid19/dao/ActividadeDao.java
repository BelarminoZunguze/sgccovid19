package uem.mz.sgccovid19.dao;

import java.util.List;

import uem.mz.sgccovid19.entity.Actividade;

public interface ActividadeDao extends GenericDao<Actividade>{
	
	public List<Actividade> buscarActividade();

}
