package uem.mz.sgccovid19.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uem.mz.sgccovid19.dao.FichaMonitoriaRespostasDao;
import uem.mz.sgccovid19.entity.Indicador;
import uem.mz.sgccovid19.entity.monitoria.FichaMonitoriaRespostas;

@Repository
public class FichaMonitoriaRespostasDaoImpl extends GenericDaoImpl<FichaMonitoriaRespostas> implements FichaMonitoriaRespostasDao{
	
	public FichaMonitoriaRespostasDaoImpl() {
		super();
	}

	@Override
	public List<FichaMonitoriaRespostas> buscarFichaMonitoriaRespostas() {
		Query query = getCurrentSession().createQuery("select fich from FichaMonitoriaRespostas fich");
		return query.list();
	}
	
	@Override
	public List<FichaMonitoriaRespostas> buscarFichaMonitoriaRespostasPorId(Long id){
		Query query = getCurrentSession().createQuery("select fich from FichaMonitoriaRespostas fich where fich.fichaMonitoria.id=:id");
		query.setParameter("id", id);
		return query.list();
	}
	
	@Override
	public List<FichaMonitoriaRespostas> buscarFichasPorIndicador(Long id){
		Query query = getCurrentSession().createQuery("select fich from FichaMonitoriaRespostas fich where fich.indicador.id=:id");
		query.setParameter("id", id);
		return query.list();
	}

}
