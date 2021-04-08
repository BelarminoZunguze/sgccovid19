package uem.mz.mint.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uem.mz.mint.dao.TipoUtenteDao;
import uem.mz.mint.dao.UtenteDao;
import uem.mz.mint.entity.TipoUtente;
import uem.mz.mint.entity.Utente;

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
