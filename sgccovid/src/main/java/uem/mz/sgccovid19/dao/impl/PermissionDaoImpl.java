package uem.mz.sgccovid19.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import uem.mz.sgccovid19.dao.PermissionDao;
import uem.mz.sgccovid19.entity.administracao.Permission;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

@Repository
public class PermissionDaoImpl extends GenericDaoImpl<Permission> implements
		PermissionDao {

	@SuppressWarnings("unchecked")
	@Override
	public Permission getPermission(String usersPermission) {

		Query query = em
				.createQuery("select p from Permission p where p.permissionname = ?");
		query.setParameter(1, usersPermission);
		
		return (Permission) DataAccessUtils.singleResult(query.getResultList());
	}

	@Override
	public Permission buscarPermissoesPeloNome(String param) {
		Query query = em
				.createQuery("select p from Permission p where p.permissionname = :param");
		query.setParameter("param", "GESTÃO_DE_VALIDACÃO_DE_PRE_REGISTO");
		return (Permission) query.getSingleResult();

	}

	@Override
	public List<Permission> listaPermissoesNaoSelecionadas( Set<Permission> permissions) {
		// TODO Auto-generated method stub
		List<Long> plis = new ArrayList<Long>();
		for( Permission p : permissions){
			plis.add(p.getId());
		}
		
		org.hibernate.Query query = getCurrentSession().createQuery("from Permission p where p.id not in (:plis)");
		query.setParameterList("plis", plis);
		return query.list();
	}
	
	
}
