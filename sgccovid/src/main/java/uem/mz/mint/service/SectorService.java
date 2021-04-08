package uem.mz.mint.service;
import java.util.List;

import uem.mz.mint.entity.Departamento;
import uem.mz.mint.entity.Sector;

public interface SectorService extends GenericService<Sector>{
	
	public List<Sector> buscarSector();
	public List<Sector> buscarSectorPorDepartamento(Departamento dep);

}
