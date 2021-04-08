package uem.mz.sgccovid19.controller;

import java.io.InputStream;  
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.zkoss.bind.annotation.Command;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import net.sf.jasperreports.engine.JRException;
import uem.mz.mint.entity.Departamento;
import uem.mz.mint.entity.Distrito;
import uem.mz.mint.entity.Ficha;
import uem.mz.mint.entity.Provincia;
import uem.mz.mint.entity.Sector;
import uem.mz.mint.entity.TipoUtente;
import uem.mz.mint.entity.UnidadeOrganica;
import uem.mz.mint.entity.Utente;
import uem.mz.mint.entity.administracao.User;
import uem.mz.mint.service.DistritoService;
import uem.mz.mint.service.FichaService;
import uem.mz.mint.service.ProvinciaService;
import uem.mz.mint.service.SectorService;
import uem.mz.mint.service.TipoUtenteService;
import uem.mz.mint.service.UnidadeOrganicaService;
import uem.mz.mint.service.UtenteService;
import uem.mz.mint.util.Breadcrumb;
import uem.mz.mint.util.MasterRep;
import uem.mz.mint.util.showClientNotification;

public class FichaController extends GenericForwardComposer{
	
	
	private Window win;
	private Div pesquisar;
	
	private showClientNotification cli = new showClientNotification();
	
	private List<String> links;
	@Wire 
	private Div breadcrumb;
	
	private Div target;
	
	private User user;
	
	private FichaService fichaService;
	private List<Ficha> fichaList;
	private ListModelList<Ficha> fichaModel;
	
	private Listbox lbxFichas;
	
	
	
	
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		user = (User) Executions.getCurrent().getDesktop().getSession().getAttribute("utilizadorAutenticado");
		
		target = (Div) Executions.getCurrent().getArg().get("target");
		
		fichaService = (FichaService) SpringUtil.getBean("fichaService");
		
		}
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		buscarFichas();
		
	}
	
	public void buscarFichas() {
		fichaList = fichaService.buscarFicha();
		fichaModel = new ListModelList<Ficha>(fichaList);
		lbxFichas.setModel(fichaModel);
	}
	
	
	
	
	public void onClick$btn_nova_ficha() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/ficha_investigacao/notificacao.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Notificação");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	
	
	
        
   
     
   
     
     
     
     
     
   
   
   
   
   
    
    
   
   

}
