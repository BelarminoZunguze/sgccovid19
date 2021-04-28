package uem.mz.sgccovid19.entity;

import java.io.Serializable; 
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table (name = "tipo_utente")
public class TipoUtente extends IdEntity{
	
	private static final long serialVersionUID = 1L;
	
	
	@Column (name = "codigo")
	private String codigo;
	
	
	@Column (name = "designacao")
	private String designacao;
	
	
	
		
	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	


		

}
