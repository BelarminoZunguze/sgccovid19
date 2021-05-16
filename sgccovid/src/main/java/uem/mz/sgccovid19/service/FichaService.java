package uem.mz.sgccovid19.service;

import java.util.List;

import uem.mz.sgccovid19.entity.Classificacao;
import uem.mz.sgccovid19.entity.Distrito;
import uem.mz.sgccovid19.entity.Ficha;
import uem.mz.sgccovid19.entity.TipoUtente;
import uem.mz.sgccovid19.entity.UnidadeOrganica;

public interface FichaService extends GenericService<Ficha>{
	
	public List<Ficha> buscarFicha();
	
	public List<Ficha> buscarFichas(String numFicha, UnidadeOrganica uniOrg, String genero, Classificacao classific, TipoUtente tipoUte);
	
	public List<Ficha> buscarFichasPorUnidade(UnidadeOrganica uniOrg);
	
	public List<Ficha> buscarFichasPorDistrito(Distrito distrito, UnidadeOrganica uniOrg);
}
