package uem.mz.sgccovid19.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.mz.sgccovid19.dao.RoleDao;
import uem.mz.sgccovid19.entity.administracao.Role;
import uem.mz.sgccovid19.service.RoleService;

@Service("roleService")
public class RoleServiceImpl extends GenericServiceImpl<Role>
		implements RoleService {
	
	@Autowired
	private RoleDao roleDao;

	@Override
	public Role findByName(String role) {
		// TODO Auto-generated method stub
		return roleDao.findByName(role);
	}

}
