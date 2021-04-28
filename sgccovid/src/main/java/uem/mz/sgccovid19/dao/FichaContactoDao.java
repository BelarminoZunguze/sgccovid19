package uem.mz.sgccovid19.dao;

import java.util.List;

import uem.mz.sgccovid19.entity.FichaContactoDirecto;

public interface FichaContactoDao extends GenericDao<FichaContactoDirecto>{
	
	public List<FichaContactoDirecto> buscarFichaContacto();

}
