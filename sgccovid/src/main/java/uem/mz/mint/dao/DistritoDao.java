package uem.mz.mint.dao;

import java.util.List;

import uem.mz.mint.entity.Distrito;
import uem.mz.mint.entity.Provincia;

public interface DistritoDao extends GenericDao<Distrito>{
	
	public List<Distrito> buscarDistritos();
	public List<Distrito> buscarDistritosPorProvincia(Provincia pro);

}
