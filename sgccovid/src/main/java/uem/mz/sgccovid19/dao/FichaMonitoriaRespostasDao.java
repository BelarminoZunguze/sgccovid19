package uem.mz.sgccovid19.dao;

import java.util.List;

import uem.mz.sgccovid19.entity.monitoria.FichaMonitoriaRespostas;

public interface FichaMonitoriaRespostasDao extends GenericDao<FichaMonitoriaRespostas>{
	
	public List<FichaMonitoriaRespostas> buscarFichaMonitoriaRespostas();
	
	public List<FichaMonitoriaRespostas> buscarFichaMonitoriaRespostasPorId(Long id);
	
	public List<FichaMonitoriaRespostas> buscarFichasPorIndicador(Long id);

}
