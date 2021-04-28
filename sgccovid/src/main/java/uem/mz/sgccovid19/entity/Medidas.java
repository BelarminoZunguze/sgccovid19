package uem.mz.sgccovid19.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name = "medidas")
public class Medidas extends IdEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "designacao")
	private String designacao;

	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}
	
	


}
