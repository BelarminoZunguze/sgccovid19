package uem.mz.mint.service;

import java.util.List;

import uem.mz.mint.entity.FichaContactoDirecto;

public interface FichaContactoService extends GenericService<FichaContactoDirecto>{
	
	public List<FichaContactoDirecto> buscarFichaContacto();

}
