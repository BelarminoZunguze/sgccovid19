package uem.mz.sgccovid19.dao;

import java.util.List;

import uem.mz.sgccovid19.entity.administracao.UserRole;

public interface UserRoleDao extends GenericDao<UserRole> {

	public UserRole findByName(String rollname);

	public UserRole buscarRoleCandidato(String rolename);
	
	public List<UserRole> buscarRoleNaoAdminCandidato();
	
	public List<UserRole> buscarUserRoleSemPermissoes();

}
