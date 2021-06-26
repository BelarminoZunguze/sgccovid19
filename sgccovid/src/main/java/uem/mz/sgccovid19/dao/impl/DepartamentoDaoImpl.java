package uem.mz.sgccovid19.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uem.mz.sgccovid19.dao.DepartamentoDao;
import uem.mz.sgccovid19.entity.Departamento;
import uem.mz.sgccovid19.entity.UnidadeOrganica;

@Repository
public class DepartamentoDaoImpl extends GenericDaoImpl<Departamento>
implements DepartamentoDao{
	
	public DepartamentoDaoImpl() {
		super();
	}

	@Override
	public List<Departamento> buscarDepartamento() {
		Query query = getCurrentSession().createQuery("select dep from Departamento dep JOIN FETCH dep.unidade_organica uniOrg order by dep.designacao asc ");
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
