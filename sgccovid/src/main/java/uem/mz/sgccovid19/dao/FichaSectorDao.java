package uem.mz.sgccovid19.dao;

import java.util.List;

import uem.mz.sgccovid19.entity.Ficha;
import uem.mz.sgccovid19.entity.FichaSector;

public interface FichaSectorDao extends GenericDao<FichaSector>{
	
	public List<FichaSector> buscarFichaSector();
	
	public List<FichaSector> buscarFichaSectorPorFicha(Ficha fich);

}
