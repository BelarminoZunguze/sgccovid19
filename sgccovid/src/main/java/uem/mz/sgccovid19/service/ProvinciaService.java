package uem.mz.sgccovid19.service;

import java.util.List;

import uem.mz.sgccovid19.entity.Provincia;

public interface ProvinciaService extends GenericService<Provincia>{
	
	public List<Provincia> buscarProvincia();

}
