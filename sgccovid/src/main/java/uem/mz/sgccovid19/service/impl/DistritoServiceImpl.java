package uem.mz.sgccovid19.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.mz.sgccovid19.dao.DistritoDao;
import uem.mz.sgccovid19.entity.Distrito;
import uem.mz.sgccovid19.entity.Provincia;
import uem.mz.sgccovid19.service.DistritoService;

@Service("distritoService")
public class DistritoServiceImpl extends GenericServiceImpl<Distrito> implements DistritoService{
	
	@Autowired
	private DistritoDao disDao;

	@Override
	public List<Distrito> buscarDistritos() {
		// TODO Auto-generated method stub
		return disDao.buscarDistritos();
	}

	@Override
	public List<Distrito> buscarDistritosPorProvincia(Provincia pro) {
		// TODO Auto-generated method stub
		return disDao.buscarDistritosPorProvincia(pro);
	}

}
