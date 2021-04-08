package uem.mz.mint.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uem.mz.mint.dao.ProvinciaDao;
import uem.mz.mint.entity.Provincia;

@Repository
public class ProvinciaDaoImpl extends GenericDaoImpl<Provincia> implements ProvinciaDao{
	
	public ProvinciaDaoImpl() {
		super();
	}

	@Override
	public List<Provincia> buscarProvincia() {
		Query query = getCurrentSession().createQuery("select prov from Provincia prov order by prov.designacao asc ");
		return query.list();
	}

}
