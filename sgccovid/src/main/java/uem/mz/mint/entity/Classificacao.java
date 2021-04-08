package uem.mz.mint.entity;

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
@Table (name = "classificacao")
public class Classificacao extends IdEntity{
	
	private static final long serialVersionUID = 1L;
	
	
	
	@Column (name = "nome_classificacao", nullable = false)
	private String nome;

	
	public String getNome() {
		return nome;
	}

	
	public void setNome(String nome) {
		this.nome = nome;
	}

	

	
	
	

}
