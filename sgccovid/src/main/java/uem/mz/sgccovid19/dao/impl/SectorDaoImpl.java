package uem.mz.sgccovid19.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uem.mz.sgccovid19.dao.SectorDao;
import uem.mz.sgccovid19.entity.Departamento;
import uem.mz.sgccovid19.entity.Sector;

@Repository
public class SectorDaoImpl extends GenericDaoImpl<Sector> implements SectorDao{
	
	public SectorDaoImpl() {
		super();
	}

	@Override
	public List<Sector> buscarSector() {
		Query query = getCurrentSession().createQuery("select sect from Sector sect");
		return query.list();
	}

	@Override
	public List<Sector> buscarSectorPorDepartamento(Departamento dep) {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery("select sect from Sector sect join fetch "
				+ " sect.departamento dep left join fetch dep.unidade_organica uo left join fetch uo.instituicao inst "
				+ " where dep=:dep order by sect.designacao asc ");
		query.setParameter("dep", dep);
		return query.list();
	}

}
