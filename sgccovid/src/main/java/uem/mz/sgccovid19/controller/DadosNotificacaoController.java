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

import uem.mz.mint.entity.Classificacao;
import uem.mz.mint.entity.Distrito;
import uem.mz.mint.entity.Ficha;
import uem.mz.mint.entity.Provincia;
import uem.mz.mint.entity.TipoUtente;
import uem.mz.mint.entity.Utente;
import uem.mz.mint.entity.administracao.User;
import uem.mz.mint.service.ClassificacaoService;
import uem.mz.mint.service.DistritoService;
import uem.mz.mint.service.FichaService;
import uem.mz.mint.service.ProvinciaService;
import uem.mz.mint.service.TipoUtenteService;
import uem.mz.mint.service.UtenteService;
import uem.mz.mint.util.Breadcrumb;
import uem.mz.mint.util.showClientNotification;

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
	private Datebox dtb_dataEntrada;
	private Textbox txt_MeioTransporte;
	
	
	
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		user = (User) Executions.getCurrent().getDesktop().getSession().getAttribute("utilizadorAutenticado");
		
		target = (Div) Executions.getCurrent().getArg().get("target");
		
		utente = (Utente) Executions.getCurrent().getArg().get("utente");
		
		classificacaoService = (ClassificacaoService) SpringUtil.getBean("classificacaoService");
		
		fichaService = (FichaService) SpringUtil.getBean("fichaService");
		}
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		buscarClassificacao();
		
	}
	
	public void buscarClassificacao() {
		classList = classificacaoService.buscarClassificacao();
		classModel = new ListModelList<Classificacao>(classList);
		cbxClassificacao.setModel(classModel);
	}
	
	
    
	public void onClick$btn_voltar2() {
	   	
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/ficha_investigacao/dados_notificacao.zul", target, map);

		links = new ArrayList<String>();
		links.add("Dados da Notificacao");
		Breadcrumb.drawn(breadcrumb, "", links);

		
	}
     
     public void onClick$btn_proximo3() {
    	 
    	ficha = new Ficha();
    	
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
 		target.getChildren().clear();
 		Executions.createComponents("views/ficha_investigacao/residencia_caso.zul", target, map);

 		links = new ArrayList<String>();
 		links.add("Informações da residência do caso");
 		Breadcrumb.drawn(breadcrumb, "", links);

 		
 	}
    
     

}
