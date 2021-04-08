package uem.mz.mint.dao;

import java.util.List;

import uem.mz.mint.entity.FichaContactoDirecto;

public interface FichaContactoDao extends GenericDao<FichaContactoDirecto>{
	
	public List<FichaContactoDirecto> buscarFichaContacto();

}
