package uem.mz.sgccovid19.entity;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "actividade")
@Access(AccessType.FIELD)
public class Actividade extends IdEntity{
	
private static final long serialVersionUID = 1L;
	
	@Column(name = "designacao")
	private String designacao;
	
	@ManyToOne
	@JoinColumn (name = "medidas_id")
	private Medidas medidas;

	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "actividade")
	private List<Indicador> indicadores;

	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public List<Indicador> getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}

	public Medidas getMedidas() {
		return medidas;
	}

	public void setMedidas(Medidas medidas) {
		this.medidas = medidas;
	}
	
	
	
	

}
