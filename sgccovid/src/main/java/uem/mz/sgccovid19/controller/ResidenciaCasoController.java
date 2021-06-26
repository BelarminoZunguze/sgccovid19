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

import uem.mz.sgccovid19.entity.Distrito;
import uem.mz.sgccovid19.entity.Ficha;
import uem.mz.sgccovid19.entity.FichaContactoDirecto;
import uem.mz.sgccovid19.entity.LocalIsolamento;
import uem.mz.sgccovid19.entity.Provincia;
import uem.mz.sgccovid19.entity.Sector;
import uem.mz.sgccovid19.entity.Utente;
import uem.mz.sgccovid19.entity.administracao.User;
import uem.mz.sgccovid19.service.DistritoService;
import uem.mz.sgccovid19.service.FichaService;
import uem.mz.sgccovid19.service.LocalIsolamentoService;
import uem.mz.sgccovid19.service.ProvinciaService;
import uem.mz.sgccovid19.util.Breadcrumb;
import uem.mz.sgccovid19.util.showClientNotification;

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
	
	private LocalIsolamentoService localService;
	private List<LocalIsolamento> localList;
	private ListModelList<LocalIsolamento> localModel;
	
	
	private Combobox cbxLocalIsolamento;
	private Combobox cbxProvIsolamento;
	private Combobox cbxDistrIsolamento;
	private Datebox dtb_dataInformou;
	private Textbox txt_outras;
	
	private Datebox dtb_dataNotificacao;
	
	private Radio rdb_sim_isolamento;
	private Radio rdb_nao_isolamento;
	
	private Datebox dtb_dataUltima;
	
	private Provincia provincia;
	
	private FichaService fichaService;
	
	private FichaContactoDirecto fichContacto;
	
	private Div div_isolamento;
	private Div div_datas;
	
	
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
		
		fichContacto = (FichaContactoDirecto) Executions.getCurrent().getArg().get("fichContacto");
		
		localService = (LocalIsolamentoService) SpringUtil.getBean("localService");
		
		
		
		}
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		buscarProvincia();
		buscarDistritoIsolamento();
		buscarLocal();
		carregarDados();
		
	}
	
	private void buscarLocal(){
		localList = localService.buscarLocalIsolamento();
		localModel = new ListModelList<LocalIsolamento>(localList);
		cbxLocalIsolamento.setModel(localModel);
	}
	
	public void onSelect$cbxProvIsolamento() {
		provincia = cbxProvIsolamento.getSelectedItem().getValue();
		distritoList = distritoService.buscarDistritosPorProvincia(provincia);
		distritoModel = new ListModelList<Distrito>(distritoList);
		cbxDistrIsolamento.setModel(distritoModel);
		
	}
	
	public void buscarDistritoIsolamento() {
		distritoList = distritoService.buscarDistritos();
		distritoModel = new ListModelList<Distrito>(distritoList);
		cbxDistrIsolamento.setModel(distritoModel);
		
	}
	
	public void buscarProvincia() {
		provinciaList = provinciaService.buscarProvincia();
		provinciaModel = new ListModelList<Provincia>(provinciaList);
		cbxProvIsolamento.setModel(provinciaModel);
		
	}
	
	public void onCheck$rdb_sim_isolamento() {
		div_isolamento.setVisible(true);
		div_datas.setVisible(true);
	}
	
	public void onCheck$rdb_nao_isolamento() {
		div_isolamento.setVisible(false);
		div_datas.setVisible(false);
	}
	
    
	public void onClick$btn_voltar3() {
	   	
 		final HashMap<String, Object> map = new HashMap<String, Object>();
 		map.put("target", target);
 		map.put("ficha", ficha);
 		map.put("utente", utente);
 		map.put("fichContacto", fichContacto);
 		target.getChildren().clear();
 		Executions.createComponents("views/ficha_investigacao/dados_notificacao.zul", target, map);

 		

 		
 	}
     
     public void onClick$btn_proximo4() {
    	 
    	if(rdb_sim_isolamento.isSelected()) {
    		ficha.setEmIsolamento(true);
    		
    		
    		ficha.setLocal_isolamento((String)(cbxLocalIsolamento.getSelectedItem()==null ? null : cbxLocalIsolamento.getSelectedItem().getLabel()));
			 
    		ficha.setDistrito_isolamento((Distrito)(cbxDistrIsolamento.getSelectedItem()==null ? null : cbxDistrIsolamento.getSelectedItem().getValue()));
    		
    		
    		
    	}else {ficha.setEmIsolamento(false);}
    	
    	if(dtb_dataInformou.getValue()!=null) {
    		ficha.setDataQueInformoUnidade(dtb_dataInformou.getValue());
    	}
    	if(dtb_dataUltima.getValue()!=null) {
    		ficha.setDataUltimaVezNaUnidade(dtb_dataUltima.getValue());
    	}
    	
    	
    	if(txt_outras.getValue()!=null) {
    		ficha.setOutrasInformacoes(txt_outras.getValue());
    	}
    	
    	//ficha.setNumeroFicha("F"+ficha.getId());
    	
    	fichaService.saveOrUpdate(ficha);
    	
  		final HashMap<String, Object> map = new HashMap<String, Object>();
  		map.put("target", target);
  		map.put("utente", utente);
  		map.put("ficha", ficha);
  		map.put("fichContacto", fichContacto);
  		target.getChildren().clear();
  		Executions.createComponents("views/ficha_investigacao/update_contactos.zul", target, map);
  		
  		
		
  		
  	}
     
     public void carregarDados() {
    	 
    	 if (ficha!=null) {
    		 
    		 if(ficha.isEmIsolamento()==true) {
    			 rdb_sim_isolamento.setChecked(true);
    			 
    			 if(ficha.getLocal_isolamento()!=null) {
    				 cbxLocalIsolamento.setValue(ficha.getLocal_isolamento());
    			 }
    			 
    			 if(ficha.getDistrito_isolamento().getProvincia()!=null) {
    				 cbxProvIsolamento.setValue(ficha.getDistrito_isolamento().getProvincia().getDesignacao());
    			 }
    			 
    			 if(ficha.getDistrito_isolamento()!=null) {
    				 cbxDistrIsolamento.setValue(ficha.getDistrito_isolamento().getDesignacao());
    			 }
    			 
    		 } else {rdb_nao_isolamento.setChecked(true);}
    		 
    		 
    		 if(ficha.getDataQueInformoUnidade()!=null) {
    			 dtb_dataInformou.setValue(ficha.getDataQueInformoUnidade());
    		 }
    		 
    		 if(ficha.getDataUltimaVezNaUnidade()!=null) {
    			 dtb_dataUltima.setValue(ficha.getDataUltimaVezNaUnidade());
    		 }
    		 
    		 if(ficha.getOutrasInformacoes()!=null) {
    			 txt_outras.setValue(ficha.getOutrasInformacoes());
    		 }
    		 
    		 
    	 }
    	 
    	 
     }
     
     
    

}
