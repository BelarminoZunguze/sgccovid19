package uem.mz.mint.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.mz.mint.dao.UserRoleDao;
import uem.mz.mint.entity.administracao.UserRole;
import uem.mz.mint.service.UserRoleService;

@Service("userRoleService")
public class UserRoleServiceImpl extends GenericServiceImpl<UserRole> implements
		UserRoleService {

	@Autowired
	private UserRoleDao userRoleDao;

	@Override
	public UserRole findByName(String rollname) {
		return userRoleDao.findByName(rollname);
	}

	@Override
	public UserRole buscarRoleCandidato(String rolename) {
		return userRoleDao.buscarRoleCandidato(rolename);
	}

	@Override
	public List<UserRole> buscarRoleNaoAdminCandidato() {
		return userRoleDao.buscarRoleNaoAdminCandidato();
	}

	@Override
	public List<UserRole> buscarUserRoleSemPermissoes() {
		// TODO Auto-generated method stub
		return userRoleDao.buscarUserRoleSemPermissoes();
	}
	
	

}
