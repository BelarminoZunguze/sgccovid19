package uem.mz.mint.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.mz.mint.dao.ModuleDao;
import uem.mz.mint.entity.administracao.Module;
import uem.mz.mint.service.ModuleService;


@Service("moduleService")
public class ModuleServiceImpl extends GenericServiceImpl<Module> implements ModuleService{

	@Autowired
	private ModuleDao modDao;

	@Override
	public List<Module> buscarModules() {
		// TODO Auto-generated method stub
		return modDao.buscarModules();
	}
	
}
