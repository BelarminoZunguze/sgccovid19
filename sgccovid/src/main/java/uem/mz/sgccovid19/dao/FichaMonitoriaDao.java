package uem.mz.sgccovid19.dao;

import java.util.List;

import uem.mz.sgccovid19.entity.monitoria.FichaMonitoria;

public interface FichaMonitoriaDao extends GenericDao<FichaMonitoria>{
	
	public List<FichaMonitoria> buscarFichaMonitoria();

}
