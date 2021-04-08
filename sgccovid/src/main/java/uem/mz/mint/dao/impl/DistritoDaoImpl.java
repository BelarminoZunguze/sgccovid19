package uem.mz.mint.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uem.mz.mint.dao.DistritoDao;
import uem.mz.mint.entity.Distrito;
import uem.mz.mint.entity.Provincia;

@Repository
public class DistritoDaoImpl extends GenericDaoImpl<Distrito> implements DistritoDao{

	@Override
	public List<Distrito> buscarDistritos() {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery("select d from Distrito d join fetch d.provincia p order by d.designacao asc ");
		return query.list();
	}

	@Override
	public List<Distrito> buscarDistritosPorProvincia(Provincia pro) {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery("select d from Distrito d join fetch d.provincia p where p=:pro order by d.designacao asc ");
		query.setParameter("pro", pro);
		return query.list();
	}

}
