package uem.mz.sgccovid19.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uem.mz.sgccovid19.dao.ActividadeDao;
import uem.mz.sgccovid19.entity.Actividade;

@Repository
public class ActividadeDaoImpl extends GenericDaoImpl<Actividade> implements ActividadeDao{
	
	public ActividadeDaoImpl() {
		super();
	}


	@Override
	public List<Actividade> buscarActividade() {
		Query query = getCurrentSession().createQuery("select act from Actividade act JOIN FETCH act.indicadores ind");
		return query.list();

    }

}
