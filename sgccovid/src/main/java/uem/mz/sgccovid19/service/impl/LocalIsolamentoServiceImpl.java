package uem.mz.sgccovid19.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.mz.sgccovid19.dao.LocalIsolamentoDao;
import uem.mz.sgccovid19.entity.LocalIsolamento;
import uem.mz.sgccovid19.service.LocalIsolamentoService;

@Service("localService")
public class LocalIsolamentoServiceImpl extends GenericServiceImpl<LocalIsolamento> implements LocalIsolamentoService{
	
	@Autowired
	private LocalIsolamentoDao locDao;
	
	@Override
	public List<LocalIsolamento> buscarLocalIsolamento() {
		// TODO Auto-generated method stub
		return locDao.buscarLocalIsolamento();
	}

}
