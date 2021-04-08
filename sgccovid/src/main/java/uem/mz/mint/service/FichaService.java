package uem.mz.mint.service;

import java.util.List;

import uem.mz.mint.entity.Ficha;

public interface FichaService extends GenericService<Ficha>{
	
	public List<Ficha> buscarFicha();

}
