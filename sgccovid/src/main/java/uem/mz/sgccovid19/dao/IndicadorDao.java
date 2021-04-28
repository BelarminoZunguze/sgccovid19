package uem.mz.sgccovid19.dao;

import java.util.List;

import uem.mz.sgccovid19.entity.Indicador;

public interface IndicadorDao extends GenericDao<Indicador>{
	
	public List<Indicador> buscarIndicador();
	
	public List<Indicador> buscarIndicadorPorId(Long id);

}
