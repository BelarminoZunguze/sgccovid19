package uem.mz.mint.dao;

import java.util.List;

import uem.mz.mint.entity.Ficha;

public interface FichaDao extends GenericDao<Ficha>{
	
	public List<Ficha> buscarFicha();

}
