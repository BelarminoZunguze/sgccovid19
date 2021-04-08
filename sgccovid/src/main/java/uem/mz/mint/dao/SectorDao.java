package uem.mz.mint.dao;

import java.util.List;

import uem.mz.mint.entity.Departamento;
import uem.mz.mint.entity.Sector;

public interface SectorDao extends GenericDao<Sector>{
	
	public List<Sector> buscarSector();
	public List<Sector> buscarSectorPorDepartamento(Departamento dep);

}
