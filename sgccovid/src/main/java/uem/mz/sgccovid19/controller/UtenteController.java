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
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import uem.mz.sgccovid19.entity.Departamento;
import uem.mz.sgccovid19.entity.Distrito;
import uem.mz.sgccovid19.entity.Ficha;
import uem.mz.sgccovid19.entity.FichaContactoDirecto;
import uem.mz.sgccovid19.entity.Provincia;
import uem.mz.sgccovid19.entity.TipoUtente;
import uem.mz.sgccovid19.entity.UnidadeOrganica;
import uem.mz.sgccovid19.entity.Utente;
import uem.mz.sgccovid19.entity.administracao.User;
import uem.mz.sgccovid19.service.DistritoService;
import uem.mz.sgccovid19.service.ProvinciaService;
import uem.mz.sgccovid19.service.TipoUtenteService;
import uem.mz.sgccovid19.service.UnidadeOrganicaService;
import uem.mz.sgccovid19.service.UtenteService;
import uem.mz.sgccovid19.util.Breadcrumb;
import uem.mz.sgccovid19.util.showClientNotification;

public class UtenteController extends GenericForwardComposer{
	

	private Window win;
	private Div pesquisar;
	
	private showClientNotification cli = new showClientNotification();
	
	private List<String> links;
	@Wire 
	private Div breadcrumb;
	
	private Div target;
	private Div div_provincia_residencia;
	private Div div_distrito_residencia;
	
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
	private Textbox txtNome;
	private Textbox txtEmail;
	private Textbox txtContacto;
	private Textbox txtEndereco;
	private Datebox dtb_dataNascimento;
	
	private Ficha ficha;
	
	private FichaContactoDirecto fichContacto;
	
	private Provincia provincia;
	
	
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
		
		utente = (Utente) Executions.getCurrent().getArg().get("utente");
		
		ficha = (Ficha) Executions.getCurrent().getArg().get("ficha");
		
		fichContacto = (FichaContactoDirecto) Executions.getCurrent().getArg().get("fichContacto");
		
		}
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		buscarTipoUtente();
		buscarProvincia();
		buscarDistrito();
		PreencherDados();
		
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
	
	public void onSelect$cbxProvincia() {
		provincia = cbxProvincia.getSelectedItem().getValue();
		distritoList = distritoService.buscarDistritosPorProvincia(provincia);
		distritoModel = new ListModelList<Distrito>(distritoList);
		cbxDistrito.setModel(distritoModel);
		
	}
	
	public void buscarProvincia() {
		provinciaList = provinciaService.buscarProvincia();
		provinciaModel = new ListModelList<Provincia>(provinciaList);
		cbxProvincia.setModel(provinciaModel);
		
	}
	
	
    
    
   public void onClick$btn_voltar1() {
    	
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("utente", utente);
		map.put("ficha", ficha);
		map.put("fichContacto", fichContacto);
		target.getChildren().clear();
		Executions.createComponents("views/ficha_investigacao/notificacao.zul", target, map);

		

		
	}
   
   public void onClick$btn_proximo2() {
	   if(utente==null) {
		   utente = new Utente();
	   }
	    
	   utente.setNome(txtNome.getText());
	   
	   if(txtEmail.getValue()!=null) {
	    	utente.setEmail(txtEmail.getValue());
	    }
	   
	   if(txtContacto.getValue()!=null) {
	    	utente.setContacto(txtContacto.getValue());
	    }
	   
	   utente.setGenero((String)(cbxGenero.getSelectedItem().getValue()));
	   
	   utente.setDataNascimento(dtb_dataNascimento.getValue());
	   
	   
	   utente.setNacionalidade((String)cbxNacionalidade.getSelectedItem().getValue());
	   utente.setTipo_utente((TipoUtente)(cbxTipoUtente.getSelectedItem().getValue()));
	   
	 
	   if(cbxDistrito.getSelectedItem().getValue()!=null) {
		   utente.setDistrito((Distrito)(cbxDistrito.getSelectedItem().getValue()));  
	   }
	   
	   
	   utente.setEndereco(txtEndereco.getValue());
	   
	    
	    utente.setUnidade(user.getUnidade());
	    utente.setUserCreated(user.getId());
	    utente.setUserUpdated(user.getId());	
	    
	    utenteService.saveOrUpdate(utente);
		
		
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("utente", utente);
		map.put("ficha", ficha);
		map.put("fichContacto", fichContacto);
		target.getChildren().clear();
		Executions.createComponents("views/ficha_investigacao/dados_notificacao.zul", target, map);
		
		
		
		
	}
   
   public void PreencherDados() {
	   if(utente!=null) {
		   
		   if(utente.getTipo_utente()!=null) {
			   cbxTipoUtente.setValue(utente.getTipo_utente().getDesignacao());
		   }
		   
		   if(utente.getNacionalidade()!=null) {
			   cbxNacionalidade.setValue(utente.getNacionalidade());
		   }
		   
		   if(utente.getNome()!=null) {
			   txtNome.setValue(utente.getNome());
		   }
		   
		   if(utente.getEmail()!=null) {
			   txtEmail.setValue(utente.getEmail());
		   }
		   
		   if(utente.getContacto()!=null) {
			   txtContacto.setValue(utente.getContacto());
		   }
		   
		   if(utente.getGenero()!=null) {
			   cbxGenero.setValue(utente.getGenero());
		   }
		   
		   if(utente.getDataNascimento()!=null) {
			   dtb_dataNascimento.setValue(utente.getDataNascimento());
		   }
			  
		   if(utente.getDistrito().getProvincia()!=null) {
			   cbxProvincia.setValue(utente.getDistrito().getProvincia().getDesignacao());
		   }
		   
		   if(utente.getDistrito()!=null) {
			   cbxDistrito.setValue(utente.getDistrito().getDesignacao());
		   }
		   
		   
		   if(utente.getEndereco()!=null) {
			   txtEndereco.setValue(utente.getEndereco());
		   }
		   
		   
		   
	   }
	    
	   
	   
   }
   
    
     
    
     
     
     
     
     
     
     
   

}
