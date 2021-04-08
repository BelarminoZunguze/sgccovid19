package uem.mz.mint.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uem.mz.mint.dao.FichaContactoDao;
import uem.mz.mint.entity.FichaContactoDirecto;

@Repository
public class FichaContactoDaoImpl extends GenericDaoImpl<FichaContactoDirecto> 
implements FichaContactoDao{
	
	public FichaContactoDaoImpl() {
		super();
	}
	
	@Override
	public List<FichaContactoDirecto> buscarFichaContacto() {
		Query query = getCurrentSession().createQuery("select fi from FichaContactoDirecto fi");
		return query.list();
	}
	

}
