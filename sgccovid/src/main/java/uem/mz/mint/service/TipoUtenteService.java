package uem.mz.mint.service;

import java.util.List;

import uem.mz.mint.entity.TipoUtente;

public interface TipoUtenteService extends GenericService<TipoUtente>{
	
	public List<TipoUtente> buscarTipoUtente();

}
