package uem.mz.mint.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uem.mz.mint.dao.FichaDao;
import uem.mz.mint.entity.Ficha;

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
				+ "LEFT JOIN FETCH fich.fichaContacto fichcontac"
				+ "LEFT JOIN FETCH fich.distrito_isolamento disIso LEFT JOIN FETCH disIso.provincia proIso"
				+ "LEFT JOIN FETCH ute.distrito dis LEFT JOIN FETCH dis.provincia pro"
				+ "LEFT JOIN FETCH ute.tipo_utente tput");
		
		return query.list();
	}

}
