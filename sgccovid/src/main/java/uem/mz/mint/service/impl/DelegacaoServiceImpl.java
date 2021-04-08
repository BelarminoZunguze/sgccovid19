package uem.mz.mint.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.mz.mint.dao.DelegacaoDao;
import uem.mz.mint.entity.Delegacao;
import uem.mz.mint.service.DelegacaoService;

@Service("delegacaoService")
public class DelegacaoServiceImpl extends GenericServiceImpl<Delegacao> implements DelegacaoService{

	@Autowired
	private DelegacaoDao delDao;
	
}
