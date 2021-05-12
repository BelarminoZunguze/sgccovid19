package uem.mz.sgccovid19.service;

import java.util.List;

import uem.mz.sgccovid19.entity.UnidadeOrganica;
import uem.mz.sgccovid19.entity.monitoria.FichaMonitoria;

public interface FichaMonitoriaService extends GenericService<FichaMonitoria>{
	
	public List<FichaMonitoria> buscarFichaMonitoria();
	
	public List<FichaMonitoria> buscarFichasMonPorUnidade(UnidadeOrganica uniOrg);

}
