package uem.mz.sgccovid19.dao;

import java.util.List;

import uem.mz.sgccovid19.entity.Utente;

public interface UtenteDao extends GenericDao<Utente>{
	
	public List<Utente> buscarUtente();

}
