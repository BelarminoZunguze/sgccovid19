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
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import uem.mz.mint.entity.Distrito;
import uem.mz.mint.entity.Ficha;
import uem.mz.mint.entity.Provincia;
import uem.mz.mint.entity.Utente;
import uem.mz.mint.entity.administracao.User;
import uem.mz.mint.service.DistritoService;
import uem.mz.mint.service.FichaService;
import uem.mz.mint.service.ProvinciaService;
import uem.mz.mint.util.Breadcrumb;
import uem.mz.mint.util.showClientNotification;

public class ResidenciaCasoController extends GenericForwardComposer{
	
	private Window win;
	private Div pesquisar;
	
	private showClientNotification cli = new showClientNotification();
	
	private List<String> links;
	@Wire 
	private Div breadcrumb;
	
	private Div target;
	
	private User user;
	
	
	private Utente utente;
	private Ficha ficha;
	
	private DistritoService distritoService;
	private List<Distrito> distritoList;
	private ListModelList<Distrito> distritoModel;
	
	
	private ProvinciaService provinciaService;
	private List<Provincia> provinciaList;
	private ListModelList<Provincia> provinciaModel;
	
	
	private Combobox cbxLocalIsolamento;
	private Combobox cbxProvIsolamento;
	private Combobox cbxDistrIsolamento;
	private Datebox dtb_dataInformou;
	private Textbox txt_outras;
	
	private Datebox dtb_dataNotificacao;
	
	private Radio rdb_sim_isolamento;
	
	private Datebox dtb_dataUltima;
	
	
	private FichaService fichaService;
	
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		user = (User) Executions.getCurrent().getDesktop().getSession().getAttribute("utilizadorAutenticado");
		
		target = (Div) Executions.getCurrent().getArg().get("target");
		
		utente = (Utente) Executions.getCurrent().getArg().get("utente");
		
		ficha = (Ficha) Executions.getCurrent().getArg().get("ficha");
		
		fichaService = (FichaService) SpringUtil.getBean("fichaService");
		
		
		distritoService = (DistritoService) SpringUtil.getBean("distritoService");
		
		provinciaService = (ProvinciaService) SpringUtil.getBean("provinciaService");
		
		
		}
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		buscarDistrito();
		buscarProvincia();
		
	}
	
	public void buscarDistrito() {
		distritoList = distritoService.buscarDistritos();
		distritoModel = new ListModelList<Distrito>(distritoList);
		cbxDistrIsolamento.setModel(distritoModel);
		
	}
	
	public void buscarProvincia() {
		provinciaList = provinciaService.buscarProvincia();
		provinciaModel = new ListModelList<Provincia>(provinciaList);
		cbxProvIsolamento.setModel(provinciaModel);
		
	}
	
    
	public void onClick$btn_voltar3() {
	   	
 		final HashMap<String, Object> map = new HashMap<String, Object>();
 		map.put("target", target);
 		target.getChildren().clear();
 		Executions.createComponents("views/ficha_investigacao/dados_caso.zul", target, map);

 		links = new ArrayList<String>();
 		links.add("Dados do caso");
 		Breadcrumb.drawn(breadcrumb, "", links);

 		
 	}
     
     public void onClick$btn_proximo4() {
    	 
    	if(rdb_sim_isolamento.isSelected()) {
    		ficha.setEmIsolamento(true);
    		ficha.setLocal_isolamento((String)cbxLocalIsolamento.getSelectedItem().getValue());
    		ficha.setDistrito_isolamento((Distrito)cbxDistrIsolamento.getSelectedItem().getValue());
    		
    	}else {ficha.setEmIsolamento(false);}
    	
    	ficha.setDataQueInformoUnidade(dtb_dataInformou.getValue());
    	ficha.setDataUltimaVezNaUnidade(dtb_dataUltima.getValue());
    	
    	if(txt_outras.getValue()!=null) {
    		ficha.setOutrasInformacoes(txt_outras.getValue());
    	}
    	
    	ficha.setNumeroFicha("F"+ficha.getId());
    	
    	fichaService.saveOrUpdate(ficha);
    	
  		final HashMap<String, Object> map = new HashMap<String, Object>();
  		map.put("target", target);
  		map.put("utente", utente);
  		map.put("ficha", ficha);
  		target.getChildren().clear();
  		Executions.createComponents("views/ficha_investigacao/contactos_unidade.zul", target, map);

  		links = new ArrayList<String>();
  		links.add("Seguimento de Contactos na Unidade");
  		Breadcrumb.drawn(breadcrumb, "", links);

  		
  	}
     
     
    

}
