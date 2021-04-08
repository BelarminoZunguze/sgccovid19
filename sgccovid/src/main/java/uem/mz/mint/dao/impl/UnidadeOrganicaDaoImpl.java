package uem.mz.mint.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uem.mz.mint.dao.UnidadeOrganicaDao;
import uem.mz.mint.entity.Instituicao;
import uem.mz.mint.entity.UnidadeOrganica;

@Repository
public class UnidadeOrganicaDaoImpl extends GenericDaoImpl<UnidadeOrganica> 
					implements UnidadeOrganicaDao{
	public UnidadeOrganicaDaoImpl() {
		super();
	}

	@Override
	public List<UnidadeOrganica> buscarUnidadeOrganica() {
		Query query = getCurrentSession().createQuery("select uo from UnidadeOrganica uo join fetch "
				+ " uo.instituicao inst order by uo.designacao asc ");
		return query.list();
	}

	@Override
	public List<UnidadeOrganica> buscarUnidadePorInst(Instituicao inst) {
		Query query = getCurrentSession().createQuery("select uo from UnidadeOrganica uo JOIN FETCH uo.instituicao inst where inst =:instituicao");
		query.setParameter("instituicao", inst);

		
		return  query.list();
	}

}
