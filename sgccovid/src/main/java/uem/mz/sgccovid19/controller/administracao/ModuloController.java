package uem.mz.sgccovid19.controller.administracao;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zhtml.Button;
import org.zkoss.zhtml.Table;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

import uem.mz.sgccovid19.entity.administracao.Module;
import uem.mz.sgccovid19.service.ModuleService;


public class ModuloController extends GenericForwardComposer {
	
	List<Module> moduloList = new ArrayList<Module>();

	@WireVariable
	ModuleService modSer;

	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		modSer = (ModuleService) SpringUtil.getBean("moduleService");
		moduloList = modSer.getAll();
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
	}

	public List<Module> getModuloList() {
		return moduloList;
	}

	public void setModuloList(List<Module> moduloList) {
		this.moduloList = moduloList;
	}
	

}
