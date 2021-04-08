package uem.mz.mint.service;

import java.util.List;

import uem.mz.mint.entity.Provincia;

public interface ProvinciaService extends GenericService<Provincia>{
	
	public List<Provincia> buscarProvincia();

}
