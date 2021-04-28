package uem.mz.sgccovid19.entity.administracao;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import uem.mz.sgccovid19.entity.IdEntity;

@Entity
@Table (name="module")
@Access (AccessType.FIELD)
public class Module extends IdEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "designation", columnDefinition = "TEXT")
	private String designation;
	
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
