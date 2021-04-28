package uem.mz.sgccovid19.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uem.mz.sgccovid19.dao.TipoUtenteDao;
import uem.mz.sgccovid19.dao.UtenteDao;
import uem.mz.sgccovid19.entity.TipoUtente;
import uem.mz.sgccovid19.entity.Utente;

@Repository
public class TipoUtenteDaoImpl extends GenericDaoImpl<TipoUtente> 
implements TipoUtenteDao{
	
	public TipoUtenteDaoImpl() {
		super();
	}
	
	@Override
	public List<TipoUtente> buscarTipoUtente() {
		Query query = getCurrentSession().createQuery("select tiput from TipoUtente tiput");
		return query.list();
	}
	

}
