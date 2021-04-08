package uem.mz.mint.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.mz.mint.dao.PermissionDao;
import uem.mz.mint.entity.administracao.Permission;
import uem.mz.mint.service.PermissionService;

@Service("permissionService")
public class PermissionServiceImpl extends GenericServiceImpl<Permission>
		implements PermissionService {

	@Autowired
	private PermissionDao permissionDao;

	@Override
	public Permission getPermission(String usersPermission) {
		// TODO Auto-generated method stub
		return permissionDao.getPermission(usersPermission);
	}

	public Permission buscarPermissoesPeloNome(String param) {
		return permissionDao.buscarPermissoesPeloNome(param);
	}
	
	public List<Permission> listaPermissoesNaoSelecionadas(Set<Permission> per){
		
		return permissionDao.listaPermissoesNaoSelecionadas(per);
	}

}
