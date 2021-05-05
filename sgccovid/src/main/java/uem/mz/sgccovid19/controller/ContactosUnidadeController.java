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

import uem.mz.sgccovid19.entity.Departamento;
import uem.mz.sgccovid19.entity.Ficha;
import uem.mz.sgccovid19.entity.FichaContactoDirecto;
import uem.mz.sgccovid19.entity.Sector;
import uem.mz.sgccovid19.entity.UnidadeOrganica;
import uem.mz.sgccovid19.entity.Utente;
import uem.mz.sgccovid19.entity.administracao.User;
import uem.mz.sgccovid19.service.DepartamentoService;
import uem.mz.sgccovid19.service.FichaContactoService;
import uem.mz.sgccovid19.service.FichaService;
import uem.mz.sgccovid19.service.SectorService;
import uem.mz.sgccovid19.service.UnidadeOrganicaService;
import uem.mz.sgccovid19.service.UtenteService;
import uem.mz.sgccovid19.util.Breadcrumb;
import uem.mz.sgccovid19.util.showClientNotification;

public class ContactosUnidadeController extends GenericForwardComposer{
	
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
	
	private UtenteService utenteService;
	
	private FichaService fichaService;
	
	private Radio rdb_sim_dentro;
	private Radio rdb_sim_fora;
	
	private Combobox cbxDepartamentoDentro;
	private Combobox cbxDepartamentoFora;
	private Combobox cbxSectorDentro;
	private Combobox cbxUnidadeFora;
	private Combobox cbxSectorFora;
	
	private Textbox txtOutrosDentro;
	private Textbox txtoutrosFora;
	
	private UnidadeOrganicaService unidadeOrganicaService;
	private List<UnidadeOrganica> uniOrgList;
	private ListModelList<UnidadeOrganica> uniOrgModel;
	
	private DepartamentoService departamentoService;
	private List<Departamento> departList;
	private ListModelList<Departamento> departModel;
	
	private SectorService sectorService;
	private List<Sector> sectortList;
	private ListModelList<Sector> sectorModel;
	
	private FichaContactoDirecto fichContacto;
	
	private FichaContactoService fichaContactoService;
	
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		user = (User) Executions.getCurrent().getDesktop().getSession().getAttribute("utilizadorAutenticado");
		
		target = (Div) Executions.getCurrent().getArg().get("target");
		
		utente = (Utente) Executions.getCurrent().getArg().get("utente");
		
		ficha = (Ficha) Executions.getCurrent().getArg().get("ficha");
		
		utenteService = (UtenteService) SpringUtil.getBean("utenteService");
		
		fichaService = (FichaService) SpringUtil.getBean("fichaService");
		
		unidadeOrganicaService = (UnidadeOrganicaService) SpringUtil.getBean("unidadeOrganicaService");
		
		departamentoService = (DepartamentoService) SpringUtil.getBean("departamentoService");
		
		sectorService = (SectorService) SpringUtil.getBean("sectorService");
		
		fichaContactoService = (FichaContactoService) SpringUtil.getBean("fichaContactoService");
		
		
		}
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		buscarUnidadeOrganica();
		
		buscarDepartamento();
		
		buscarSector();
		
	}
	
	public void buscarSector() {
		  sectortList = sectorService.buscarSector();
		  sectorModel = new ListModelList<Sector>(sectortList);
		  cbxSectorDentro.setModel(sectorModel);
		  cbxSectorFora.setModel(sectorModel);
		
	}
	
	public void buscarDepartamento() {
		  departList = departamentoService.buscarDepartamento();
		  departModel = new ListModelList<Departamento>(departList);
		  cbxDepartamentoDentro.setModel(departModel);
		  cbxDepartamentoFora.setModel(departModel);
		
	}
	
	private void buscarUnidadeOrganica(){    	  
	  	  uniOrgList = unidadeOrganicaService.buscarUnidadeOrganica();
	  	  uniOrgModel = new ListModelList<UnidadeOrganica>(uniOrgList);
	  	  cbxUnidadeFora.setModel(uniOrgModel);    	  
	}
	
	
	
    
	public void onClick$btn_voltar4() {
 	   	
  		final HashMap<String, Object> map = new HashMap<String, Object>();
  		map.put("target", target);
  		target.getChildren().clear();
  		Executions.createComponents("views/ficha_investigacao/residencia_caso.zul", target, map);

  		links = new ArrayList<String>();
  		links.add("Informações da residência do caso");
  		Breadcrumb.drawn(breadcrumb, "", links);

  		
  	}
     
     public void onClick$btn_gravar_ficha() {
    	 
    	 if(rdb_sim_dentro.isSelected() || rdb_sim_fora.isSelected()) {
    		 
    		 fichContacto = new FichaContactoDirecto();
    		 
    		 if(rdb_sim_dentro.isSelected()) {
    			 fichContacto.setTeveContactoDentro(true);
    			 
    			 //fichContacto.setDepartamentoDentro((String)cbxDepartamentoDentro.getSelectedItem().getValue());
    			 fichContacto.setDepartamentoDentro((Departamento)(cbxDepartamentoDentro.getSelectedItem()==null ? null : cbxDepartamentoDentro.getSelectedItem().getValue()));
    			 //fichContacto.setSectorDentro((String)cbxSectorDentro.getSelectedItem().getValue());
    			 fichContacto.setSectorDentro((Sector)(cbxSectorDentro.getSelectedItem()==null ? null : cbxSectorDentro.getSelectedItem().getValue()));
    			 
    			 if(txtOutrosDentro.getValue()!=null) {
    				 
    				 fichContacto.setOutrosEspacosDentro(txtOutrosDentro.getValue());
    			 }
    			 
    		 } else {fichContacto.setTeveContactoDentro(false);}
    		 
    		 
    		 if(rdb_sim_fora.isSelected()) {
    			 fichContacto.setTeveContactoFora(true);
    			 //fichContacto.setUnidadeFora((String)cbxUnidadeFora.getSelectedItem().getValue());
    			 fichContacto.setUnidadeFora((UnidadeOrganica)(cbxUnidadeFora.getSelectedItem()==null ? null : cbxUnidadeFora.getSelectedItem().getValue()));
    			 //fichContacto.setDepartamentoFora((String)cbxDepartamentoFora.getSelectedItem().getValue());
    			 fichContacto.setDepartamentoFora((Departamento)(cbxDepartamentoFora.getSelectedItem()==null ? null : cbxDepartamentoFora.getSelectedItem().getValue()));
    			 //fichContacto.setSectorFora((String)cbxSectorFora.getSelectedItem().getValue());
    			 fichContacto.setSectorFora((Sector)(cbxSectorFora.getSelectedItem()==null ? null : cbxSectorFora.getSelectedItem().getValue()));
    			 
    			 if(txtoutrosFora.getValue()!=null) {
    				 
    				 fichContacto.setOutrosEspacosFora(txtoutrosFora.getValue());
    			 }
    			 
    		 } else {fichContacto.setTeveContactoFora(false);}
    		 
    		 fichContacto.setUserCreated(user.getId());
    		 fichContacto.setUserUpdated(user.getId());
    		 
    		 fichaContactoService.saveOrUpdate(fichContacto);
    		 
    		 ficha.setFichaContacto(fichContacto);
    		 
    	 }
    	 
    	 
    	 
    	 
    	 fichaService.saveOrUpdate(ficha);
    	 
    	
    	 final HashMap<String, Object> map = new HashMap<String, Object>();
 		map.put("target", target);
 		map.put("ficha", ficha);
 		map.put("utente", utente);
 		target.getChildren().clear();
 		Executions.createComponents("views/ficha_investigacao/confirmacao_ficha.zul", target, map);
 		
 		links = new ArrayList<String>();
 		Breadcrumb.drawn(breadcrumb, "", links);
		
   		
   	}
     
     
   
   
	

}
