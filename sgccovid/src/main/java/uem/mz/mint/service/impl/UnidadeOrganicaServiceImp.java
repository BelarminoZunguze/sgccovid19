package uem.mz.mint.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.mz.mint.dao.UnidadeOrganicaDao;
import uem.mz.mint.entity.Instituicao;
import uem.mz.mint.entity.UnidadeOrganica;
import uem.mz.mint.service.UnidadeOrganicaService;

@Service("unidadeOrganicaService")
public class UnidadeOrganicaServiceImp extends GenericServiceImpl<UnidadeOrganica> 
									implements UnidadeOrganicaService{
	
	@Autowired
	private UnidadeOrganicaDao uniOrgDao;

	@Override
	public List<UnidadeOrganica> buscarUnidadeOrganica() {
		// TODO Auto-generated method stub
		return uniOrgDao.buscarUnidadeOrganica();
	}

	@Override
	public List<UnidadeOrganica> buscarUnidadePorInst(Instituicao inst) {
		// TODO Auto-generated method stub
		return uniOrgDao.buscarUnidadePorInst(inst);
	}
	

}
