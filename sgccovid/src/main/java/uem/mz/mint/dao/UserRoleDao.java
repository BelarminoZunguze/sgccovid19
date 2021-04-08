package uem.mz.mint.dao;

import java.util.List;

import uem.mz.mint.entity.administracao.UserRole;

public interface UserRoleDao extends GenericDao<UserRole> {

	public UserRole findByName(String rollname);

	public UserRole buscarRoleCandidato(String rolename);
	
	public List<UserRole> buscarRoleNaoAdminCandidato();
	
	public List<UserRole> buscarUserRoleSemPermissoes();

}
