package uem.mz.sgccovid19.service;

import java.util.List;

import uem.mz.sgccovid19.entity.TipoUtente;

public interface TipoUtenteService extends GenericService<TipoUtente>{
	
	public List<TipoUtente> buscarTipoUtente();

}
