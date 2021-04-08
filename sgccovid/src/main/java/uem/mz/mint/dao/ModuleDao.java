package uem.mz.mint.dao;

import java.util.List;

import uem.mz.mint.entity.administracao.Module;

public interface ModuleDao extends GenericDao<Module>{

	public List<Module> buscarModules();
	
}
