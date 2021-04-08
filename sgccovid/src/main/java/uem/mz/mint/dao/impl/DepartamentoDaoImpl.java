package uem.mz.mint.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uem.mz.mint.dao.DepartamentoDao;
import uem.mz.mint.entity.Departamento;
import uem.mz.mint.entity.UnidadeOrganica;

@Repository
public class DepartamentoDaoImpl extends GenericDaoImpl<Departamento>
implements DepartamentoDao{
	
	public DepartamentoDaoImpl() {
		super();
	}

	@Override
	public List<Departamento> buscarDepartamento() {
		Query query = getCurrentSession().createQuery("select dep from Departamento dep JOIN FETCH dep.provincia prov "
				+ " left join fetch dep.unidade_organica uo left join fetch uo.instituicao inst order by dep.designacao asc ");
		return query.list();
	}

	@Override
	public List<Departamento> buscarDepartamentoPorUnidade(
			UnidadeOrganica unidOrg) {
		Query query = getCurrentSession().createQuery("select dep from Departamento dep JOIN FETCH dep.unidade_organica uniOrg where uniOrg =:unidade_organica");
		query.setParameter("unidade_organica", unidOrg);

		
		return  query.list();
	}

}
