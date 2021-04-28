package uem.mz.sgccovid19.service;

import java.util.List;

import uem.mz.sgccovid19.entity.FichaContactoDirecto;

public interface FichaContactoService extends GenericService<FichaContactoDirecto>{
	
	public List<FichaContactoDirecto> buscarFichaContacto();

}
