package uem.mz.mint.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.mz.mint.dao.DepartamentoDao;
import uem.mz.mint.entity.Departamento;
import uem.mz.mint.entity.UnidadeOrganica;
import uem.mz.mint.service.DepartamentoService;

@Service("departamentoService")
public class DepartamentoServiceImpl extends GenericServiceImpl<Departamento> implements DepartamentoService{

	@Autowired
	private DepartamentoDao depDao;
	
	@Override
	public List<Departamento> buscarDepartamento() {
		// TODO Auto-generated method stub
		return depDao.buscarDepartamento();
	}

	@Override
	public List<Departamento> buscarDepartamentoPorUnidade(
			UnidadeOrganica unidOrg) {
		// TODO Auto-generated method stub
		return depDao.buscarDepartamentoPorUnidade(unidOrg);
	}

}
