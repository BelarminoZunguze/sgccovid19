package uem.mz.mint.dao.impl;

import javax.persistence.Query;

import uem.mz.mint.dao.RoleDao;
import uem.mz.mint.entity.administracao.Role;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role> implements
		RoleDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public Role findByName(String role) {
		
		Query query = em
				.createQuery("select r from Role r where r.designation = ?");
		query.setParameter(1, role);

		return (Role) DataAccessUtils.singleResult(query.getResultList());
	}

}
