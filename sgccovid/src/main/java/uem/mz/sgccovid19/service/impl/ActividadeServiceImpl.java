package uem.mz.sgccovid19.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.mz.sgccovid19.dao.ActividadeDao;
import uem.mz.sgccovid19.entity.Actividade;
import uem.mz.sgccovid19.service.ActividadeService;

@Service("actividadeService")
public class ActividadeServiceImpl extends GenericServiceImpl<Actividade> implements ActividadeService{
	
	@Autowired
	private ActividadeDao actDao;
	
	@Override
	public List<Actividade> buscarActividade() {
		// TODO Auto-generated method stub
		return actDao.buscarActividade();
	}

}
