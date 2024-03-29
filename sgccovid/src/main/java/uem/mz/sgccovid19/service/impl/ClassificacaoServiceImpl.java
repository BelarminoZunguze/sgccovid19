package uem.mz.sgccovid19.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.mz.sgccovid19.dao.ClassificacaoDao;
import uem.mz.sgccovid19.entity.Classificacao;
import uem.mz.sgccovid19.service.ClassificacaoService;

@Service("classificacaoService")
public class ClassificacaoServiceImpl extends GenericServiceImpl<Classificacao> implements ClassificacaoService{
	
	@Autowired
	private ClassificacaoDao classDao;
	
	@Override
	public List<Classificacao> buscarClassificacao() {
		// TODO Auto-generated method stub
		return classDao.buscarClassificacao();
	}


}
