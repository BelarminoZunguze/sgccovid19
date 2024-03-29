package uem.mz.sgccovid19.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.mz.sgccovid19.dao.FichaMonitoriaDao;
import uem.mz.sgccovid19.entity.UnidadeOrganica;
import uem.mz.sgccovid19.entity.monitoria.FichaMonitoria;
import uem.mz.sgccovid19.service.FichaMonitoriaService;

@Service("fichaMonitoriaService")
public class FichaMonitoriaServiceImpl extends GenericServiceImpl<FichaMonitoria> implements FichaMonitoriaService{
	
	@Autowired
	private FichaMonitoriaDao fichDao;
	
	@Override
	public List<FichaMonitoria> buscarFichaMonitoria() {
		// TODO Auto-generated method stub
		return fichDao.buscarFichaMonitoria();
	}
	
	@Override
	public List<FichaMonitoria> buscarFichasMonPorUnidade(UnidadeOrganica uniOrg) {
		// TODO Auto-generated method stub
		return fichDao.buscarFichasMonPorUnidade(uniOrg);
	}
	
	@Override
	public List<FichaMonitoria> buscarFichaMonitoria(String numFicha, UnidadeOrganica uniOrg) {
		// TODO Auto-generated method stub
		return fichDao.buscarFichaMonitoria(numFicha, uniOrg);
	}
	
	

}
