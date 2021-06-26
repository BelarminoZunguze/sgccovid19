package uem.mz.sgccovid19.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.mz.sgccovid19.dao.FichaSectorDao;
import uem.mz.sgccovid19.entity.Ficha;
import uem.mz.sgccovid19.entity.FichaSector;
import uem.mz.sgccovid19.service.FichaSectorService;

@Service("fichaSectorService")
public class FichaSectorServiceImpl extends GenericServiceImpl<FichaSector> implements FichaSectorService{

	@Autowired
	private FichaSectorDao secDao;
	
	@Override
	public List<FichaSector> buscarFichaSector() {
		// TODO Auto-generated method stub
		return secDao.buscarFichaSector();
	}
	
	
	@Override
	public List<FichaSector> buscarFichaSectorPorFicha(Ficha fich) {
		// TODO Auto-generated method stub
		return secDao.buscarFichaSectorPorFicha(fich);
	}
	
}
