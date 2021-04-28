package uem.mz.sgccovid19.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uem.mz.sgccovid19.dao.IndicadorDao;
import uem.mz.sgccovid19.entity.Indicador;

@Repository
public class IndicadorDaoImpl extends GenericDaoImpl<Indicador> implements IndicadorDao{
	
	public IndicadorDaoImpl() {
		super();
	}

	@Override
	public List<Indicador> buscarIndicador() {
		Query query = getCurrentSession().createQuery("select ind from Indicador ind JOIN FETCH ind.actividade act");
		return query.list();
	}
	
	@Override
	public List<Indicador> buscarIndicadorPorId(Long id){
		Query query = getCurrentSession().createQuery("select ind from Indicador ind "
				+ "JOIN FETCH ind.actividade act where act.medidas.id=:id");
		query.setParameter("id", id);
		return query.list();
	}

}
