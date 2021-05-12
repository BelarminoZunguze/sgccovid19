package uem.mz.sgccovid19.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uem.mz.sgccovid19.entity.UnidadeOrganica;
import uem.mz.sgccovid19.dao.FichaDao;
import uem.mz.sgccovid19.entity.Classificacao;
import uem.mz.sgccovid19.entity.Ficha;
import uem.mz.sgccovid19.entity.TipoUtente;

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
	
	@Override
	public List<Ficha> buscarFichas(String numFicha, UnidadeOrganica uniOrg, String genero, Classificacao classific, TipoUtente tipoUte) {
		String ParamnumFicha = numFicha=="" ? "fich.numeroFicha is not null" : "fich.numeroFicha=:numFicha";
		String Paramunidade = uniOrg==null ? "" : "and ute.unidade=:uniOrg";
		String Paramgenero = genero==null ? "" : "and ute.genero=:genero";
		String Paramclass = classific==null ? "" : "and fich.classificacao=:classific";
		String Paramtipo = tipoUte==null ? "" : "and tput=:tipoUte";
		
		Query query = getCurrentSession().createQuery("select fich from Ficha fich JOIN FETCH fich.utente ute "
				+ "LEFT JOIN FETCH fich.classificacao cla"
				+ "LEFT JOIN FETCH ute.distrito dis LEFT JOIN FETCH dis.provincia pro"
				+ "LEFT JOIN FETCH ute.tipo_utente tput where "+ParamnumFicha+" "+Paramunidade+" "+Paramgenero+" "+Paramclass+" "+Paramtipo);
		if(numFicha!=""){query.setParameter("numFicha", numFicha);}
		if(uniOrg!=null){query.setParameter("uniOrg", uniOrg);}
		if(genero!=null){query.setParameter("genero", genero);}
		if(classific!=null){query.setParameter("classific", classific);}
		if(tipoUte!=null){query.setParameter("tipoUte", tipoUte);}
		return query.list();
	}
	
	@Override
	public List<Ficha> buscarFichasPorUnidade(UnidadeOrganica uniOrg) {
		
		Query query = getCurrentSession().createQuery("select fich from Ficha fich JOIN FETCH fich.utente ute "
				+ "LEFT JOIN FETCH fich.classificacao cla"
				+ "LEFT JOIN FETCH ute.distrito dis LEFT JOIN FETCH dis.provincia pro"
				+ "LEFT JOIN FETCH ute.tipo_utente tput where ute.unidade=:uniOrg");
		if(uniOrg!=null){query.setParameter("uniOrg", uniOrg);}
		return query.list();
	}

}
