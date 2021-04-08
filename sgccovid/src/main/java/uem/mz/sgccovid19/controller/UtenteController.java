package uem.mz.sgccovid19.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import uem.mz.mint.entity.Departamento;
import uem.mz.mint.entity.Distrito;
import uem.mz.mint.entity.Provincia;
import uem.mz.mint.entity.TipoUtente;
import uem.mz.mint.entity.UnidadeOrganica;
import uem.mz.mint.entity.Utente;
import uem.mz.mint.entity.administracao.User;
import uem.mz.mint.service.DistritoService;
import uem.mz.mint.service.ProvinciaService;
import uem.mz.mint.service.TipoUtenteService;
import uem.mz.mint.service.UnidadeOrganicaService;
import uem.mz.mint.service.UtenteService;
import uem.mz.mint.util.Breadcrumb;
import uem.mz.mint.util.showClientNotification;

public class UtenteController extends GenericForwardComposer{
	

	private Window win;
	private Div pesquisar;
	
	private showClientNotification cli = new showClientNotification();
	
	private List<String> links;
	@Wire 
	private Div breadcrumb;
	
	private Div target;
	
	private User user;
	
	private UtenteService utenteService;
	private List<Utente> utenteList;
	private ListModelList<Utente> utenteModel;
	private Utente utente;
	
	private TipoUtenteService tipoUtenteService;
	private List<TipoUtente> tiputList;
	private ListModelList<TipoUtente> tiputModel;
	
	private DistritoService distritoService;
	private List<Distrito> distritoList;
	private ListModelList<Distrito> distritoModel;
	
	
	private ProvinciaService provinciaService;
	private List<Provincia> provinciaList;
	private ListModelList<Provincia> provinciaModel;
	
	private UnidadeOrganicaService unidadeOrganicaService;
	private List<UnidadeOrganica> uniOrgList;
	private ListModelList<UnidadeOrganica> uniOrgModel;
	
	
	private Combobox cbxTipoUtente;
	private Combobox cbxNacionalidade;
	private Combobox cbxGenero;
	private Combobox cbxProvincia;
	private Combobox cbxDistrito;
	private Combobox cbxUnidade;
	private Textbox txtNome;
	private Textbox txtEmail;
	private Textbox txtContacto;
	private Textbox txtEndereco;
	private Datebox dtb_dataNascimento;
	
	
	
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		user = (User) Executions.getCurrent().getDesktop().getSession().getAttribute("utilizadorAutenticado");
		
		target = (Div) Executions.getCurrent().getArg().get("target");
		
		utenteService = (UtenteService) SpringUtil.getBean("utenteService");
		
		tipoUtenteService = (TipoUtenteService) SpringUtil.getBean("tipoUtenteService");
		
		distritoService = (DistritoService) SpringUtil.getBean("distritoService");
		
		provinciaService = (ProvinciaService) SpringUtil.getBean("provinciaService");
		
		unidadeOrganicaService = (UnidadeOrganicaService) SpringUtil.getBean("unidadeOrganicaService");
		
		
		}
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		buscarTipoUtente();
		buscarDistrito();
		buscarProvincia();
		buscarUnidadeOrganica();
		
	}
	
	public void buscarTipoUtente() {
		tiputList = tipoUtenteService.buscarTipoUtente();
		tiputModel = new ListModelList<TipoUtente>(tiputList);
		cbxTipoUtente.setModel(tiputModel);
	}
	
	public void buscarDistrito() {
		distritoList = distritoService.buscarDistritos();
		distritoModel = new ListModelList<Distrito>(distritoList);
		cbxDistrito.setModel(distritoModel);
		
	}
	
	public void buscarProvincia() {
		provinciaList = provinciaService.buscarProvincia();
		provinciaModel = new ListModelList<Provincia>(provinciaList);
		cbxProvincia.setModel(provinciaModel);
		
	}
	
	private void buscarUnidadeOrganica(){    	  
	  	  uniOrgList = unidadeOrganicaService.buscarUnidadeOrganica();
	  	  uniOrgModel = new ListModelList<UnidadeOrganica>(uniOrgList);
	  	  cbxUnidade.setModel(uniOrgModel);    	  
	}
	
    
    
   public void onClick$btn_voltar1() {
    	
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/ficha_investigacao/notificacao.zul", target, map);

		links = new ArrayList<String>();
		links.add("Notificacao");
		Breadcrumb.drawn(breadcrumb, "", links);

		
	}
   
   public void onClick$btn_proximo2() {
	   
	    utente = new Utente();
	    
	    utente.setNome(txtNome.getText());
	    utente.setNacionalidade((String)cbxNacionalidade.getSelectedItem().getValue());
		utente.setGenero((String)(cbxGenero.getSelectedItem().getValue()));
	    utente.setDataNascimento(dtb_dataNascimento.getValue());
	    utente.setEmail(txtEmail.getValue());
	    utente.setContacto(txtContacto.getValue());
	    utente.setEndereco(txtEndereco.getValue());
	    utente.setTipo_utente((TipoUtente)(cbxTipoUtente.getSelectedItem().getValue()));
	    utente.setDistrito((Distrito)(cbxDistrito.getSelectedItem().getValue()));
	    utente.setUnidade((UnidadeOrganica)(cbxUnidade.getSelectedItem().getValue()));
	    	
	    utente.setUserCreated(user.getId());
	    utente.setUserUpdated(user.getId());	
	    
	    utenteService.saveOrUpdate(utente);
		
		
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("utente", utente);
		target.getChildren().clear();
		Executions.createComponents("views/ficha_investigacao/dados_notificacao.zul", target, map);

		links = new ArrayList<String>();
		links.add("Dados do caso");
		Breadcrumb.drawn(breadcrumb, "", links);

		
	}
   
    
     
    
   
     
     
     
     
     
     
     
   

}
