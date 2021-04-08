package uem.mz.mint.service;

import java.util.List;

import uem.mz.mint.entity.Distrito;
import uem.mz.mint.entity.Provincia;

public interface DistritoService extends GenericService<Distrito>{

	public List<Distrito> buscarDistritos();
	public List<Distrito> buscarDistritosPorProvincia(Provincia pro);
	
}
