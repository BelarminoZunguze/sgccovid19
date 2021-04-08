package uem.mz.mint.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.mz.mint.dao.SectorDao;
import uem.mz.mint.entity.Departamento;
import uem.mz.mint.entity.Sector;
import uem.mz.mint.service.SectorService;

@Service("sectorService")
public class SectorServiceImpl extends GenericServiceImpl<Sector> implements SectorService{
	
	@Autowired
	private SectorDao setDao;

	@Override
	public List<Sector> buscarSector() {
		// TODO Auto-generated method stub
		return setDao.buscarSector();
	}

	public List<Sector> buscarSectorPorDepartamento(Departamento dep) {
		// TODO Auto-generated method stub
		return setDao.buscarSectorPorDepartamento(dep);
	}

}
