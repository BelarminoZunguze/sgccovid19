package uem.mz.sgccovid19.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.mz.sgccovid19.dao.FichaDao;
import uem.mz.sgccovid19.entity.Ficha;
import uem.mz.sgccovid19.service.FichaService;

@Service("fichaService")
public class FichaServiceImpl extends GenericServiceImpl<Ficha> implements FichaService{
	
	@Autowired
	private FichaDao fichDao;
	
	@Override
	public List<Ficha> buscarFicha() {
		// TODO Auto-generated method stub
		return fichDao.buscarFicha();
	}

}
