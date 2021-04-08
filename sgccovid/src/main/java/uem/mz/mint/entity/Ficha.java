package uem.mz.mint.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table (name = "ficha_investigacao")
public class Ficha extends IdEntity{
	
	private static final long serialVersionUID = 1L;
	
	
	@Column (name = "numero_ficha")
	private String numeroFicha;
	
	
	@Temporal(TemporalType.DATE)
	@Column (name = "data_teste")
	private Date dataTeste;
	
	@Temporal(TemporalType.DATE)
	@Column (name = "data_notificacao")
	private Date dataNotificacao;
	
	@Column (name = "viagem")
	private boolean viajou;
	
	@Column (name = "estado")
	private String estado;
	
	@Column (name = "proveniencia")
	private String proveniencia;
	
	
	@Column (name = "local_isolamento")
	private String local_isolamento;
	
	@Column (name = "outras_informacoes")
	private String outrasInformacoes;
	
	@Temporal(TemporalType.DATE)
	@Column (name = "data_entrada_pais")
	private Date dataEntradaNoPais;
	
	@Column (name = "ponto_entrada")
	private String pontoEntrada;
	
	@Column (name = "detectadoNoPontoEntrada")
	private boolean detectadoNoPontoEntrada;
	
	@Column (name = "emIsolamento")
	private boolean emIsolamento;
	
	@Column (name = "meio_transporte")
	private String meioTransporte;
	
	
	@Temporal(TemporalType.DATE)
	@Column (name = "data_ultima_unidade")
	private Date dataUltimaVezNaUnidade;
	
	@Temporal(TemporalType.DATE)
	@Column (name = "dat_informa_unidade")
	private Date dataQueInformoUnidade;
	
	
	@ManyToOne
	@JoinColumn (name = "utente_id")
	private Utente utente;
	
	
	@ManyToOne
	@JoinColumn (name = "classificacao_id")
	private Classificacao classificacao;
	
	@ManyToOne
	@JoinColumn (name = "ficha_contacto_directo_id")
	private FichaContactoDirecto fichaContacto;
	
	
	@ManyToOne
	@JoinColumn (name = "distrito_id")
	private Distrito distrito_isolamento;
	

	
	public String getNumeroFicha() {
		return numeroFicha;
	}

	public void setNumeroFicha(String numeroFicha) {
		this.numeroFicha = numeroFicha;
	}
	
	
		
	public String getLocal_isolamento() {
		return local_isolamento;
	}

	public void setLocal_isolamento(String local_isolamento) {
		this.local_isolamento = local_isolamento;
	}

	
	public Date getDataTeste() {
		return dataTeste;
	}
	
	public void setDataTeste(Date dataTeste) {
		this.dataTeste = dataTeste;
	}
	
	public Date getDataNotificacao() {
		return dataNotificacao;
	}
	
	public void setDataNotificacao(Date dataNotificacao) {
		this.dataNotificacao = dataNotificacao;
	}
	
	public boolean isViajou() {
		return viajou;
	}
	
	public void setViajou(boolean viajou) {
		this.viajou = viajou;
	}
	
	public String getProveniencia() {
		return proveniencia;
	}
	
	public void setProveniencia(String proveniencia) {
		this.proveniencia = proveniencia;
	}
	
	public Date getDataEntradaNoPais() {
		return dataEntradaNoPais;
	}
	
	public void setDataEntradaNoPais(Date dataEntradaNoPais) {
		this.dataEntradaNoPais = dataEntradaNoPais;
	}
	
	public String getPontoEntrada() {
		return pontoEntrada;
	}
	
	public void setPontoEntrada(String pontoEntrada) {
		this.pontoEntrada = pontoEntrada;
	}
	
	public boolean isDetectadoNoPontoEntrada() {
		return detectadoNoPontoEntrada;
	}
	
	public void setDetectadoNoPontoEntrada(boolean detectadoNoPontoEntrada) {
		this.detectadoNoPontoEntrada = detectadoNoPontoEntrada;
	}
	
	
	public boolean isEmIsolamento() {
		return emIsolamento;
	}
	public void setEmIsolamento(boolean emIsolamento) {
		this.emIsolamento = emIsolamento;
	}
	public Date getDataUltimaVezNaUnidade() {
		return dataUltimaVezNaUnidade;
	}
	
	public void setDataUltimaVezNaUnidade(Date dataUltimaVezNaUnidade) {
		this.dataUltimaVezNaUnidade = dataUltimaVezNaUnidade;
	}
	
	public Date getDataQueInformoUnidade() {
		return dataQueInformoUnidade;
	}
	public void setDataQueInformoUnidade(Date dataQueInformoUnidade) {
		this.dataQueInformoUnidade = dataQueInformoUnidade;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getOutrasInformacoes() {
		return outrasInformacoes;
	}

	public void setOutrasInformacoes(String outrasInformacoes) {
		this.outrasInformacoes = outrasInformacoes;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Classificacao getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(Classificacao classificacao) {
		this.classificacao = classificacao;
	}

	
	
	public Distrito getDistrito_isolamento() {
		return distrito_isolamento;
	}

	public void setDistrito_isolamento(Distrito distrito_isolamento) {
		this.distrito_isolamento = distrito_isolamento;
	}

	public String getMeioTransporte() {
		return meioTransporte;
	}

	public void setMeioTransporte(String meioTransporte) {
		this.meioTransporte = meioTransporte;
	}

	public FichaContactoDirecto getFichaContacto() {
		return fichaContacto;
	}

	public void setFichaContacto(FichaContactoDirecto fichaContacto) {
		this.fichaContacto = fichaContacto;
	}

	
	


		
}
