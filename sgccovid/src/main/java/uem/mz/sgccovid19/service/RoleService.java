package uem.mz.sgccovid19.service;

import uem.mz.sgccovid19.entity.administracao.Role;

public interface RoleService extends GenericService<Role> {

	public Role findByName(String role);
}
