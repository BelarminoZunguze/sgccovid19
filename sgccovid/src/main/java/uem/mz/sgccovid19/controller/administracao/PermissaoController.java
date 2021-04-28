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
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

import uem.mz.sgccovid19.entity.administracao.Permission;
import uem.mz.sgccovid19.entity.administracao.User;
import uem.mz.sgccovid19.service.PermissionService;


public class PermissaoController extends GenericForwardComposer {

	private Table table;
	private AnnotateDataBinder binder;
	private Listbox lbx_permissoes;
	private Button btnGravar;
	private Textbox tbxDescricao, tbxPermissao;
	private User user;
	
	List<Permission> perfilList = new ArrayList<Permission>();

	@WireVariable
	PermissionService perSer;

	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		binder = new AnnotateDataBinder(comp);
		
		user = (User) Executions.getCurrent().getDesktop().getSession().getAttribute("utilizadorAutenticado");
		perSer = (PermissionService) SpringUtil.getBean("permissionService");
		perfilList = perSer.getAll();
		
		binder.bindBean("perfilList", perfilList);
		binder.loadAll();
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		visualizarPermissoes();
	}

	private void visualizarPermissoes() {

	}

	public List<Permission> getListPer() {
		return perfilList;
	}

	public void onClick$btnGravar() {
		Permission permissao = new Permission();
		permissao.setPermissionname(tbxPermissao.getValue());
		permissao.setDescription(tbxDescricao.getValue());
		//permissao.setUserCreated(user.getId());
		//permissao.setUserUpdated(user.getId());	
		perSer.create(permissao);
		Clients.showNotification("Permissao criada com sucesso!");
		tbxPermissao.setRawValue(null);
		tbxDescricao.setRawValue(null);	
		
		perfilList = perSer.getAll();
		table.invalidate();
		//table.getPage();
		binder.loadComponent(table);
	}

	public List<Permission> getPerfilList() {
		return perfilList;
	}

	public void setPerfilList(List<Permission> perfilList) {
		this.perfilList = perfilList;
	}
	

}
