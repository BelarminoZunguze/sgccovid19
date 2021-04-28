package uem.mz.sgccovid19.dao;

import java.util.List;

import uem.mz.sgccovid19.entity.Provincia;

public interface ProvinciaDao extends GenericDao<Provincia>{
	
	public List<Provincia> buscarProvincia();

}
