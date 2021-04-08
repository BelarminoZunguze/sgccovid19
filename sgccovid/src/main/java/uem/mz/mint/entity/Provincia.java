package uem.mz.mint.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "provincia")
@Access(AccessType.FIELD)
public class Provincia extends IdEntity{
	
	@Column(name = "designacao", nullable = false,length=120,unique=true)
	private String designacao;
	
	@Column(name = "situacao", nullable = false)
	private boolean situacao;


	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public boolean isSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}
	
	
}
