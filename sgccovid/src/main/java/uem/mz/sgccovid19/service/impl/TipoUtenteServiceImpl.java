package uem.mz.sgccovid19.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.mz.sgccovid19.dao.TipoUtenteDao;
import uem.mz.sgccovid19.entity.TipoUtente;
import uem.mz.sgccovid19.service.TipoUtenteService;

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
