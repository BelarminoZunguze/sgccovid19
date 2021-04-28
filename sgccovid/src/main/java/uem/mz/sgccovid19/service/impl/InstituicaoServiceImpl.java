package uem.mz.sgccovid19.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.mz.sgccovid19.dao.InstituicaoDao;
import uem.mz.sgccovid19.entity.Instituicao;
import uem.mz.sgccovid19.service.InstituicaoService;

@Service("instituicaoService")
public class InstituicaoServiceImpl extends GenericServiceImpl<Instituicao> implements InstituicaoService{

	@Autowired
	private InstituicaoDao insDao;
	
	@Override
	public List<Instituicao> buscarInstituicao() {
		// TODO Auto-generated method stub
		return insDao.buscarInstituicao();
	}

}
