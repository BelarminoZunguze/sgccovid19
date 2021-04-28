package uem.mz.sgccovid19.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.zkoss.zk.ui.util.GenericForwardComposer;

@Entity
@Table(name="param_delegacao")
@Access(AccessType.FIELD)
public class Delegacao extends IdEntity{


	private static final long serialVersionUID = 1L;
	
	@Column (name = "designacao")
	private String designacao;
	
	@Column (name = "endereco")
	private String endereco;
	
	@Column (name = "contacto")
	private String contacto;

	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	
	
}
