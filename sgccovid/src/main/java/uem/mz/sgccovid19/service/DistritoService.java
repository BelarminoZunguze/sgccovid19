package uem.mz.sgccovid19.service;

import java.util.List;

import uem.mz.sgccovid19.entity.Distrito;
import uem.mz.sgccovid19.entity.Provincia;

public interface DistritoService extends GenericService<Distrito>{

	public List<Distrito> buscarDistritos();
	public List<Distrito> buscarDistritosPorProvincia(Provincia pro);
	
}
