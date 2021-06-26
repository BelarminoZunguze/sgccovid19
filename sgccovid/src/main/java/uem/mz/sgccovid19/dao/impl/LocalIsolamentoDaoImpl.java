package uem.mz.sgccovid19.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uem.mz.sgccovid19.dao.LocalIsolamentoDao;
import uem.mz.sgccovid19.entity.LocalIsolamento;

@Repository
public class LocalIsolamentoDaoImpl extends GenericDaoImpl<LocalIsolamento> 
implements LocalIsolamentoDao{
	
	public LocalIsolamentoDaoImpl() {
		super();
	}
	
	@Override
	public List<LocalIsolamento> buscarLocalIsolamento() {
		Query query = getCurrentSession().createQuery("select loc from LocalIsolamento loc");
		return query.list();
	}

}
