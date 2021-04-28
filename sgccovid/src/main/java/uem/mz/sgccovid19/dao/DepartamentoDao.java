package uem.mz.sgccovid19.dao;

import java.util.List;

import uem.mz.sgccovid19.entity.Departamento;
import uem.mz.sgccovid19.entity.UnidadeOrganica;

public interface DepartamentoDao extends GenericDao<Departamento>{
	
	public List<Departamento> buscarDepartamento();
	public List<Departamento> buscarDepartamentoPorUnidade(UnidadeOrganica unidOrg);

}
