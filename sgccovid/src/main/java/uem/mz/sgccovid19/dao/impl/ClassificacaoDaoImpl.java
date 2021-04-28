package uem.mz.sgccovid19.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uem.mz.sgccovid19.dao.ClassificacaoDao;
import uem.mz.sgccovid19.entity.Classificacao;

@Repository
public class ClassificacaoDaoImpl extends GenericDaoImpl<Classificacao> implements ClassificacaoDao{

	public ClassificacaoDaoImpl() {
		super();
	}


	@Override
	public List<Classificacao> buscarClassificacao() {
		Query query = getCurrentSession().createQuery("select cla from Classificacao cla");
		return query.list();

    }
}
