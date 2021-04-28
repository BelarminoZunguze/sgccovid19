package uem.mz.sgccovid19.dao.impl;

import java.util.List;

import uem.mz.sgccovid19.dao.UserRoleDao;
import uem.mz.sgccovid19.entity.administracao.UserRole;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleDaoImpl extends GenericDaoImpl<UserRole> implements
		UserRoleDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRole> getAll() {
		Query query = getCurrentSession().createQuery("from UserRole ur where ur.type = 'Normal'");
		return query.list();
	}

	@Override
	public UserRole findByName(String rolename) {
		
		Query query = getCurrentSession().createQuery("from UserRole ur where ur.rolename = ?");
		query.setParameter(1, rolename);
		return (UserRole) query.uniqueResult();
	}

	public UserRole buscarRoleCandidato(String rolename) {
		
		Query query = getCurrentSession().createQuery("select ur from UserRole ur where ur.rolename = :rolename");
		query.setParameter("rolename", rolename);
		return (UserRole) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRole> buscarRoleNaoAdminCandidato() {
		Query query = getCurrentSession().createQuery("select ur from UserRole ur where ur.rolename <> 'Admin' and ur.rolename <> 'Candidato'");
		return query.list();
	}

	//terminar a query que busca perfis nao configurados
	@Override
	public List<UserRole> buscarUserRoleSemPermissoes() {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery("select ur from UserRole ur where ur.permissions");
		return query.list();
	}
}
