package uem.mz.sgccovid19.dao;

import java.util.List;
import java.util.Set;

import uem.mz.sgccovid19.entity.administracao.Permission;

public interface PermissionDao extends GenericDao<Permission> {

	public Permission getPermission(String usersPermission);
	public  Permission buscarPermissoesPeloNome(String param);
	public List<Permission> listaPermissoesNaoSelecionadas(Set<Permission> permissions);
	
}
