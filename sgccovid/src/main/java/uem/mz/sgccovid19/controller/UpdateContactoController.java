package uem.mz.sgccovid19.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import uem.mz.sgccovid19.entity.Departamento;
import uem.mz.sgccovid19.entity.Distrito;
import uem.mz.sgccovid19.entity.Ficha;
import uem.mz.sgccovid19.entity.FichaContactoDirecto;
import uem.mz.sgccovid19.entity.FichaSector;
import uem.mz.sgccovid19.entity.Provincia;
import uem.mz.sgccovid19.entity.Sector;
import uem.mz.sgccovid19.entity.TipoUtente;
import uem.mz.sgccovid19.entity.UnidadeOrganica;
import uem.mz.sgccovid19.entity.Utente;
import uem.mz.sgccovid19.entity.administracao.Permission;
import uem.mz.sgccovid19.entity.administracao.User;
import uem.mz.sgccovid19.service.DepartamentoService;
import uem.mz.sgccovid19.service.DistritoService;
import uem.mz.sgccovid19.service.FichaContactoService;
import uem.mz.sgccovid19.service.FichaSectorService;
import uem.mz.sgccovid19.service.FichaService;
import uem.mz.sgccovid19.service.ProvinciaService;
import uem.mz.sgccovid19.service.SectorService;
import uem.mz.sgccovid19.service.TipoUtenteService;
import uem.mz.sgccovid19.service.UnidadeOrganicaService;
import uem.mz.sgccovid19.service.UtenteService;
import uem.mz.sgccovid19.util.showClientNotification;

public class UpdateContactoController extends GenericForwardComposer{
	
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
	
	private DepartamentoService departamentoService;
	private List<Departamento> departList;
	private ListModelList<Departamento> departModel;
	
	private SectorService sectorService;
	private List<Sector> sectortList;
	private ListModelList<Sector> sectorEscolhidoModel;
	private ListModelList<Sector> sectorModel;
	
	
	
	private Combobox cbxUnidadeFora;
	
	
	private Textbox txtNome;
	private Textbox txtEmail;
	private Textbox txtContacto;
	private Textbox txtEndereco;
	private Textbox txt_proveniencia;
	private Textbox txt_pontoEntrada;
	private Textbox txt_MeioTransporte;
	private Textbox txt_outras;
	private Textbox txtOutrosDentro;
	private Textbox txtoutrosFora;
	
	private Datebox dtb_dataNascimento;
	private Datebox dtb_dataTeste;
	private Datebox dtb_dataNotificacao;
	private Datebox dtb_dataEntrada;
	private Datebox dtb_dataInformou;
	private Datebox dtb_dataUltima;
	
	
	private Radio rdb_sim;
	private Radio rdb_nao;
	private Radio rdb_sim_detectado;
	private Radio rdb_nao_detectado;
	private Radio rdb_sim_isolamento;
	private Radio rdb_nao_isolamento;
	private Radio rdb_sim_dentro;
	private Radio rdb_nao_dentro;
	private Radio rdb_sim_fora;
	private Radio rdb_nao_fora;
	
	private UtenteController ut;
	private Ficha ficha;
	
	private Provincia provincia;
	
	private Listbox lbxDepartamentoSectores;
	private Listbox lbxSectoresEscolhidos;
	
	private Listbox lbxSectoresFora;
	private Listbox lbxSectoresForaEscolhidos;
	
	private List<Sector> listSector = new ArrayList<Sector>();
	private Set<Sector> setSector = new HashSet<Sector>();
	
	private List<Sector> listSector2 = new ArrayList<Sector>();
	private List<Sector> sectortList2;
	
	
	private Sector sec;
	
	private Div div_espacos_dentro;
	private Div div_espacos_fora;
	
	private FichaSectorService fichaSectorService;
	
	private FichaContactoDirecto fichContacto;
	
	private FichaService fichaService;
	
	private FichaContactoService fichaContactoService;
	
	
	
	
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
		
		departamentoService = (DepartamentoService) SpringUtil.getBean("departamentoService");
		
		sectorService = (SectorService) SpringUtil.getBean("sectorService");
		
		
		ficha = (Ficha) Executions.getCurrent().getArg().get("ficha");
		
		utente = (Utente) Executions.getCurrent().getArg().get("utente");
		
		fichaSectorService = (FichaSectorService) SpringUtil.getBean("fichaSectorService");
		
		fichContacto = (FichaContactoDirecto) Executions.getCurrent().getArg().get("fichContacto");
		
		fichaContactoService = (FichaContactoService) SpringUtil.getBean("fichaContactoService");
		
		fichaService = (FichaService) SpringUtil.getBean("fichaService");
		
		
		
		
		}
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		
		
		buscarSector();
		
		buscarUnidadeOrganica();
		
		
	}
	
	public void onClick$btn_voltar4() {
 	   	
  		final HashMap<String, Object> map = new HashMap<String, Object>();
  		map.put("target", target);
  		map.put("utente", utente);
  		map.put("ficha", ficha);
  		map.put("fichContacto", fichContacto);
  		target.getChildren().clear();
  		Executions.createComponents("views/ficha_investigacao/residencia_caso.zul", target, map);

  		

  		
  	}
	
	
	 public void onClick$btn_proximo5() {
		 
		 
		 if(rdb_sim_dentro.isSelected() || rdb_sim_fora.isSelected()) {
    		 
    		 if(fichContacto==null) {
    			 fichContacto = new FichaContactoDirecto(); 
    		 }
    		 
    		
    		 if(rdb_sim_dentro.isSelected()) {
    			 
    			 Sector novoSector = new Sector();
    			  
    			 for(int i=0; i<listSector.size();i++) {
    				   
    				  novoSector = listSector.get(i);
    				  FichaSector fichSec = new FichaSector();
    				  fichSec.setFicha(ficha);
    				  fichSec.setSector(novoSector);
    				  fichSec.setUserCreated(user.getId());
    				  fichSec.setUserUpdated(user.getId());
    				  fichaSectorService.saveOrUpdate(fichSec);
    			 }
    			 
    			 
    			 fichContacto.setTeveContactoDentro(true);
    			 
    			 if(txtOutrosDentro.getText()!=null) {

    				 fichContacto.setOutrosEspacosDentro(txtOutrosDentro.getValue());
    				 
    			 }
    			 
    		 }
    		 
    		 
    		 
    		 if(rdb_sim_fora.isSelected()) {
    			 
    			 Sector novoSector = new Sector();
    			 
    			  
    			 for(int i=0; i<listSector2.size();i++) {
    				   
    				  novoSector = listSector2.get(i);
    				  FichaSector fichSec = new FichaSector();
    				  fichSec.setFicha(ficha);
    				  fichSec.setSector(novoSector);
    				  fichSec.setUserCreated(user.getId());
    				  fichSec.setUserUpdated(user.getId());
    				  fichaSectorService.saveOrUpdate(fichSec);
    			 }
    			 
    			  
    			 fichContacto.setTeveContactoFora(true);
    			
    			 
    			 if(txtoutrosFora.getValue()!=null) {
    				 
    				 fichContacto.setOutrosEspacosFora(txtoutrosFora.getValue());
    			 }
    			 
    		 }
    		 
    		 fichContacto.setUserCreated(user.getId());
    		 fichContacto.setUserUpdated(user.getId());
    		 
    		 fichaContactoService.saveOrUpdate(fichContacto);
    		 
    		 ficha.setFichaContacto(fichContacto);
    		 
    		 fichaService.saveOrUpdate(ficha);
    		 
    		final HashMap<String, Object> map = new HashMap<String, Object>();
	 		map.put("target", target);
	 		map.put("ficha", ficha);
	 		map.put("utente", utente);
	 		map.put("fichContacto", fichContacto);
	 		target.getChildren().clear();
	 		Executions.createComponents("views/ficha_investigacao/confirmacao_ficha.zul", target, map);
		 
		 
    		 
    		 
    		 
    		 
		 }
		 
		 
		 
		 
		 
		 
		 
	 }
	
	
	
	
	
	
	
	
	
	
	public void buscarSector() {
		
		  List<Sector> sectoresProvisorios = new ArrayList<Sector>();
		  sectortList = new ArrayList<Sector>();
		  
		  departList = departamentoService.buscarDepartamentoPorUnidade((UnidadeOrganica)user.getUnidade());
		  
		  for(int i=0;i<departList.size();i++) {
			  sectoresProvisorios = sectorService.buscarSectorPorDepartamento((Departamento)departList.get(i));
			  sectortList.addAll(sectoresProvisorios);
			
		  }
		  
		  
		  sectorModel = new ListModelList<Sector>(sectortList);
		  sectorModel.setMultiple(true);
		  lbxDepartamentoSectores.setModel(sectorModel);
		 
	}
	
	private void buscarUnidadeOrganica(){    	  
	  	  uniOrgList = unidadeOrganicaService.buscarUnidadeOrganica();
	  	  uniOrgModel = new ListModelList<UnidadeOrganica>(uniOrgList);
	  	  cbxUnidadeFora.setModel(uniOrgModel);    	  
	}
	
	public void onSelect$cbxUnidadeFora() {
		  
		  List<Sector> sectoresProvisorios = new ArrayList<Sector>();
		  sectortList2 = new ArrayList<Sector>();
		  
		  departList = departamentoService.buscarDepartamentoPorUnidade((UnidadeOrganica)cbxUnidadeFora.getSelectedItem().getValue());
		  
		  for(int i=0;i<departList.size();i++) {
			  sectoresProvisorios = sectorService.buscarSectorPorDepartamento((Departamento)departList.get(i));
			  sectortList2.addAll(sectoresProvisorios);
			
		  }
		  
		  
		  sectorModel = new ListModelList<Sector>(sectortList2);
		  sectorModel.setMultiple(true);
		  lbxSectoresFora.setModel(sectorModel);
		 
		
	}
	
	public void listaSectoresEscolhidos() {
		
		sectorEscolhidoModel = new ListModelList<Sector>(listSector);
		sectorEscolhidoModel.setMultiple(true);
		lbxSectoresEscolhidos.setModel(sectorEscolhidoModel);
		
	}
	
	
	
	public void onClick$chooseAll(){
		
			
			
			
			ListModel<Sector> listSec =  lbxDepartamentoSectores.getModel();
			
			
			
			ListModelList<Sector> lmsector = new ListModelList<Sector>((Collection<? extends Sector>) listSec);
			
			for (Sector s: lmsector){
				
				listSector.add(s);
				setSector.add(s);
				sectortList.remove(s);
	
			}
			
			sectorEscolhidoModel = new ListModelList<Sector>(listSector);
			sectorEscolhidoModel.setMultiple(true);
			lbxSectoresEscolhidos.setModel(sectorEscolhidoModel);
			
			sectorModel = new ListModelList<Sector>(sectortList);
			sectorModel.setMultiple(true);
			lbxDepartamentoSectores.setModel(sectorModel);
			
			
			
			
		}
	
		public void onClick$chooseBtn(){
			
		
	
			Set<Listitem> listSelectItens = lbxDepartamentoSectores.getSelectedItems();
			
			
				
			for(final Listitem li: listSelectItens){
				 Sector secEscolhido = (Sector)li.getValue();
				 
				 listSector.add(secEscolhido);
				 sectortList.remove(secEscolhido);
				 
				 
			}
			sectorModel = new ListModelList<Sector>(sectortList);
			sectorModel.setMultiple(true);
			lbxDepartamentoSectores.setModel(sectorModel);
	
			listaSectoresEscolhidos();
			
			
		}
		
		public void onClick$removeBtn(){
			
			
			Set<Listitem> listSelectItens = lbxSectoresEscolhidos.getSelectedItems();

			
				
			for(final Listitem li: listSelectItens){
				
				 Sector secEscolhido = (Sector)li.getValue();
				 
				 sectortList.add(secEscolhido);
				 listSector.remove(secEscolhido);
				 
			}
			
			sectorEscolhidoModel = new ListModelList<Sector>(listSector);
			sectorEscolhidoModel.setMultiple(true);
			lbxSectoresEscolhidos.setModel(sectorEscolhidoModel);
			
			sectorModel = new ListModelList<Sector>(sectortList);
			sectorModel.setMultiple(true);
			lbxDepartamentoSectores.setModel(sectorModel);
			lbxDepartamentoSectores.clearSelection();
			
			
			
		}
	
		public void onClick$removeAll(){
			
			
				
				
			ListModel<Sector> listSec =  lbxSectoresEscolhidos.getModel();
			
			ListModelList<Sector> lmsector = new ListModelList<Sector>((Collection<? extends Sector>) listSec);
			
			for (Sector s: lmsector){
				
				sectortList.add(s);
				listSector.remove(s);
				setSector.remove(s);
				
	
			}
			
			sectorEscolhidoModel = new ListModelList<Sector>(listSector);
			sectorEscolhidoModel.setMultiple(true);
			lbxSectoresEscolhidos.setModel(sectorEscolhidoModel);
			
			sectorModel = new ListModelList<Sector>(sectortList);
			sectorModel.setMultiple(true);
			lbxDepartamentoSectores.setModel(sectorModel);
			
			
				
				
			
			}
		
		public void onClickRemoverSectores(ForwardEvent e){
			
			
			
			Sector secEscolhido = (Sector) e.getData();
			
			 sectortList.add(secEscolhido);
			 listSector.remove(secEscolhido);
			 
		
		
			sectorEscolhidoModel = new ListModelList<Sector>(listSector);
			sectorEscolhidoModel.setMultiple(true);
			lbxSectoresEscolhidos.setModel(sectorEscolhidoModel);
			
			sectorModel = new ListModelList<Sector>(sectortList);
			sectorModel.setMultiple(true);
			lbxDepartamentoSectores.setModel(sectorModel);
			lbxDepartamentoSectores.clearSelection();
		
		}
		
		
		public void onClick$chooseAll2(){
		
			ListModel<Sector> listSec =  lbxSectoresFora.getModel();
			
			
			ListModelList<Sector> lmsector = new ListModelList<Sector>((Collection<? extends Sector>) listSec);
			
			for (Sector s: lmsector){
				
				listSector2.add(s);
				setSector.add(s);
				sectortList2.remove(s);
	
			}
			
			sectorEscolhidoModel = new ListModelList<Sector>(listSector2);
			sectorEscolhidoModel.setMultiple(true);
			lbxSectoresForaEscolhidos.setModel(sectorEscolhidoModel);
			
			sectorModel = new ListModelList<Sector>(sectortList2);
			sectorModel.setMultiple(true);
			lbxSectoresFora.setModel(sectorModel);
			
			
			
			
		}
	
		public void onClick$chooseBtn2(){
			
			
			Set<Listitem> listSelectItens = lbxSectoresFora.getSelectedItems();
			
			
				
			for(final Listitem li: listSelectItens){
				 Sector secEscolhido = (Sector)li.getValue();
				 
				 	listSector2.add(secEscolhido);
				    sectortList2.remove(secEscolhido);
				 
				 
			}
			sectorModel = new ListModelList<Sector>(sectortList2);
			sectorModel.setMultiple(true);
			lbxSectoresFora.setModel(sectorModel);
	
			sectorEscolhidoModel = new ListModelList<Sector>(listSector2);
			sectorEscolhidoModel.setMultiple(true);
			lbxSectoresForaEscolhidos.setModel(sectorEscolhidoModel);
			
			
		}
		
		public void onClick$removeBtn2(){
			
				Set<Listitem> listSelectItens = lbxSectoresForaEscolhidos.getSelectedItems();

			
				
			for(final Listitem li: listSelectItens){
				
				 Sector secEscolhido = (Sector)li.getValue();
				 
				 	sectortList2.add(secEscolhido);
				 	listSector2.remove(secEscolhido);
				 
			}
			
			sectorEscolhidoModel = new ListModelList<Sector>(listSector2);
			sectorEscolhidoModel.setMultiple(true);
			lbxSectoresForaEscolhidos.setModel(sectorEscolhidoModel);
			
			sectorModel = new ListModelList<Sector>(sectortList2);
			sectorModel.setMultiple(true);
			lbxSectoresFora.setModel(sectorModel);
			lbxSectoresFora.clearSelection();
			
			
			
		}
	
		public void onClick$removeAll2(){
			
				
			ListModel<Sector> listSec =  lbxSectoresForaEscolhidos.getModel();
			
			ListModelList<Sector> lmsector = new ListModelList<Sector>((Collection<? extends Sector>) listSec);
			
			for (Sector s: lmsector){
				
				sectortList2.add(s);
				listSector2.remove(s);
				setSector.remove(s);
				
	
			}
			
			sectorEscolhidoModel = new ListModelList<Sector>(listSector2);
			sectorEscolhidoModel.setMultiple(true);
			lbxSectoresForaEscolhidos.setModel(sectorEscolhidoModel);
			
			sectorModel = new ListModelList<Sector>(sectortList2);
			sectorModel.setMultiple(true);
			lbxSectoresFora.setModel(sectorModel);
			
			
				
				
			
			}
		
		public void onClickRemoverSectores2(ForwardEvent e){
			
			
			Sector secEscolhido = (Sector) e.getData();
			
				sectortList2.add(secEscolhido);
				listSector2.remove(secEscolhido);
			 
		
		
			sectorEscolhidoModel = new ListModelList<Sector>(listSector2);
			sectorEscolhidoModel.setMultiple(true);
			lbxSectoresForaEscolhidos.setModel(sectorEscolhidoModel);
			
			sectorModel = new ListModelList<Sector>(sectortList2);
			sectorModel.setMultiple(true);
			lbxSectoresFora.setModel(sectorModel);
			lbxSectoresFora.clearSelection();
		
		}
		
		public void onCheck$rdb_sim_dentro() {
			div_espacos_dentro.setVisible(true);
			
		}
		
		public void onCheck$rdb_nao_dentro() {
			
			div_espacos_dentro.setVisible(false);
			
		}
		
		
		public void onCheck$rdb_sim_fora() {
			div_espacos_fora.setVisible(true);
			
		}
		
		public void onCheck$rdb_nao_fora() {
			div_espacos_fora.setVisible(false);
			
		}
		
		
		
	
	
	
	
	
	
	
    
    
  

}
