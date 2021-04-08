package uem.mz.mint.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.mz.mint.dao.InstituicaoDao;
import uem.mz.mint.entity.Instituicao;
import uem.mz.mint.service.InstituicaoService;

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
