package uem.mz.mint.service;

import java.util.List;

import uem.mz.mint.entity.administracao.User;

public interface UserService extends GenericService<User> {

	public User getUser(String login);
	public String redifinirSenha(User user);
	public List<User> retornarUserNaoCandidato();
	public boolean emailExiste(String email);
	public List<User> procurarUser(String username);
	public User buscarUser(String login);
	public String encriptarSenha(String senha);
	//public User criarUtilizador(User user);
	//public User actualizarUtilizador(User user);
}
