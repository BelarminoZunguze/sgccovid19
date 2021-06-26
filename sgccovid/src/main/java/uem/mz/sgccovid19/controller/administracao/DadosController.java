package uem.mz.sgccovid19.controller.administracao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
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
import org.zkoss.zul.Textbox;
import org.zkoss.zul.TreeModel;
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

public class DadosController extends GenericForwardComposer{
	
	private Pattern BCRYPT_PATTERN = Pattern
			.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");
	private Textbox txtUtilizador;
	private Textbox txtSenha;
	private Textbox txtConfirmarSenha;
	private Textbox txtSenhaAntiga;
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
	private org.zkoss.zhtml.Div div_alterar_dados;
	private org.zkoss.zhtml.Div div_dados_utilizador;
	private org.zkoss.zhtml.Div div_edicao_dados;
	private org.zkoss.zhtml.Div div_labelUnidade;
	
	private Div div_botoes;
	// private Tree tree;

	private Button btnProcurar;
	private Button btnGravar;
	private Button btnActualizar;
	private Button btnCancelar;
	private Button btnEcrypt;

	private Window win;
	
	private User uti;
	private User selUti;
	
	private Div target;
	
	private Label label_nome;
	private Label label_nome_utilizador;
	private Label label_contacto;
	private Label label_unidade;

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
	
	private User user;

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
		user = (User) Executions.getCurrent().getDesktop().getSession().getAttribute("utilizadorAutenticado");
		target = (Div) Executions.getCurrent().getArg().get("target");
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);

		buscarUnidadeOrganica();
		
		if(user.getRoles().toString().equals("[Admin]")) {
			divUnidade.setVisible(false);
			div_labelUnidade.setVisible(false);
		}
		preencherDados();
		

	}
	
	public void preencherDados() {
		if(user.getNome()!=null) {
			label_nome.setValue(""+user.getNome());
		}
		if(user.getUsername()!=null) {
			label_nome_utilizador.setValue(""+user.getUsername());
			
		}
		
		if(user.getContacto()!=null) {
			label_contacto.setValue(""+user.getContacto());
		}
		
		if(user.getUnidade()!=null) {
			label_unidade.setValue(user.getUnidade().getDesignacao());
		}
		
		
		
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

			
			} else {

				cli.Msg("Verifique a senha", win);
			}

		}
	}

	

	
	public void onClick$btn_editar() {
		div_dados_utilizador.setVisible(false);
		div_edicao_dados.setVisible(true);
		divSenha.setVisible(false);
		div_botoes.setVisible(true);
		
		txtUtilizador.setValue(user.getUsername());
		txtNome.setValue(user.getNome());
		txtContacto.setValue(user.getContacto());
		if(user.getRoles().toString().equals("[Admin]")==false) {
			cbxUnidade.setValue(user.getUnidade().getDesignacao());
		}
		
		
	}
	
	public void onClick$btn_alterar_senha() {
		div_dados_utilizador.setVisible(false);
		div_edicao_dados.setVisible(false);
		divSenha.setVisible(true);
		div_botoes.setVisible(true);
		
	}
	
	public void onClick$btn_voltar() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("dashboard.zul", target, map);
	}
	
	public void onClick$btn_cancelar() {
		div_dados_utilizador.setVisible(true);
		div_edicao_dados.setVisible(false);
		divSenha.setVisible(false);
		div_botoes.setVisible(false);
	}
	
	public void onClick$btn_actualizar() {
		
		if(div_edicao_dados.isVisible()) {
			
			if(txtUtilizador.getValue()!=null) {
				user.setUsername(txtUtilizador.getValue());
			}
			
			if(txtNome.getValue()!=null) {
				user.setNome(txtNome.getValue());
			}
			
			if(txtContacto.getValue()!=null) {
				user.setContacto(txtContacto.getValue());
			}
			
			utiSer.update(user);
			
		}
		
		if(divSenha.isVisible()) {
			
			txtSenhaAntiga.getValue();
			
			if(txtSenha.getValue()!=null && txtConfirmarSenha.getValue()!=null  ) {
				
				if (txtConfirmarSenha.getText().equals(txtSenha.getText())) {
					user.SetPasswordEncripted(txtSenha.getValue());
					utiSer.update(user);
				} else {

					cli.Msg("Verifique a senha", win);
					return;
				}
			}		
			
		}
		
		
		
		cli.Msg("Dados actualizados com sucesso", win);
		preencherDados();
		
		div_dados_utilizador.setVisible(true);
		div_edicao_dados.setVisible(false);
		divSenha.setVisible(false);
		div_botoes.setVisible(false);
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

	

	
	
	private void buscarUnidadeOrganica(){    	  
	  	  uniOrgList = unidadeOrganicaService.buscarUnidadeOrganica();
	  	  uniOrgModel = new ListModelList<UnidadeOrganica>(uniOrgList);
	  	  cbxUnidade.setModel(uniOrgModel);    	  
	}

	
}
