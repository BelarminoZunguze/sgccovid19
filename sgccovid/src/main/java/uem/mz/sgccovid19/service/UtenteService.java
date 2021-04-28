package uem.mz.sgccovid19.service;

import java.util.List;

import uem.mz.sgccovid19.entity.Utente;

public interface UtenteService extends GenericService<Utente>{
	
	public List<Utente> buscarUtente();

}
