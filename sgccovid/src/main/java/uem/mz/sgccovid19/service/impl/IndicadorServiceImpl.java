package uem.mz.sgccovid19.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.mz.sgccovid19.dao.IndicadorDao;
import uem.mz.sgccovid19.entity.Indicador;
import uem.mz.sgccovid19.service.IndicadorService;

@Service("indicadorService")
public class IndicadorServiceImpl extends GenericServiceImpl<Indicador> implements IndicadorService{
	
	@Autowired
	private IndicadorDao indDao;

	@Override
	public List<Indicador> buscarIndicador() {
		// TODO Auto-generated method stub
		return indDao.buscarIndicador();
	}
	
	
	@Override
	public List<Indicador> buscarIndicadorPorId(Long id) {
		// TODO Auto-generated method stub
		return indDao.buscarIndicadorPorId(id);
	}


}
