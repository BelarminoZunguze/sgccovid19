package uem.mz.sgccovid19.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "ficha_contacto_directo")
public class FichaContactoDirecto extends IdEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Column (name = "teve_contacto_dentro")
	private boolean TeveContactoDentro;
	
	@ManyToOne
	@JoinColumn(name = "departamento")
	private Departamento departamentoDentro;
	
	@ManyToOne
	@JoinColumn(name = "sector")
	private Sector sectorDentro;
	
	@Column(name = "outros_espacos_dentro")
	private String outrosEspacosDentro;
	
	
	@Column (name = "teve_contacto_fora")
	private boolean TeveContactoFora;
	
	@ManyToOne
	@JoinColumn(name = "unidade_organica")
	private UnidadeOrganica unidadeFora;
	
	@ManyToOne
	@JoinColumn(name = "departamento_id")
	private Departamento departamentoFora;
	
	@ManyToOne
	@JoinColumn(name = "sector_id")
	private Sector sectorFora;

	@Column(name = "outros_espacos_fora")
	private String outrosEspacosFora;

	public boolean isTeveContactoDentro() {
		return TeveContactoDentro;
	}

	public void setTeveContactoDentro(boolean teveContactoDentro) {
		TeveContactoDentro = teveContactoDentro;
	}

	public Departamento getDepartamentoDentro() {
		return departamentoDentro;
	}

	public void setDepartamentoDentro(Departamento departamentoDentro) {
		this.departamentoDentro = departamentoDentro;
	}

	public Sector getSectorDentro() {
		return sectorDentro;
	}

	public void setSectorDentro(Sector sectorDentro) {
		this.sectorDentro = sectorDentro;
	}

	public String getOutrosEspacosDentro() {
		return outrosEspacosDentro;
	}

	public void setOutrosEspacosDentro(String outrosEspacosDentro) {
		this.outrosEspacosDentro = outrosEspacosDentro;
	}

	public boolean isTeveContactoFora() {
		return TeveContactoFora;
	}

	public void setTeveContactoFora(boolean teveContactoFora) {
		TeveContactoFora = teveContactoFora;
	}

	public UnidadeOrganica getUnidadeFora() {
		return unidadeFora;
	}

	public void setUnidadeFora(UnidadeOrganica unidadeFora) {
		this.unidadeFora = unidadeFora;
	}

	public Departamento getDepartamentoFora() {
		return departamentoFora;
	}

	public void setDepartamentoFora(Departamento departamentoFora) {
		this.departamentoFora = departamentoFora;
	}

	public Sector getSectorFora() {
		return sectorFora;
	}

	public void setSectorFora(Sector sectorFora) {
		this.sectorFora = sectorFora;
	}

	public String getOutrosEspacosFora() {
		return outrosEspacosFora;
	}

	public void setOutrosEspacosFora(String outrosEspacosFora) {
		this.outrosEspacosFora = outrosEspacosFora;
	}

	

	
	
	

}
