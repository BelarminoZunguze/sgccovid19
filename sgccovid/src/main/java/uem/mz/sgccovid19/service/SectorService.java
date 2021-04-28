package uem.mz.sgccovid19.service;
import java.util.List;

import uem.mz.sgccovid19.entity.Departamento;
import uem.mz.sgccovid19.entity.Sector;

public interface SectorService extends GenericService<Sector>{
	
	public List<Sector> buscarSector();
	public List<Sector> buscarSectorPorDepartamento(Departamento dep);

}
