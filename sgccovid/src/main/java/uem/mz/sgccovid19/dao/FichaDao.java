package uem.mz.sgccovid19.dao;

import java.util.List;

import uem.mz.sgccovid19.entity.Ficha;

public interface FichaDao extends GenericDao<Ficha>{
	
	public List<Ficha> buscarFicha();

}
