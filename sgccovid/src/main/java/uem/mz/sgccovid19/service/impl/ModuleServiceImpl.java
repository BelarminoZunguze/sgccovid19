package uem.mz.sgccovid19.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.mz.sgccovid19.dao.ModuleDao;
import uem.mz.sgccovid19.entity.administracao.Module;
import uem.mz.sgccovid19.service.ModuleService;


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
