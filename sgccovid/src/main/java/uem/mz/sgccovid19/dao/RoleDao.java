package uem.mz.sgccovid19.dao;

import uem.mz.sgccovid19.entity.administracao.Role;

public interface RoleDao extends GenericDao<Role> {

	public Role findByName(String role);
}
