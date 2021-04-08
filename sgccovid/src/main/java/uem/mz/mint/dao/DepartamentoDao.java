package uem.mz.mint.dao;

import java.util.List;

import uem.mz.mint.entity.Departamento;
import uem.mz.mint.entity.UnidadeOrganica;

public interface DepartamentoDao extends GenericDao<Departamento>{
	
	public List<Departamento> buscarDepartamento();
	public List<Departamento> buscarDepartamentoPorUnidade(UnidadeOrganica unidOrg);

}
