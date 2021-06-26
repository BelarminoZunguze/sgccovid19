package uem.mz.sgccovid19.dao;

import java.util.List;

import uem.mz.sgccovid19.entity.LocalIsolamento;

public interface LocalIsolamentoDao extends GenericDao<LocalIsolamento>{
	
	public List<LocalIsolamento> buscarLocalIsolamento();

}
