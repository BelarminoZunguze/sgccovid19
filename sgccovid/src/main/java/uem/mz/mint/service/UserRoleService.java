package uem.mz.mint.service;

import java.util.List;

import uem.mz.mint.entity.administracao.UserRole;


public interface UserRoleService extends GenericService<UserRole> {

	public UserRole findByName(String rollname);

	public UserRole buscarRoleCandidato(String rolename);
	
	public List<UserRole> buscarRoleNaoAdminCandidato();
	
	public List<UserRole> buscarUserRoleSemPermissoes();

}
