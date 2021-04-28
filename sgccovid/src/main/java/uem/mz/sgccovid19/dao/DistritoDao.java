package uem.mz.sgccovid19.dao;

import java.util.List;

import uem.mz.sgccovid19.entity.Distrito;
import uem.mz.sgccovid19.entity.Provincia;

public interface DistritoDao extends GenericDao<Distrito>{
	
	public List<Distrito> buscarDistritos();
	public List<Distrito> buscarDistritosPorProvincia(Provincia pro);

}
