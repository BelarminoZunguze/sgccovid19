package uem.mz.sgccovid19.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.mz.sgccovid19.dao.ProvinciaDao;
import uem.mz.sgccovid19.entity.Provincia;
import uem.mz.sgccovid19.service.ProvinciaService;

@Service("provinciaService")
public class ProvinciaServiceImp extends GenericServiceImpl<Provincia> implements ProvinciaService{

	@Autowired
	private ProvinciaDao provDao;
	
	@Override
	public List<Provincia> buscarProvincia() {
		// TODO Auto-generated method stub
		return provDao.buscarProvincia();
	}

}
