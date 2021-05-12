package uem.mz.sgccovid19.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uem.mz.sgccovid19.dao.FichaMonitoriaDao;
import uem.mz.sgccovid19.entity.UnidadeOrganica;
import uem.mz.sgccovid19.entity.monitoria.FichaMonitoria;

@Repository
public class FichaMonitoriaDaoImpl extends GenericDaoImpl<FichaMonitoria> 
implements FichaMonitoriaDao{
	
	public FichaMonitoriaDaoImpl() {
		super();
	}


	@Override
	public List<FichaMonitoria> buscarFichaMonitoria() {
		Query query = getCurrentSession().createQuery("select fich from FichaMonitoria fich");
		return query.list();
	}
	
	@Override
	public List<FichaMonitoria> buscarFichasMonPorUnidade(UnidadeOrganica uniOrg) {
		Query query = getCurrentSession().createQuery("select fich from FichaMonitoria fich where fich.unidade=:uniOrg");
		if(uniOrg!=null){query.setParameter("uniOrg", uniOrg);}
		return query.list();
	}


}
