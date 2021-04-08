package uem.mz.mint.dao;

import uem.mz.mint.entity.administracao.Role;

public interface RoleDao extends GenericDao<Role> {

	public Role findByName(String role);
}
