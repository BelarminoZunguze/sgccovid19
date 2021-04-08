package uem.mz.mint.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "instituicao")
@Access(AccessType.FIELD)
public class Instituicao extends IdEntity{
	
	
	@Column(name = "designacao", nullable = false,length=120,unique=true)
	private String designacao;
	
	@Column(name = "sigla", nullable = false,length=120,unique=true)
	private String sigla;
	
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

	
	

}
