package uem.mz.sgccovid19.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uem.mz.sgccovid19.dao.FichaDao;
import uem.mz.sgccovid19.entity.Ficha;

@Repository
public class FichaDaoImpl extends GenericDaoImpl<Ficha> 
implements FichaDao{
	
	public FichaDaoImpl() {
		super();
	}
	
	@Override
	public List<Ficha> buscarFicha() {
		
		Query query = getCurrentSession().createQuery("select fich from Ficha fich JOIN FETCH fich.utente ute "
				+ "LEFT JOIN FETCH fich.classificacao class"
				+ "LEFT JOIN FETCH ute.distrito dis LEFT JOIN FETCH dis.provincia pro"
				+ "LEFT JOIN FETCH ute.tipo_utente tput");
		
		return query.list();
	}

}
