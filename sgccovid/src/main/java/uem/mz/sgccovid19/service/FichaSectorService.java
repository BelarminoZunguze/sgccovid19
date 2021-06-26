package uem.mz.sgccovid19.service;

import java.util.List;

import uem.mz.sgccovid19.entity.Ficha;
import uem.mz.sgccovid19.entity.FichaSector;

public interface FichaSectorService extends GenericService<FichaSector>{
	
	public List<FichaSector> buscarFichaSector();
	
	public List<FichaSector> buscarFichaSectorPorFicha(Ficha fich);

}
