package uem.mz.mint.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.mz.mint.dao.FichaContactoDao;
import uem.mz.mint.entity.FichaContactoDirecto;
import uem.mz.mint.service.FichaContactoService;

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
