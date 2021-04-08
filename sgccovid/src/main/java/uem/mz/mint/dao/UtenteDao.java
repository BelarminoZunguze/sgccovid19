package uem.mz.mint.dao;

import java.util.List;

import uem.mz.mint.entity.Utente;

public interface UtenteDao extends GenericDao<Utente>{
	
	public List<Utente> buscarUtente();

}
