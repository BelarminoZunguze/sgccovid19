package uem.mz.sgccovid19.service;

import java.util.List;

import uem.mz.sgccovid19.entity.LocalIsolamento;

public interface LocalIsolamentoService extends GenericService<LocalIsolamento>{
	
	public List<LocalIsolamento> buscarLocalIsolamento();

}
