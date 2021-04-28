package uem.mz.sgccovid19.dao;

import java.util.List;

import uem.mz.sgccovid19.entity.Departamento;
import uem.mz.sgccovid19.entity.Sector;

public interface SectorDao extends GenericDao<Sector>{
	
	public List<Sector> buscarSector();
	public List<Sector> buscarSectorPorDepartamento(Departamento dep);

}
