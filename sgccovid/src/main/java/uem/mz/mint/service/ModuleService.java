package uem.mz.mint.service;

import java.util.List;

import uem.mz.mint.entity.administracao.Module;


public interface ModuleService extends GenericService<Module>{

	public List<Module> buscarModules();
	
}
