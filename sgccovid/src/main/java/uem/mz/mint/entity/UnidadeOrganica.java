package uem.mz.mint.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "unidade_organica")
@Access(AccessType.FIELD)
public class UnidadeOrganica extends IdEntity{
	
	@Column(name = "codigo", nullable = false,length=30,unique=true)
	private String codigo;
	
	@Column(name = "designacao", nullable = false,length=120,unique=true)
	private String designacao;
	
	@Column(name = "sigla", nullable = false,length=120,unique=true)
	private String sigla;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="instituicao")
	private Instituicao instituicao;


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getDesignacao() {
		return designacao;
	}


	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}


	public String getSigla() {
		return sigla;
	}


	public void setSigla(String sigla) {
		this.sigla = sigla;
	}


	public Instituicao getInstituicao() {
		return instituicao;
	}


	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}
	
	
	

}
