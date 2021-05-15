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

import uem.mz.sgccovid19.entity.Classificacao;
import uem.mz.sgccovid19.entity.Distrito;
import uem.mz.sgccovid19.entity.Ficha;
import uem.mz.sgccovid19.entity.FichaContactoDirecto;
import uem.mz.sgccovid19.entity.Provincia;
import uem.mz.sgccovid19.entity.TipoUtente;
import uem.mz.sgccovid19.entity.Utente;
import uem.mz.sgccovid19.entity.administracao.User;
import uem.mz.sgccovid19.service.ClassificacaoService;
import uem.mz.sgccovid19.service.DistritoService;
import uem.mz.sgccovid19.service.FichaService;
import uem.mz.sgccovid19.service.ProvinciaService;
import uem.mz.sgccovid19.service.TipoUtenteService;
import uem.mz.sgccovid19.service.UtenteService;
import uem.mz.sgccovid19.util.Breadcrumb;
import uem.mz.sgccovid19.util.showClientNotification;

public class DadosNotificacaoController extends GenericForwardComposer{
	
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
	
	
	private ClassificacaoService classificacaoService;
	private List<Classificacao> classList;
	private ListModelList<Classificacao> classModel;
	
	private FichaService fichaService;
	
	private Combobox cbxClassificacao;
	private Datebox dtb_dataTeste;
	private Datebox dtb_dataNotificacao;
	private Radio rdb_sim;
	private Textbox txt_proveniencia;
	private Textbox txt_pontoEntrada;
	private Radio rdb_sim_detectado;
	private Radio rdb_nao_detectado;
	private Radio rdb_nao;
	private Datebox dtb_dataEntrada;
	private Textbox txt_MeioTransporte;
	
	private FichaContactoDirecto fichContacto;
	
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		user = (User) Executions.getCurrent().getDesktop().getSession().getAttribute("utilizadorAutenticado");
		
		target = (Div) Executions.getCurrent().getArg().get("target");
		
		utente = (Utente) Executions.getCurrent().getArg().get("utente");
		
		classificacaoService = (ClassificacaoService) SpringUtil.getBean("classificacaoService");
		
		fichaService = (FichaService) SpringUtil.getBean("fichaService");
		
		ficha = (Ficha) Executions.getCurrent().getArg().get("ficha");
		
		fichContacto = (FichaContactoDirecto) Executions.getCurrent().getArg().get("fichContacto");
		
		}
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		buscarClassificacao();
		carregarDados();
		
	}
	
	public void buscarClassificacao() {
		classList = classificacaoService.buscarClassificacao();
		classModel = new ListModelList<Classificacao>(classList);
		cbxClassificacao.setModel(classModel);
	}
	
	
    
	public void onClick$btn_voltar2() {
	   	
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("utente", utente);
		map.put("ficha", ficha);
		map.put("fichContacto", fichContacto);
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/ficha_investigacao/dados_caso.zul", target, map);

		

		
	}
     
     public void onClick$btn_proximo3() {
    	if(ficha==null) {
    		ficha = new Ficha();
    		ficha.setNumeroFicha("F");
    	}
    	
    	ficha.setClassificacao((Classificacao)(cbxClassificacao.getSelectedItem().getValue()));
    	ficha.setDataTeste(dtb_dataTeste.getValue());
    	ficha.setDataNotificacao(dtb_dataNotificacao.getValue());
    	
    	if(rdb_sim.isSelected()) {
    		ficha.setViajou(true);
    		ficha.setProveniencia(txt_proveniencia.getValue());
    		ficha.setPontoEntrada(txt_pontoEntrada.getValue());
    		
    		if(rdb_sim_detectado.isSelected()) {
    		   ficha.setDetectadoNoPontoEntrada(true);
    		} else {ficha.setDetectadoNoPontoEntrada(false);}
    		
    		ficha.setDataEntradaNoPais(dtb_dataEntrada.getValue());
    		ficha.setMeioTransporte(txt_MeioTransporte.getValue());
    		
    	} else {ficha.setViajou(false);}
    	
    	ficha.setUserCreated(user.getId());
    	ficha.setUserUpdated(user.getId());
    	
    	ficha.setEstado("Pendente");
    	
    	
    	
    	ficha.setUtente(utente);
    	
    	fichaService.saveOrUpdate(ficha);
    	
 		final HashMap<String, Object> map = new HashMap<String, Object>();
 		map.put("target", target);
 		map.put("utente", utente);
 		map.put("ficha", ficha);
 		map.put("fichContacto", fichContacto);
 		target.getChildren().clear();
 		Executions.createComponents("views/ficha_investigacao/residencia_caso.zul", target, map);
 		
 		
		
 		
 	}
     
     public void carregarDados() {
    	 
    	 if (ficha!=null) {
    		 
    		 if(ficha.getClassificacao().getNome()!=null) {
    			 cbxClassificacao.setValue(ficha.getClassificacao().getNome()); 
    		 }
    		 
    		 if(ficha.getDataTeste()!=null) {
    			 dtb_dataTeste.setValue(ficha.getDataTeste());
    		 }
    		 
    		 if(ficha.getDataNotificacao()!=null) {
    			 dtb_dataNotificacao.setValue(ficha.getDataNotificacao());
    		 }
    		 
    		 if(ficha.isViajou()==true) {
    			 rdb_sim.setChecked(true);
    		 } else {rdb_nao.setChecked(true);}
    		 
    		 if(ficha.getProveniencia()!=null) {
    			 txt_proveniencia.setValue(ficha.getProveniencia());
    		 }
    		 
    		 if(ficha.getPontoEntrada()!=null) {
    			 txt_pontoEntrada.setValue(ficha.getPontoEntrada());
    		 }
    		 
    		 if(ficha.isDetectadoNoPontoEntrada()==true) {
    			 rdb_sim_detectado.setChecked(true);
    		 } else {rdb_nao_detectado.setChecked(true);}
    		 
    		 if(ficha.getDataEntradaNoPais()!=null) {
    			 dtb_dataEntrada.setValue(ficha.getDataEntradaNoPais());
    		 }
    		 
    		 if(ficha.getMeioTransporte()!=null) {
    			 txt_MeioTransporte.setValue(ficha.getMeioTransporte());
    		 }
    		 
    	 }
    	 
    	 
     }
    
     

}
