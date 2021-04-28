package uem.mz.sgccovid19.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table (name = "utente")
public class Utente extends IdEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Column (name = "nome")
	private String nome;
	
	@Column (name = "email")
	private String email;
	
	@Column (name = "contacto")
	private String contacto;
	
	@Column (name = "endereco")
	private String endereco;
	
	
	@Column (name = "genero")
	private String genero;
	
	
	@Column (name = "nacionalidade")
	private String nacionalidade;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento")
	private Date dataNascimento;
	
	@ManyToOne
	@JoinColumn (name = "tipo_utente_id")
	private TipoUtente tipo_utente;
	
	
	@ManyToOne
	@JoinColumn (name = "unidade_organica")
	private UnidadeOrganica unidade;
	
	@ManyToOne 
	@JoinColumn (name = "distrito_id")
	private Distrito distrito;
	
	@OneToMany (mappedBy = "utente")
	private List<Ficha>  ficha;
	

	
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

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	

	

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	
	
	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public TipoUtente getTipo_utente() {
		return tipo_utente;
	}


	public void setTipo_utente(TipoUtente tipo_utente) {
		this.tipo_utente = tipo_utente;
	}


	public UnidadeOrganica getUnidade() {
		return unidade;
	}


	public void setUnidade(UnidadeOrganica unidade) {
		this.unidade = unidade;
	}


	
	public List<Ficha> getFicha() {
		return ficha;
	}


	public void setFicha(List<Ficha> ficha) {
		this.ficha = ficha;
	}


	



}
