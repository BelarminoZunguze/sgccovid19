package uem.mz.sgccovid19.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.mz.sgccovid19.dao.FichaMonitoriaRespostasDao;
import uem.mz.sgccovid19.entity.monitoria.FichaMonitoriaRespostas;
import uem.mz.sgccovid19.service.FichaMonitoriaRespostasService;

@Service("fichRespostasService")
public class FichaMonitoriaRespostasServiceImpl extends GenericServiceImpl<FichaMonitoriaRespostas> implements FichaMonitoriaRespostasService{
	
	@Autowired
	private FichaMonitoriaRespostasDao fichDao;
	
	@Override
	public List<FichaMonitoriaRespostas> buscarFichaMonitoriaRespostas() {
		// TODO Auto-generated method stub
		return fichDao.buscarFichaMonitoriaRespostas();
	}
	
	@Override
	public List<FichaMonitoriaRespostas> buscarFichaMonitoriaRespostasPorId(Long id) {
		// TODO Auto-generated method stub
		return fichDao.buscarFichaMonitoriaRespostasPorId(id);
	}
	
	@Override
	public List<FichaMonitoriaRespostas> buscarFichasPorIndicador(Long id) {
		// TODO Auto-generated method stub
		return fichDao.buscarFichasPorIndicador(id);
	}

}
