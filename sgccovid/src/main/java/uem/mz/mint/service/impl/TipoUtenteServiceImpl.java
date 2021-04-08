package uem.mz.mint.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.mz.mint.dao.TipoUtenteDao;
import uem.mz.mint.entity.TipoUtente;
import uem.mz.mint.service.TipoUtenteService;

@Service("tipoUtenteService")
public class TipoUtenteServiceImpl extends GenericServiceImpl<TipoUtente> implements TipoUtenteService{
	
	@Autowired
	private TipoUtenteDao tputDao;
	
	@Override
	public List<TipoUtente> buscarTipoUtente() {
		// TODO Auto-generated method stub
		return tputDao.buscarTipoUtente();
	}

}
