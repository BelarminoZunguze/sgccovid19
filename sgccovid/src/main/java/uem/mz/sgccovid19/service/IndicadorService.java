package uem.mz.sgccovid19.service;

import java.util.List;

import uem.mz.sgccovid19.entity.Indicador;

public interface IndicadorService extends GenericService<Indicador>{
	
	public List<Indicador> buscarIndicador();
	
	public List<Indicador> buscarIndicadorPorId(Long id);


}
