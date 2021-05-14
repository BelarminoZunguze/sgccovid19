package uem.mz.sgccovid19.controller.administracao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.DefaultTreeModel;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Tree;
import org.zkoss.zul.TreeModel;
import org.zkoss.zul.TreeNode;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Window;

import uem.mz.sgccovid19.entity.Delegacao;
import uem.mz.sgccovid19.entity.UnidadeOrganica;
import uem.mz.sgccovid19.entity.administracao.Permission;
import uem.mz.sgccovid19.entity.administracao.User;
import uem.mz.sgccovid19.entity.administracao.UserRole;
import uem.mz.sgccovid19.service.DelegacaoService;
import uem.mz.sgccovid19.service.PermissionService;
import uem.mz.sgccovid19.service.UnidadeOrganicaService;
import uem.mz.sgccovid19.service.UserRoleService;
import uem.mz.sgccovid19.service.UserService;
import uem.mz.sgccovid19.util.showClientNotification;


public class UtilizadorController extends GenericForwardComposer {
	private Pattern BCRYPT_PATTERN = Pattern
			.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");
	private Textbox txtUtilizador;
	private Textbox txtSenha;
	private Textbox txtConfirmarSenha;
	private Textbox txtNome;
	private Textbox txtContacto;
	
	private Checkbox chx_activo;
	private Checkbox chx_alterarSenha;
	private Listbox lbxUtilizador;
	//private Listbox lbxLocal;
	private Combobox cbxPerfil;// private Listbox lbxPerfil;
	
	private Combobox cbxUnidade;

	private org.zkoss.zhtml.Div divConfirmarSenha;
	private org.zkoss.zhtml.Div divSenha;
	private org.zkoss.zhtml.Div divNome;
	private org.zkoss.zhtml.Div divContacto;
	private org.zkoss.zhtml.Div divUnidade;
	// private Tree tree;

	private Button btnProcurar;
	private Button btnGravar;
	private Button btnActualizar;
	private Button btnCancelar;
	private Button btnEcrypt;

	private Window win;

	private User uti;
	private User selUti;

	private Permission perm;
	private UserRole per;
	private UserRole selPer;

	private List<UserRole> listPer;
	private ListModelList<UserRole> listModPer;
	// private Set<Local> selLocalItens;
	private List<Delegacao> selLocations;
	private List<User> listUti;
	private ListModelList<User> listModUti;
	
	private UnidadeOrganicaService unidadeOrganicaService;
	private List<UnidadeOrganica> uniOrgList;
	private ListModelList<UnidadeOrganica> uniOrgModel;

	private showClientNotification cli = new showClientNotification();

	@WireVariable
	private UserService utiSer;
	@WireVariable
	private DelegacaoService locSer;

	@WireVariable
	private PermissionService permSer;

	@WireVariable
	private UserRoleService perSer;

	TreeModel<UserRole> treeModPer;
	DefaultTreeModel<UserRole> defTreeModPer;

	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);

		utiSer = (UserService) SpringUtil.getBean("userService");
		perSer = (UserRoleService) SpringUtil.getBean("userRoleService");
		permSer = (PermissionService) SpringUtil.getBean("permissionService");
		locSer = (DelegacaoService) SpringUtil.getBean("delegacaoService");
		unidadeOrganicaService = (UnidadeOrganicaService) SpringUtil.getBean("unidadeOrganicaService");

	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);

		// listaUtilizador();
		listaPerfil();
		buscarUnidadeOrganica();

	}

	public void onClick$btnProcurar() {
		lbxUtilizador.getItems().clear();
		actualizarLista();

	}

	public void onClick$btnGravar() {

		if (btnGravar.getLabel().equals("Novo")) {
			divNome.setVisible(true);
			divContacto.setVisible(true);
			divUnidade.setVisible(true);
			divConfirmarSenha.setVisible(true);
			divSenha.setVisible(true);
			txtConfirmarSenha.setRawValue(null);
			txtSenha.setRawValue(null);
			txtUtilizador.setRawValue(null);
			cbxPerfil.setVisible(true);
			btnGravar.setLabel("Gravar");
			//limparLocal();
			cbxPerfil.setRawValue(null);

		} else {

			if (txtConfirmarSenha.getText().equals(txtSenha.getText())) {

				uti = new User();
				uti.setUsername(txtUtilizador.getValue());
				uti.setNome(txtNome.getValue());
				uti.setContacto(txtContacto.getValue());
				uti.setUnidade((UnidadeOrganica)cbxUnidade.getSelectedItem().getValue());
				uti.SetPasswordEncripted(txtSenha.getValue());
				uti.setPlanPass(txtSenha.getValue());
				uti.setEnabled(chx_activo.isChecked());
				actualizarPerfil(uti);
				/*List<Delegacao> selLocalItens = new ArrayList<>();
				for (Listitem local : lbxLocal.getItems())
					if (local.isSelected())
						selLocalItens.add((Delegacao) local.getValue());

				try {
					selUti.setUserDelegacao((Set<Delegacao>) selLocalItens);
				} catch (NullPointerException npe) {
					System.err.println("Nenhum local seleccionado");
				}*/
				// uti.setLocation();
				utiSer.saveOrUpdate(uti);
				cli.Msg("Utilizador registado com Sucesso", win);
				limpaCampos();

				listaUtilizador();
			} else {

				cli.Msg("Verifique a senha", win);
			}

		}
	}

	public void onClick$btnActualizar() {
		selUti.setUsername(txtUtilizador.getValue());
		if (txtConfirmarSenha.getText().equals(txtSenha.getText())) {
			selUti.SetPasswordEncripted(txtSenha.getValue());
		} else {

			cli.Msg("Verifique a senha", win);
			return;
		}
		// uti.setPlanPass(txtSenha.getValue());
		selUti.setEnabled(chx_activo.isChecked());
		actualizarPerfil(selUti);

		utiSer.saveOrUpdate(selUti);
		cli.Msg("Utilizador registado com Sucesso", win);
		limpaCampos();

		listaUtilizador();
	}

	public void onClick$btnCancelar() {

		limpaCampos();
	}

	public void onSelect$lbxUtilizador() {
		/////////////////////limparLocal();
		selUti = (User) lbxUtilizador.getSelectedItem().getValue();
		txtUtilizador.setValue(selUti.getUsername());
		txtSenha.setValue("***********");
		txtConfirmarSenha.setValue("***********");
		chx_activo.setChecked(selUti.isEnabled());

		Set<UserRole> setPer = selUti.getRoles();
		List<UserRole> lisPer = new ListModelList<UserRole>(setPer);
		// alert(""+lisPer.iterator().next().getDesignacao());
		try {
			cbxPerfil.setValue(lisPer.get(0).getRolename());
		} catch (IndexOutOfBoundsException iob) {
			iob.printStackTrace();

		}
		// divConfirmarSenha.setVisible(true);
		// lbxPerfil
		////////////////preencherLocal((List<Delegacao>) selUti.getUserDelegacao());

		cbxPerfil.setVisible(true);
	}

	/*public void preencherLocal(List<Delegacao> local) {

		for (Delegacao localItem : local) {
			for (Listitem item : lbxLocal.getItems())
				if (((Delegacao) item.getValue()).getId() == localItem.getId())
					item.setSelected(true);

		}
	}*/

	public void onCheck$chx_activo() {

		try {
			selUti = utiSer.buscarUser(txtUtilizador.getValue());

			if (selUti != null) {
				if (chx_activo.isChecked()) {
					selUti.setEnabled(true);
					utiSer.update(selUti);
					actualizarLista();
					Clients.showNotification("Utilizador activado");
				} else {
					selUti.setEnabled(false);
					utiSer.update(selUti);
					actualizarLista();
					Clients.showNotification("Utilizador desactivado com sucesso!");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void onCheck$chx_alterarSenha() {

		if (txtUtilizador.getText() != "") {
			divConfirmarSenha.setVisible(true);
			txtSenha.setRawValue(null);
			divSenha.setVisible(true);
			txtConfirmarSenha.setRawValue(null);
			btnActualizar.setVisible(true);
			btnGravar.setVisible(false);
		}

	}

	public void limpaCampos() {
		txtUtilizador.setRawValue(null);
		txtConfirmarSenha.setRawValue(null);
		txtSenha.setRawValue(null);
		chx_activo.setChecked(true);
		chx_alterarSenha.setChecked(false);
		btnActualizar.setVisible(false);
		btnGravar.setVisible(true);
		btnGravar.setLabel("Novo");
		divConfirmarSenha.setVisible(false);
		divSenha.setVisible(false);
		// txtSenha.setDisabled(true);
		txtNome.setRawValue(null);
		txtContacto.setRawValue(null);
		cbxUnidade.setModel(uniOrgModel);   

		cbxPerfil.setModel(listModPer);
		cbxPerfil.setRawValue(null);
		 lbxUtilizador.getItems().clear();
		// cbxPerfil.setVisible(false);
		//////////////////////////limparLocal();

	}

	public void listaUtilizador() {
		// listUti = utiSer.getAll();
		listUti = new ArrayList<User>();
		listModUti = new ListModelList<User>(listUti);
		lbxUtilizador.setModel(listModUti);
	}

	public void listaPerfil() {

		listPer = perSer.getAll();
		listModPer = new ListModelList<UserRole>(listPer);
		cbxPerfil.setModel(listModPer);

		//ListModelList<Delegacao> lml = new ListModelList<Delegacao>(locSer.getAll());
		//lbxLocal.setModel(lml);
		//lml.setMultiple(true);

	}
	
	private void buscarUnidadeOrganica(){    	  
	  	  uniOrgList = unidadeOrganicaService.buscarUnidadeOrganica();
	  	  uniOrgModel = new ListModelList<UnidadeOrganica>(uniOrgList);
	  	  cbxUnidade.setModel(uniOrgModel);    	  
	}

	public void actualizarPerfil(User user) {
		UserRole p = cbxPerfil.getSelectedItem().getValue();
		Set<UserRole> set = new HashSet<UserRole>();
		set.add(p);
		user.setRoles(set);
		List<Delegacao> selLocalItens = new ArrayList<Delegacao>();
		/*for (Listitem local : lbxLocal.getSelectedItems())
			selLocalItens.add((Delegacao) local.getValue());
		user.setUserDelegacao((Set<Delegacao>) selLocalItens);*/
		utiSer.saveOrUpdate(user);
	}

	public void onSelect$lbxLocal() {
		User user = new User();

		try {
			user = (User) lbxUtilizador.getSelectedItem().getValue();
			actualizarPerfil(user);
			Clients.showNotification("Local Actualizado!");
		} catch (NullPointerException ex) {

		}
	}

	public void actualizarLista() {
		if (txtUtilizador.getText() != "") {

			//User uti = utiSer.buscarUser(txtUtilizador.getText());
			List<User> lu = new ArrayList<User>();
			lu = utiSer.procurarUser(txtUtilizador.getText());

			if (lu == null) {
				return;
			} else {
				lbxUtilizador.setModel(new ListModelList<User>(lu));
			}
		} else {
			return;
		}

	}

	/*public void limparLocal() {
		for (Listitem item : lbxLocal.getItems())
			item.setSelected(false);
	}*/

}
