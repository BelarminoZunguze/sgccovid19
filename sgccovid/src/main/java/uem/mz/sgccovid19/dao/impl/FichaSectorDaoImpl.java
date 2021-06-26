package uem.mz.sgccovid19.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uem.mz.sgccovid19.dao.FichaSectorDao;
import uem.mz.sgccovid19.entity.Ficha;
import uem.mz.sgccovid19.entity.FichaSector;

@Repository
public class FichaSectorDaoImpl extends GenericDaoImpl<FichaSector> 
implements FichaSectorDao{
	
	public FichaSectorDaoImpl() {
		super();
	}
	
	@Override
	public List<FichaSector> buscarFichaSector() {
		Query query = getCurrentSession().createQuery("select fisec from FichaSector fisec join fetch fisec.sector sect join fetch sect.departamento dep left join fetch dep.unidade_organica uo");
		return query.list();
	}
	
	@Override
	public List<FichaSector> buscarFichaSectorPorFicha(Ficha fich) {
		Query query = getCurrentSession().createQuery("select fisec from FichaSector fisec join fetch fisec.sector sect join fetch sect.departamento dep left join fetch dep.unidade_organica uo "
				+ "where fisec.ficha=:fich");
		query.setParameter("fich", fich);
		return query.list();
	}

}
