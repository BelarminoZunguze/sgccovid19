package uem.mz.mint.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uem.mz.mint.dao.UtenteDao;
import uem.mz.mint.entity.Utente;

@Repository
public class UtenteDaoImpl extends GenericDaoImpl<Utente> 
implements UtenteDao{
	
	public UtenteDaoImpl() {
		super();
	}
	
	@Override
	public List<Utente> buscarUtente() {
		Query query = getCurrentSession().createQuery("select ut from Utente ut");
		return query.list();
	}
	

}
