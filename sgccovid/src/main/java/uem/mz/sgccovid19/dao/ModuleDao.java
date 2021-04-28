package uem.mz.sgccovid19.dao;

import java.util.List;

import uem.mz.sgccovid19.entity.administracao.Module;

public interface ModuleDao extends GenericDao<Module>{

	public List<Module> buscarModules();
	
}
