package uem.mz.mint.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.mz.mint.dao.UtenteDao;
import uem.mz.mint.entity.Utente;
import uem.mz.mint.service.UtenteService;

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
