package uem.mz.sgccovid19.entity.monitoria;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import uem.mz.sgccovid19.entity.IdEntity;
import uem.mz.sgccovid19.entity.Indicador;

@Entity
@Table (name = "ficha_monitoria_respostas")
public class FichaMonitoriaRespostas extends IdEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Column (name = "pontoSituacao")
	private boolean pontoSituacao;
	
	@Column (name = "observacoes")
	private String observacoes;
	
	@ManyToOne
	@JoinColumn (name = "indicador_id")
	private Indicador indicador;
	
	@ManyToOne
	@JoinColumn (name = "ficha_monitoria_id")
	private FichaMonitoria fichaMonitoria;

	public boolean isPontoSituacao() {
		return pontoSituacao;
	}

	public void setPontoSituacao(boolean pontoSituacao) {
		this.pontoSituacao = pontoSituacao;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Indicador getIndicador() {
		return indicador;
	}

	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
	}

	public FichaMonitoria getFichaMonitoria() {
		return fichaMonitoria;
	}

	public void setFichaMonitoria(FichaMonitoria fichaMonitoria) {
		this.fichaMonitoria = fichaMonitoria;
	}
	
	
	

}
