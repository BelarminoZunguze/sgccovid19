package uem.mz.sgccovid19.entity.administracao;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.google.common.base.Objects;

import uem.mz.sgccovid19.entity.Delegacao;
import uem.mz.sgccovid19.entity.IdEntity;
import uem.mz.sgccovid19.entity.UnidadeOrganica;

@Entity
@Table(name = "users")
public class User extends IdEntity implements UserDetails {

	private static final long serialVersionUID = 6311364761937265306L;

	@NotNull(message = "Utilizador nao pode ser vazio")
	@NotEmpty(message = "Utilizador nao pode conter apenas espacos em branco")
	@Size(max = 50, message = "Utilizador não deve ter mais de 50 caracteres")
	@Column(name = "username", length = 50, unique = true)
	private String username;

	@NotNull(message = "Senha nao pode ser vazia")
	@NotEmpty(message = "Senha nao pode conter apenas espaços em branco")
	@Column(columnDefinition = "LONGTEXT")
	private String password;

	@Column(name = "enabled")
	private boolean enabled;
	
	@Transient
	private String planPass = "";

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", nullable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false, updatable = false))
	private Set<UserRole> roles = new HashSet<UserRole>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_delegacao", joinColumns = @JoinColumn(name = "user_id", nullable = false, updatable = true), inverseJoinColumns = @JoinColumn(name = "delegacao_id", nullable = false, updatable = true))
	private Set<Delegacao> userDelegacao = new HashSet<Delegacao>();
	
	@ManyToOne
	@JoinColumn (name = "unidade_organica")
	private UnidadeOrganica unidade;
	
	@Column (name = "nome")
	private String nome;
	
	@Column (name = "contacto")
	private String contacto;
	
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public User() {
		this.enabled = true;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}

	
	
	public UnidadeOrganica getUnidade() {
		return unidade;
	}

	public void setUnidade(UnidadeOrganica unidade) {
		this.unidade = unidade;
	}

	public String getPlanPass() {
		return planPass;
	}

	public void setPlanPass(String planPass) {
		this.planPass = planPass;
	}

	@Override
	public String toString() {
		return String.format("%s(id=%d, username=%s, password=%s, enabled=%b)",
				this.getClass().getSimpleName(), this.getId(),
				this.getUsername(), this.getPassword(), this.getEnabled());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;

		if (o instanceof User) {
			final User other = (User) o;
			return Objects.equal(getId(), other.getId())
					&& Objects.equal(getUsername(), other.getUsername())
					&& Objects.equal(getPassword(), other.getPassword())
					&& Objects.equal(getEnabled(), other.getEnabled());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId(), getUsername(), getPassword(),
				getEnabled());
	}

	@Transient
	public Set<Permission> getPermissions() {
		Set<Permission> perms = new HashSet<Permission>();
		for (UserRole role : roles) {
			perms.addAll(role.getPermissions());
		}
		return perms;
	}

	@Override
	@Transient
	public Collection<GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.addAll(getRoles());
		authorities.addAll(getPermissions());
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		// return true = account is valid / not expired
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// return true = account is not locked
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// return true = password is valid / not expired
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.getEnabled();
	}
	
	public void SetPasswordEncripted(String senha) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		this.password = passwordEncoder.encode(senha);
		
	}
	
	
	public String gerarSenhaAleatoria() {
		  int max = 8;
		  String[] caracteres = { "a", "1", "b", "2", "4", "5", "6", "7", "8",
		    "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
		    "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
		    "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I",
		    "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
		    "V", "W", "X", "Y", "Z" };

		  StringBuilder senha = new StringBuilder();

		  for (int i = 0; i < max; i++) {
		   int posicao = (int) (Math.random() * caracteres.length);
		   senha.append(caracteres[posicao]);
		  }

		  return senha.toString();
	}

	public Set<Delegacao> getUserDelegacao() {
		return userDelegacao;
	}

	public void setUserDelegacao(Set<Delegacao> userDelegacao) {
		this.userDelegacao = userDelegacao;
	}
	
}