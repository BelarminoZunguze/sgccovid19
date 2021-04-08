package uem.mz.mint.dao;

import java.util.List;
import uem.mz.mint.entity.Provincia;

public interface ProvinciaDao extends GenericDao<Provincia>{
	
	public List<Provincia> buscarProvincia();

}
