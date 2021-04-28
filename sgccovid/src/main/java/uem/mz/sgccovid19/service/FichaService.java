package uem.mz.sgccovid19.service;

import java.util.List;

import uem.mz.sgccovid19.entity.Ficha;

public interface FichaService extends GenericService<Ficha>{
	
	public List<Ficha> buscarFicha();

}
