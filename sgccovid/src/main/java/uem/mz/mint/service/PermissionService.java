package uem.mz.mint.service;

import java.util.List;
import java.util.Set;

import uem.mz.mint.entity.administracao.Permission;

public interface PermissionService extends GenericService<Permission> {

	public Permission getPermission(String usersPermission);
	public Permission buscarPermissoesPeloNome(String param);
	public List<Permission> listaPermissoesNaoSelecionadas(Set<Permission> permissions);
}
