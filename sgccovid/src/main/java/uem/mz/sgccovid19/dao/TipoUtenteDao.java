package uem.mz.sgccovid19.dao;

import java.util.List;

import uem.mz.sgccovid19.entity.TipoUtente;

public interface TipoUtenteDao extends GenericDao<TipoUtente>{
	
	public List<TipoUtente> buscarTipoUtente();

}
