package uem.mz.sgccovid19.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name = "local_isolamento")
public class LocalIsolamento extends IdEntity{
	
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
