package uem.mz.sgccovid19.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.mz.sgccovid19.dao.UtenteDao;
import uem.mz.sgccovid19.entity.Utente;
import uem.mz.sgccovid19.service.UtenteService;

@Service("utenteService")
public class UtenteServiceImpl extends GenericServiceImpl<Utente> implements UtenteService{
	
	@Autowired
	private UtenteDao utDao;
	
	@Override
	public List<Utente> buscarUtente() {
		// TODO Auto-generated method stub
		return utDao.buscarUtente();
	}

}
