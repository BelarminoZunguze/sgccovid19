package uem.mz.mint.service;

import java.util.List;

import uem.mz.mint.entity.Utente;

public interface UtenteService extends GenericService<Utente>{
	
	public List<Utente> buscarUtente();

}
