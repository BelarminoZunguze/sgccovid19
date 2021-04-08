package uem.mz.mint.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.mz.mint.dao.DistritoDao;
import uem.mz.mint.entity.Distrito;
import uem.mz.mint.entity.Provincia;
import uem.mz.mint.service.DistritoService;

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
