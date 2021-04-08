package uem.mz.mint.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uem.mz.mint.dao.ModuleDao;
import uem.mz.mint.entity.administracao.Module;

@Repository
public class ModuleDaoImpl extends GenericDaoImpl<Module> implements ModuleDao{

	@Override
	public List<Module> buscarModules() {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery("select g from Module g order by g.designation asc ");
		//query.setMaxResults(15);
		return query.list();
	}

}
