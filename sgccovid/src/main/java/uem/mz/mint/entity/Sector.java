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
@Table(name = "sector")
@Access(AccessType.FIELD)
public class Sector extends IdEntity{
	
	@Column(name = "codigo", nullable = false,length=30,unique=true)
	private String codigo;
	
	@Column(name = "designacao", nullable = false,length=120,unique=true)
	private String designacao;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="departamento")
	private Departamento departamento;


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


	public Departamento getDepartamento() {
		return departamento;
	}


	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	
	
	

}
