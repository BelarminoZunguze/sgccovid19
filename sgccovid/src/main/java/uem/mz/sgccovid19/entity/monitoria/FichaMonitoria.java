package uem.mz.sgccovid19.entity.monitoria;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import uem.mz.sgccovid19.entity.IdEntity;

@Entity
@Table (name = "ficha_monitoria")
public class FichaMonitoria extends IdEntity{
	
	private static final long serialVersionUID = 1L;
	
	
	@Column (name = "numero_ficha")
	private String numeroFicha;
	
	@Column (name = "constrangimentos")
	private String constrangimentos;
	
	@Column (name = "desafios")
	private String desafios;
	
	@Column (name = "recomendacoes")
	private String recomendacoes;
	
	@Column (name = "numPessoas")
	private String numPessoas;
	
	@Column (name = "numNiveis")
	private String numNiveis;
	
	@Column (name = "estado")
	private String estado;

	public String getNumeroFicha() {
		return numeroFicha;
	}

	public void setNumeroFicha(String numeroFicha) {
		this.numeroFicha = numeroFicha;
	}

	public String getConstrangimentos() {
		return constrangimentos;
	}

	public void setConstrangimentos(String constrangimentos) {
		this.constrangimentos = constrangimentos;
	}

	public String getNumPessoas() {
		return numPessoas;
	}

	public void setNumPessoas(String numPessoas) {
		this.numPessoas = numPessoas;
	}

	public String getNumNiveis() {
		return numNiveis;
	}

	public void setNumNiveis(String numNiveis) {
		this.numNiveis = numNiveis;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDesafios() {
		return desafios;
	}

	public void setDesafios(String desafios) {
		this.desafios = desafios;
	}

	public String getRecomendacoes() {
		return recomendacoes;
	}

	public void setRecomendacoes(String recomendacoes) {
		this.recomendacoes = recomendacoes;
	}
	
	
	
	
	

}
