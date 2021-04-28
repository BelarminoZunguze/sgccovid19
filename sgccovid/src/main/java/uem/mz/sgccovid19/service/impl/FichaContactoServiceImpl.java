package uem.mz.sgccovid19.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.mz.sgccovid19.dao.FichaContactoDao;
import uem.mz.sgccovid19.entity.FichaContactoDirecto;
import uem.mz.sgccovid19.service.FichaContactoService;

@Service("fichaContactoService")
public class FichaContactoServiceImpl extends GenericServiceImpl<FichaContactoDirecto> implements FichaContactoService{
	
	@Autowired
	private FichaContactoDao fiDao;
	
	@Override
	public List<FichaContactoDirecto> buscarFichaContacto() {
		// TODO Auto-generated method stub
		return fiDao.buscarFichaContacto();
	}

}
