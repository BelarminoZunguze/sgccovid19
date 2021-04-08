package uem.mz.mint.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class IdEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "created", nullable = false)
	protected Date created;

	@Temporal(TemporalType.DATE)
	@Column(name = "updated", nullable = false)
	protected Date updated;
	
	protected Long userCreated;
	
	protected Long userUpdated;

	public IdEntity(){
		super();
		created = updated=new Date();
	}
	
	@PrePersist
	public void onCreate(){
		updated = created = new Date();
	}
	
	@PreUpdate
	public void onUpdate(){
		updated = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Long getUserCreated() {
		return userCreated;
	}

	public void setUserCreated(Long userCreated) {
		this.userCreated = userCreated;
	}

	public Long getUserUpdated() {
		return userUpdated;
	}

	public void setUserUpdated(Long userUpdated) {
		this.userUpdated = userUpdated;
	}

	@Override
	public String toString() {
		return String.format("%s(id=%d)", this.getClass().getSimpleName(),
				this.getId());
	}
	
	
}
