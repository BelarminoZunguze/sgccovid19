package uem.mz.sgccovid19.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "indicador")
public class Indicador extends IdEntity{
	
private static final long serialVersionUID = 1L;
	
	@Column(name = "designacao")
	private String designacao;
	
	@ManyToOne
	@JoinColumn (name = "actividade_id")
	private Actividade actividade;

	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public Actividade getActividade() {
		return actividade;
	}

	public void setActividade(Actividade actividade) {
		this.actividade = actividade;
	}
	
	


}
