package uem.mz.sgccovid19.service;

import java.util.List;

import uem.mz.sgccovid19.entity.administracao.Module;


public interface ModuleService extends GenericService<Module>{

	public List<Module> buscarModules();
	
}
