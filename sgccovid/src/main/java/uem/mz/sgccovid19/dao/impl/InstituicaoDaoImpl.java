package uem.mz.sgccovid19.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uem.mz.sgccovid19.dao.InstituicaoDao;
import uem.mz.sgccovid19.entity.Instituicao;

@Repository
public class InstituicaoDaoImpl extends GenericDaoImpl<Instituicao> 
					implements InstituicaoDao{
	public InstituicaoDaoImpl() {
		super();
	}


	@Override
	public List<Instituicao> buscarInstituicao() {
		Query query = getCurrentSession().createQuery("select inst from Instituicao inst order by inst.designacao asc ");
		return query.list();
	}
	
	

}
