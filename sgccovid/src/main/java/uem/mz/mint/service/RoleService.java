package uem.mz.mint.service;

import uem.mz.mint.entity.administracao.Role;

public interface RoleService extends GenericService<Role> {

	public Role findByName(String role);
}
