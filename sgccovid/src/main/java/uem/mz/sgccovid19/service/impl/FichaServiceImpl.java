package uem.mz.sgccovid19.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.mz.sgccovid19.dao.FichaDao;
import uem.mz.sgccovid19.entity.Classificacao;
import uem.mz.sgccovid19.entity.Ficha;
import uem.mz.sgccovid19.entity.TipoUtente;
import uem.mz.sgccovid19.entity.UnidadeOrganica;
import uem.mz.sgccovid19.service.FichaService;

@Service("fichaService")
public class FichaServiceImpl extends GenericServiceImpl<Ficha> implements FichaService{
	
	@Autowired
	private FichaDao fichDao;
	
	@Override
	public List<Ficha> buscarFicha() {
		// TODO Auto-generated method stub
		return fichDao.buscarFicha();
	}
	
	@Override
	public List<Ficha> buscarFichas(String numFicha, UnidadeOrganica uniOrg, String genero, Classificacao classific, TipoUtente tipoUte) {
		// TODO Auto-generated method stub
		return fichDao.buscarFichas(numFicha, uniOrg, genero, classific, tipoUte);
	}
	
	@Override
	public List<Ficha> buscarFichasPorUnidade(UnidadeOrganica uniOrg) {
		// TODO Auto-generated method stub
		return fichDao.buscarFichasPorUnidade(uniOrg);
	}

}
