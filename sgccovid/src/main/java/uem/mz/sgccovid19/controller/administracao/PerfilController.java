package uem.mz.sgccovid19.controller.administracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uem.mz.sgccovid19.entity.administracao.Permission;
import uem.mz.sgccovid19.entity.administracao.UserRole;
import uem.mz.sgccovid19.service.PermissionService;
import uem.mz.sgccovid19.service.UserRoleService;
import uem.mz.sgccovid19.util.showClientNotification;

/*import org.hibernate.validator.internal.util.privilegedactions.GetAnnotationParameter;*/
import org.zkoss.spring.SpringUtil;
import org.zkoss.zhtml.A;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class PerfilController extends GenericForwardComposer {
	
	@WireVariable 
	private UserRoleService perSer;
	
	@WireVariable 
	private  PermissionService permSer;
	
	private UserRole per;
	private UserRole selPer;
	
	private List<UserRole> listPer;
	private ListModelList<UserRole> listModPer;
	
	private List<Permission> listPerm = new ArrayList<Permission>();
	private Set<Permission> setPerm = new HashSet<Permission>();
	private ListModelList<Permission> listModPerm;
	private ListModelList<Permission> listModPermPer;
	private ListModelList<Permission> listModPermGetAll;
	
	private List<Permission> listPermPer = new ArrayList<Permission>();
	private Set<Permission> setPermPer = new HashSet<Permission>();

	private Textbox txtDesignacao;
	private Listheader lhrRemove;
	private Listbox lbxPermissoes;
	private Listbox lbxPermissoesPerfil;
	private Listbox lbxPerfil;
	private Combobox cbxPerfil;
	
	//private DualListbox dual_direitos;
	private A chooseAll;
	private A chooseBtn;
	private A removeBtn;
	private A removeAll;
	
	//private Button btnGravar;
	private Button btnNovoPerfil;
	private Button btnActualizar;
	private Button btnCancelar;
	
	private Window win;
	
	private showClientNotification cli = new showClientNotification();
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		perSer = (UserRoleService)SpringUtil.getBean("userRoleService");
		permSer = (PermissionService)SpringUtil.getBean("permissionService");
		
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
	
		listaPerfil();
		listaPermissoes();
		listaPerfilGetAll();
		lhrRemove.setVisible(false);
		txtDesignacao.setVisible(false);
		cbxPerfil.setVisible(true);
	}
	
	
	public void onClick$chooseAll(){
		
		if(cbxPerfil.getSelectedItem()==null && txtDesignacao.getText()==""){
			
			return;
		}else{
		ListModelList<Permission> listModelPermAll;
		
		ListModel<Permission> lp = lbxPermissoes.getModel();
		ListModelList<Permission> lmp = new ListModelList<Permission>((Collection<? extends Permission>) lp);
		for (Permission p: lmp){
			
			listPermPer.add(p);
			 setPermPer.add(p);
			 listPerm.remove(p);

		}
		selPer.setPermissions(setPermPer);
		listModPermPer = new ListModelList<Permission>(listPermPer);
		listModPermPer.setMultiple(true);
		lbxPermissoesPerfil.setModel(listModPermPer);

		listModPerm = new ListModelList<Permission>(listPerm);
		listModPerm.setMultiple(true);
		lbxPermissoes.setModel(listModPerm);

		}
		
		
	}
	
	
	public void onClick$chooseBtn(){

		Set<Listitem> listSelectItens = lbxPermissoes.getSelectedItems();
		
		if (cbxPerfil.getSelectedItem()==null && txtDesignacao.getText()=="") {
			return;
		} else {
			
		for(final Listitem li: listSelectItens){
			 Permission dis = (Permission)li.getValue();
			 
			 listPermPer.add(dis);
			 setPermPer.add(dis);

			 listPerm.remove(dis);
			 setPerm.remove(dis);
		}
		selPer.setPermissions(setPermPer);
		listModPerm = new ListModelList<Permission>(listPerm);
		listModPerm.setMultiple(true);
		lbxPermissoes.setModel(listModPerm);

		listaPermissoesPerfil();
		
		}
	}
	
	public void onClick$removeBtn(){
		
		Set<Listitem> listSelectItens = lbxPermissoesPerfil.getSelectedItems();

		if (cbxPerfil.getSelectedItem()==null && txtDesignacao.getText()=="") {
			return;
		} else {
			
		for(final Listitem li: listSelectItens){
			
			Permission dis = li.getValue();
			 listPerm.add(dis);
			 setPerm.add(dis);
			 
			listPermPer.remove(dis);
			 setPermPer.remove(dis);
		}
		selPer.setPermissions(setPermPer);
		listModPerm = new ListModelList<Permission>(listPerm);
		listModPerm.setMultiple(true);
		lbxPermissoes.setModel(listModPerm);

		listaPermissoesPerfil();
		lbxPermissoes.clearSelection();
		
		}
		
	}

	
	public void onClick$removeAll(){
		
		if(cbxPerfil.getSelectedItem()==null && txtDesignacao.getText()==""){
			
			return;
		}else{
		ListModelList<Permission> listModelPermAll;
		
		ListModel<Permission> lp = lbxPermissoesPerfil.getModel();
		ListModelList<Permission> lmp = new ListModelList<Permission>((Collection<? extends Permission>) lp);
		for (Permission p: lmp){
			listPerm.add(p);
			listPermPer.remove(p);
			 setPermPer.remove(p);
		}
		selPer.setPermissions(setPermPer);
		
		listModPermPer = new ListModelList<Permission>(listPermPer);
		listModPermPer.setMultiple(true);
		lbxPermissoesPerfil.setModel(listModPerm);

		listModPerm = new ListModelList<Permission>(listPerm);
		listModPerm.setMultiple(true);
		//lbxPermissoes.getItems().clear();
		lbxPermissoes.setModel(listModPerm);
	}
	}
	
	public void onClick$btnGravar(){
		
		
		perSer.saveOrUpdate(selPer);
		cli.Msg("Permissoes criadas com sucesso", win);
		listaPerfilGetAll();
		listaPermissoes();
		limpaCampos();
		
	}
	
	public void onClick$btnNovoPerfil(){
		
		if(btnNovoPerfil.getLabel().equals("Novo Perfil")){
			
			txtDesignacao.setVisible(true);
			cbxPerfil.setVisible(false);
			btnNovoPerfil.setLabel("Gravar");
			selPer = new UserRole();
		}else {
			
			
			selPer.setRolename(txtDesignacao.getValue());
			List<Permission> lisPer = new ArrayList<Permission>();
			Set<Permission> setP = new HashSet<Permission>();
			ListModel<Permission> lp = lbxPermissoesPerfil.getModel();
			
			if(lbxPermissoesPerfil.getItemCount()<1){
				
				//alert("empty");
				selPer.setPermissions(null);
			}else{
			ListModelList<Permission> lmp = new ListModelList<Permission>((Collection<? extends Permission>) lp);
			for (Permission p: lmp){
				lisPer.add(p);
				setP.add(p);
			}
				selPer.setPermissions(setP);
			}
			
			perSer.saveOrUpdate(selPer);
			btnNovoPerfil.setLabel("Novo Perfil");
			cli.Msg("Permissoes criadas com sucesso", win);
			listaPerfilGetAll();
			listaPermissoes();
			limpaCampos();
			
		}
		
		
	}
	
	public void onClick$btnActualizar(){
		
		selPer.setRolename(txtDesignacao.getText());
		perSer.saveOrUpdate(selPer);
		cli.Msg("Permissoes actualizadas com sucesso", win);
		listaPerfilGetAll();
		listaPermissoes();
		limpaCampos();
	}

	public void onClick$btnCancelar(){
		limpaCampos();
		btnActualizar.setVisible(false);
		//btnGravar.setVisible(true);
	}
	
	public void onSelect$lbxPermissoesPerfil(){
		
		
		
	
	
	}
	public void onSelect$lbxPerfil(){
		//listPermPer = new ArrayList<Permissoes>();
		
		//lbxPermissoesPerfil.getItems().clear();
		selPer = (UserRole)lbxPerfil.getSelectedItem().getValue();
		txtDesignacao.setValue(selPer.getRolename());
		cbxPerfil.setValue(selPer.getRolename());
		btnNovoPerfil.setVisible(false);
		cbxPerfil.setVisible(false);
		txtDesignacao.setVisible(true);
		
		//alert(""+selPer.getDesignacao());
		listPermPer = new ListModelList<Permission>(selPer.getPermissions());
		
		setPermPer = new HashSet<Permission>(listPermPer);
		selPer.setPermissions(setPermPer);
		listaPermissoesPerfil();
		
		//Set<Permissoes> sper = new HashSet<Permissoes>();
		//listaPermissoes();
		if(!selPer.getPermissions().isEmpty()){
			listPerm = permSer.listaPermissoesNaoSelecionadas(selPer.getPermissions());
			setPerm = new HashSet<Permission>(listPerm);
		listModPerm = new ListModelList<Permission>(listPerm);
		listModPerm.setMultiple(true);
		lbxPermissoes.setModel(listModPerm);
		
		
		}else {
			listModPermGetAll.setMultiple(true);
			listPerm = new ListModelList<Permission>(listModPermGetAll);
			setPerm = new HashSet<Permission>(listPerm);
			lbxPermissoes.setModel(listModPermGetAll);
		}
		//sper.addAll(pList);
		//alert("list"+pList);
		
		/*listPerm.removeAll(selPer.getPermissoes());
		
		listModPerm = new ListModelList<Permissoes>(listPerm);
		listModPerm.setMultiple(true);
		lbxPermissoes.setModel(listModPerm);*/
		
		
		
		btnActualizar.setVisible(true);
		//btnGravar.setVisible(false);
		lhrRemove.setVisible(true);
	}
	
	public void onClickRemoverPermissoes(ForwardEvent e){
		
		Permission p = (Permission) e.getData();
		
		listPerm.add(p);
		listPermPer.remove(p);
		setPermPer = new HashSet<Permission>(listPermPer);
		selPer.setPermissions(setPermPer);
		//perSer.update(selPer);
		
		listModPerm = new ListModelList<Permission>(listPerm);
		listModPerm.setMultiple(true);
		lbxPermissoes.setModel(listModPerm);
		
		listModPermPer = new ListModelList<Permission>(listPermPer);
		listModPermPer.setMultiple(true);
		lbxPermissoesPerfil.setModel(listModPermPer);
		
	}
	
	public void onSelect$cbxPerfil(){
		
		//selPer = new Perfil();
		selPer = (UserRole) cbxPerfil.getSelectedItem().getValue();
		//alert(""+selPer.getDesignacao());
		lbxPermissoesPerfil.getItems().clear();
		listModPerm = new ListModelList<Permission>();
	}
	
	public void limpaCampos(){
		
		lbxPermissoesPerfil.getItems().clear();
		listModPerm = new ListModelList<Permission>();
		cbxPerfil.setRawValue(null);
		btnActualizar.setVisible(false);
		//btnGravar.setVisible(true);
		
		listPermPer = new ArrayList<Permission>();
		listModPerm = new ListModelList<Permission>();
		listModPermPer = new ListModelList<Permission>();
		
		listModPermGetAll.setMultiple(true);
		listPerm = new ListModelList<Permission>(listModPermGetAll);
		setPerm = new HashSet<Permission>(listPerm);
		lbxPermissoes.setModel(listModPermGetAll);
		
		lhrRemove.setVisible(false);
		lbxPermissoesPerfil.setModel(new ListModelList<Permission>());
		
		txtDesignacao.setRawValue(null);
		txtDesignacao.setVisible(false);
		cbxPerfil.setVisible(true);
		btnNovoPerfil.setLabel("Novo Perfil");
		btnNovoPerfil.setVisible(true);
	}
	
	//alterar pela query que bysca perfis nao configurados
	//buscarUserRoleSemPermissoes
	public void listaPerfil(){
		listPer = perSer.getAll();
		listModPer = new ListModelList<UserRole>(listPer);
		cbxPerfil.setModel(listModPer);
		
	}
	
	public void listaPerfilGetAll(){
		listPer = perSer.getAll();
		listModPer = new ListModelList<UserRole>(listPer);
		lbxPerfil.setModel(listModPer);
		
	}
	
	public void listaPermissoes(){
		
		listPerm =  permSer.getAll();
		listModPerm = new ListModelList<Permission>(listPerm);
		listModPermGetAll = new ListModelList<Permission>(listPerm);
		listModPerm.setMultiple(true);
		lbxPermissoes.setModel(listModPerm);
		
	}
	
	public void listaPermissoesPerfil(){
		
		//listPerm = (Set<Permissoes>) permSer.getAll();
		listModPermPer = new ListModelList<Permission>(listPermPer);
		listModPermPer.setMultiple(true);
		lbxPermissoesPerfil.setModel(listModPermPer);
		
	}
	
	
	
	
}
