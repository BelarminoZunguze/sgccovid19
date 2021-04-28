package uem.mz.sgccovid19.service;

import java.util.List;

import uem.mz.sgccovid19.entity.Departamento;
import uem.mz.sgccovid19.entity.UnidadeOrganica;

public interface DepartamentoService extends GenericService<Departamento>{
	
	public List<Departamento> buscarDepartamento();
	public List<Departamento> buscarDepartamentoPorUnidade(UnidadeOrganica unidOrg);

}
