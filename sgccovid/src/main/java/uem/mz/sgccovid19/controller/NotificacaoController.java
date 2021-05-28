package uem.mz.sgccovid19.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import uem.mz.sgccovid19.entity.Ficha;
import uem.mz.sgccovid19.entity.FichaContactoDirecto;
import uem.mz.sgccovid19.entity.Utente;
import uem.mz.sgccovid19.entity.administracao.User;
import uem.mz.sgccovid19.service.FichaService;
import uem.mz.sgccovid19.util.Breadcrumb;
import uem.mz.sgccovid19.util.showClientNotification;

public class NotificacaoController extends GenericForwardComposer{
	
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
	
	private FichaContactoDirecto fichContacto;
	
	private FichaService fichaService;
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		user = (User) Executions.getCurrent().getDesktop().getSession().getAttribute("utilizadorAutenticado");
		
		target = (Div) Executions.getCurrent().getArg().get("target");
		
		utente = (Utente) Executions.getCurrent().getArg().get("utente");
		
		ficha = (Ficha) Executions.getCurrent().getArg().get("ficha");
		
		fichContacto = (FichaContactoDirecto) Executions.getCurrent().getArg().get("fichContacto");
		
		fichaService = (FichaService) SpringUtil.getBean("fichaService");
		
		
		
		}
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		
		
	}
	
	public void onClick$btn_proximo1() {
    	
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("utente", utente);
		map.put("ficha", ficha);
		map.put("fichContacto", fichContacto);
		target.getChildren().clear();
		Executions.createComponents("views/ficha_investigacao/dados_caso.zul", target, map);
		
		
		
		
	}
	
	public void onClick$btn_voltar_ficha() {
		
		if(utente==null) {
			final HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("target", target);
			target.getChildren().clear();
			Executions.createComponents("views/ficha_investigacao/ficha_investigacao.zul", target, map);
		} else {
			
			Messagebox.show("Começou o preenchimento de uma ficha. Tem a certeza que pretende voltar?", "Confirm Dialog", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
			    public void onEvent(Event evt) throws InterruptedException {
			        if (evt.getName().equals("onOK")) {
			        	
			        	if(ficha!=null) {
			        		fichaService.delete(ficha);
			        	}
			        	
			        	final HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("target", target);
						target.getChildren().clear();
						Executions.createComponents("views/ficha_investigacao/ficha_investigacao.zul", target, map);
			           
			        } else {return;} 
			    }
			});
			
		}
		
		
		
	}


}
