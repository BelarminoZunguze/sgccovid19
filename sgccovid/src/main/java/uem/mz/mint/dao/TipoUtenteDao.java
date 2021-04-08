package uem.mz.mint.dao;

import java.util.List;

import uem.mz.mint.entity.TipoUtente;

public interface TipoUtenteDao extends GenericDao<TipoUtente>{
	
	public List<TipoUtente> buscarTipoUtente();

}
