package uem.mz.mint.service;

import java.util.List;

import uem.mz.mint.entity.Departamento;
import uem.mz.mint.entity.UnidadeOrganica;

public interface DepartamentoService extends GenericService<Departamento>{
	
	public List<Departamento> buscarDepartamento();
	public List<Departamento> buscarDepartamentoPorUnidade(UnidadeOrganica unidOrg);

}
