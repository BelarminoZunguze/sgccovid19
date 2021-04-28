package uem.mz.sgccovid19.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.mz.sgccovid19.dao.DelegacaoDao;
import uem.mz.sgccovid19.entity.Delegacao;
import uem.mz.sgccovid19.service.DelegacaoService;

@Service("delegacaoService")
public class DelegacaoServiceImpl extends GenericServiceImpl<Delegacao> implements DelegacaoService{

	@Autowired
	private DelegacaoDao delDao;
	
}
