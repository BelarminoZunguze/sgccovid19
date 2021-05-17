package uem.mz.sgccovid19.dao;

import java.util.Date;
import java.util.List;

import uem.mz.sgccovid19.entity.Classificacao;
import uem.mz.sgccovid19.entity.Distrito;
import uem.mz.sgccovid19.entity.Ficha;
import uem.mz.sgccovid19.entity.TipoUtente;
import uem.mz.sgccovid19.entity.UnidadeOrganica;

public interface FichaDao extends GenericDao<Ficha>{
	
	public List<Ficha> buscarFicha();
	
	public List<Ficha> buscarFichas(String numFicha, UnidadeOrganica uniOrg, String genero, Classificacao classific, TipoUtente tipoUte, Date dataInicio, Date dataFim);
	
	public List<Ficha> buscarFichasPorUnidade(UnidadeOrganica uniOrg);
	
	public List<Ficha> buscarFichasPorDistrito(Distrito distrito, UnidadeOrganica uniOrg, Date dataInicio, Date dataFim);

}
