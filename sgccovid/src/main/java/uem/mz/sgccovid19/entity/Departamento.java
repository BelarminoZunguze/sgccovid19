package uem.mz.sgccovid19.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "departamento")
@Access(AccessType.FIELD)
public class Departamento extends IdEntity{
	
	@Column(name = "codigo", nullable = false,length=30,unique=true)
	private String codigo;
	
	@Column(name = "designacao", nullable = false,length=120,unique=true)
	private String designacao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="provincia")
	private Provincia provincia;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="unidade_organica")
	private UnidadeOrganica unidade_organica;

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

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public UnidadeOrganica getUnidade_organica() {
		return unidade_organica;
	}

	public void setUnidade_organica(UnidadeOrganica unidade_organica) {
		this.unidade_organica = unidade_organica;
	}
	
	

}
