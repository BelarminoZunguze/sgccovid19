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

import uem.mz.sgccovid19.entity.Classificacao;
import uem.mz.sgccovid19.entity.Departamento;
import uem.mz.sgccovid19.entity.Distrito;
import uem.mz.sgccovid19.entity.Ficha;
import uem.mz.sgccovid19.entity.FichaContactoDirecto;
import uem.mz.sgccovid19.entity.FichaSector;
import uem.mz.sgccovid19.entity.LocalIsolamento;
import uem.mz.sgccovid19.entity.Provincia;
import uem.mz.sgccovid19.entity.Sector;
import uem.mz.sgccovid19.entity.TipoUtente;
import uem.mz.sgccovid19.entity.UnidadeOrganica;
import uem.mz.sgccovid19.entity.Utente;
import uem.mz.sgccovid19.entity.administracao.User;
import uem.mz.sgccovid19.entity.administracao.UserRole;
import uem.mz.sgccovid19.service.ClassificacaoService;
import uem.mz.sgccovid19.service.DepartamentoService;
import uem.mz.sgccovid19.service.DistritoService;
import uem.mz.sgccovid19.service.FichaContactoService;
import uem.mz.sgccovid19.service.FichaSectorService;
import uem.mz.sgccovid19.service.FichaService;
import uem.mz.sgccovid19.service.LocalIsolamentoService;
import uem.mz.sgccovid19.service.ProvinciaService;
import uem.mz.sgccovid19.service.SectorService;
import uem.mz.sgccovid19.service.TipoUtenteService;
import uem.mz.sgccovid19.service.UnidadeOrganicaService;
import uem.mz.sgccovid19.service.UtenteService;
import uem.mz.sgccovid19.util.Breadcrumb;
import uem.mz.sgccovid19.util.showClientNotification;

public class EdicaoController extends GenericForwardComposer{
	
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
	private ListModelList<Sector> sectorModel;
	private ListModelList<Sector> sectorEscolhidoModel;
	
	
	
	private Combobox cbxTipoUtente;
	private Combobox cbxNacionalidade;
	private Combobox cbxGenero;
	private Combobox cbxProvincia;
	private Combobox cbxDistrito;
	private Combobox cbxUnidade;
	private Combobox cbxClassificacao;
	private Combobox cbxLocalIsolamento;
	private Combobox cbxProvIsolamento;
	private Combobox cbxDistrIsolamento;
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
	private List<Sector> sectortList2 = new ArrayList<Sector>();
	
	
	private Sector sec;
	
	private Div div_espacos_dentro;
	private Div div_espacos_fora;
	
	
	private FichaContactoDirecto fichContacto;
	
	private FichaService fichaService;
	
	private FichaContactoService fichaContactoService;
	
	private FichaSectorService fichaSectorService;
	private List<Sector> sectoresList;
	private List<FichaSector> fichaSecList;
	private ListModelList<Sector> sectoresModel;
	
	private ClassificacaoService classificacaoService;
	private List<Classificacao> classList;
	private ListModelList<Classificacao> classModel;
	
	private LocalIsolamentoService localService;
	private List<LocalIsolamento> localList;
	private ListModelList<LocalIsolamento> localModel;
	
	
	
	
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
		
		classificacaoService = (ClassificacaoService) SpringUtil.getBean("classificacaoService");
		
		localService = (LocalIsolamentoService) SpringUtil.getBean("localService");
		
		
		}
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		buscarTipoUtente();
		buscarProvincia();
		buscarDistrito();
		buscarUnidadeOrganica();
		buscarProvinciaIsolamento();
		buscarDistritoIsolamento();
		
		buscarUnidadeOrganicaFora();
		buscarLocal();
		buscarSector();
		buscarClassificacao();
		preencherTela();
		
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
	
	public void buscarProvinciaIsolamento() {
		provinciaList = provinciaService.buscarProvincia();
		provinciaModel = new ListModelList<Provincia>(provinciaList);
		cbxProvIsolamento.setModel(provinciaModel);
	}
	
	public void buscarDistritoIsolamento() {
		distritoList = distritoService.buscarDistritos();
		distritoModel = new ListModelList<Distrito>(distritoList);
		cbxDistrIsolamento.setModel(distritoModel);
		
	}
	
	public void onSelect$cbxProvincia() {
		provincia = cbxProvincia.getSelectedItem().getValue();
		distritoList = distritoService.buscarDistritosPorProvincia(provincia);
		distritoModel = new ListModelList<Distrito>(distritoList);
		cbxDistrito.setModel(distritoModel);
		
	}
	
	public void onSelect$cbxProvIsolamento() {
		provincia = cbxProvIsolamento.getSelectedItem().getValue();
		distritoList = distritoService.buscarDistritosPorProvincia(provincia);
		distritoModel = new ListModelList<Distrito>(distritoList);
		cbxDistrIsolamento.setModel(distritoModel);
		
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
	
	public void buscarClassificacao() {
		classList = classificacaoService.buscarClassificacao();
		classModel = new ListModelList<Classificacao>(classList);
		cbxClassificacao.setModel(classModel);
	}
	
	private void buscarLocal(){
		localList = localService.buscarLocalIsolamento();
		localModel = new ListModelList<LocalIsolamento>(localList);
		cbxLocalIsolamento.setModel(localModel);
	}
	
	public void onCheck$rdb_sim_isolamento() {
		cbxLocalIsolamento.setConstraint("no empty: Selecione o Local de Isolamento!");
		cbxProvIsolamento.setConstraint("no empty: Selecione a Província de Isolamento!");
		cbxDistrIsolamento.setConstraint("no empty: Selecione o Distrito de Isolamento!");
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
	
	
	
	private void buscarUnidadeOrganicaFora(){    	  
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
		
		
	
		public void onSelect$lbxDepartamentoSectores(){
			
			
		   Sector secEscolhido = (Sector) lbxDepartamentoSectores.getSelectedItem().getValue();
			
		   boolean contem = false;
		   
			for(int i=0; i<listSector.size();i++) {
				
				if(secEscolhido.getId()==listSector.get(i).getId()) {
					contem = true;
				}
			} 
		   
			
			 if(contem) {
				 sectortList.remove(secEscolhido);
				 
			 } else {
				 listSector.add(secEscolhido);
				 sectortList.remove(secEscolhido);
			 }
				 
			
			sectorModel = new ListModelList<Sector>(sectortList);
			sectorModel.setMultiple(true);
			lbxDepartamentoSectores.setModel(sectorModel);
	
			sectorEscolhidoModel = new ListModelList<Sector>(listSector);
			sectorEscolhidoModel.setMultiple(true);
			lbxSectoresEscolhidos.setModel(sectorEscolhidoModel);
			
			
			
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
		
		
		
	
		public void onSelect$lbxSectoresFora(){
			
			
			
			Sector secEscolhido = (Sector) lbxSectoresFora.getSelectedItem().getValue();
				 
		 	listSector2.add(secEscolhido);
		    sectortList2.remove(secEscolhido);
				 
			sectorModel = new ListModelList<Sector>(sectortList2);
			sectorModel.setMultiple(true);
			lbxSectoresFora.setModel(sectorModel);
	
			sectorEscolhidoModel = new ListModelList<Sector>(listSector2);
			sectorEscolhidoModel.setMultiple(true);
			lbxSectoresForaEscolhidos.setModel(sectorEscolhidoModel);
			
			
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
		

	
	
	
	
	
	
	
	
	
	public void preencherTela() {
		//Dados do Utente
		
		txtNome.setValue(ficha.getUtente().getNome());
		//cbx_departamento.setValue(fun.getSector().getDepartamento()==null ? null : fun.getSector().getDepartamento().getDesignacao());	
		cbxNacionalidade.setValue(ficha.getUtente().getNacionalidade());
		txtEmail.setValue(ficha.getUtente().getEmail());
		txtContacto.setValue(ficha.getUtente().getContacto());
		cbxGenero.setValue(ficha.getUtente().getGenero());
		if(ficha.getUtente().getDataNascimento()!=null) {
			dtb_dataNascimento.setValue(ficha.getUtente().getDataNascimento());
		}
		
		cbxTipoUtente.setValue(ficha.getUtente().getTipo_utente().getDesignacao());
		cbxUnidade.setValue(ficha.getUtente().getUnidade().getDesignacao());
		cbxProvincia.setValue(ficha.getUtente().getDistrito().getProvincia().getDesignacao());
		cbxDistrito.setValue(ficha.getUtente().getDistrito().getDesignacao());
		txtEndereco.setValue(ficha.getUtente().getEndereco());
		
		//Informações sobre o caso
		
		cbxClassificacao.setValue(ficha.getClassificacao().getNome());
		if(ficha.getDataTeste()!=null) {
			dtb_dataTeste.setValue(ficha.getDataTeste());
		}
		
		if(ficha.getDataNotificacao()!=null) {
			dtb_dataNotificacao.setValue(ficha.getDataNotificacao());
		}
		
		
		if(ficha.isViajou()==true) {
			rdb_sim.setChecked(true);
			txt_proveniencia.setValue(ficha.getProveniencia());
			txt_pontoEntrada.setValue(ficha.getPontoEntrada());
			
			if(ficha.isDetectadoNoPontoEntrada()==true) {
				rdb_sim_detectado.setChecked(true);
			} else {rdb_nao_detectado.setChecked(true);}
			
			if(ficha.getDataEntradaNoPais()!=null) {
				dtb_dataEntrada.setValue(ficha.getDataEntradaNoPais());
			}
			
			txt_MeioTransporte.setValue(ficha.getMeioTransporte());
			
		} else {rdb_nao.setChecked(true);}
		
		//Resiência do caso
		
		if(ficha.isEmIsolamento()==true) {
			rdb_sim_isolamento.setChecked(true);
			
			if(ficha.getLocal_isolamento()!=null) {
				cbxLocalIsolamento.setValue(ficha.getLocal_isolamento());
			}
			
			
			if(ficha.getDistrito_isolamento()!=null) {
				cbxProvIsolamento.setValue(ficha.getDistrito_isolamento().getProvincia().getDesignacao());
				cbxDistrIsolamento.setValue(ficha.getDistrito_isolamento().getDesignacao());
							
			}
			
			dtb_dataInformou.setValue(ficha.getDataUltimaVezNaUnidade());
			dtb_dataUltima.setValue(ficha.getDataUltimaVezNaUnidade());
			txt_outras.setValue(ficha.getOutrasInformacoes());
		} else {rdb_nao_isolamento.setChecked(true);}
		
		
		//Seguimento de contactos na unidade
		
		if(ficha.getFichaContacto()!=null) {
			
			fichaSecList = fichaSectorService.buscarFichaSectorPorFicha(ficha);
			String unidadeUser = utente.getUnidade().getDesignacao();
			
			
			if(ficha.getFichaContacto().isTeveContactoDentro()==true) {
				rdb_sim_dentro.setChecked(true);
				
				List<Sector> sectoresDentro = new ArrayList<Sector>();
				
				
				for(int i=0;i<fichaSecList.size();i++) {
					
					String unidadeSector = fichaSecList.get(i).getSector().getDepartamento().getUnidade_organica().getDesignacao();
					
					if(unidadeSector.equals(unidadeUser)) {
						sectoresDentro.add(fichaSecList.get(i).getSector());
						
					}
					
				}
				
				
				ListModelList<Sector >sectoresModelDentro = new ListModelList<Sector>(sectoresDentro);
				sectoresModelDentro.setMultiple(true);
				lbxSectoresEscolhidos.setModel(sectoresModelDentro);
				
				ListModel<Sector> listSecJaExistentes =  lbxSectoresEscolhidos.getModel();
				
				
				ListModelList<Sector> lmsectorJa = new ListModelList<Sector>((Collection<? extends Sector>) listSecJaExistentes);
				
				for (Sector secJa: lmsectorJa){
					
					listSector.add(secJa);
					
		
				}
				
				
				if(ficha.getFichaContacto().getOutrosEspacosDentro()!=null) {
					
					txtOutrosDentro.setValue(ficha.getFichaContacto().getOutrosEspacosDentro());
				}
				
			} else {rdb_nao_dentro.setChecked(true);}
			
			
			
			if(ficha.getFichaContacto().isTeveContactoFora()==true) {
				rdb_sim_fora.setChecked(true);
				
				
				sectoresList = new ArrayList<Sector>();
				
				
				
				for(int i=0;i<fichaSecList.size();i++) {
					
					String unidadeSector = fichaSecList.get(i).getSector().getDepartamento().getUnidade_organica().getDesignacao();
					
					if(unidadeSector.equals(unidadeUser)==false) {
						sectoresList.add(fichaSecList.get(i).getSector());
					}
					
				}
				sectoresModel = new ListModelList<Sector>(sectoresList);
				sectoresModel.setMultiple(true);
				lbxSectoresForaEscolhidos.setModel(sectoresModel);
				
				ListModel<Sector> listSecJaExistentesFora =  lbxSectoresForaEscolhidos.getModel();
				
				
				ListModelList<Sector> lmsectorJaFora = new ListModelList<Sector>((Collection<? extends Sector>) listSecJaExistentesFora);
				
				for (Sector secJaFora: lmsectorJaFora){
					
					listSector2.add(secJaFora);
					
		
				}
				
				
				
				
				if(ficha.getFichaContacto().getOutrosEspacosFora()!=null) {
					
					txtoutrosFora.setValue(ficha.getFichaContacto().getOutrosEspacosFora());
					
				}
			} else {rdb_nao_fora.setChecked(true);}
			
		} 
		
		
		
		
	}
	
    
    
   public void onClick$btn_cancelar() {
    	
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		
		Executions.createComponents("views/ficha_investigacao/ficha_investigacao.zul", target, map);
		
		
	}
   
   public void onClick$btn_actualizar() {
	   
	    
	    utente.setUserUpdated(user.getId());
	    
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
	    
	    utenteService.update(utente);
	    
	    
	    //Gravar informações sobre o caso
	    
	    if(cbxClassificacao.getSelectedItem().getValue()!=null) {
	    	ficha.setClassificacao((Classificacao)(cbxClassificacao.getSelectedItem().getValue()));
	    }
	    
    	if(dtb_dataTeste.getValue()!=null) {
    		ficha.setDataTeste(dtb_dataTeste.getValue());
    	}
    	if(dtb_dataNotificacao.getValue()!=null) {
    		ficha.setDataNotificacao(dtb_dataNotificacao.getValue());
    	}
    	
    	
    	if(rdb_sim.isSelected()) {
    		ficha.setViajou(true);
    		if(txt_proveniencia.getValue()!=null) {
    			ficha.setProveniencia(txt_proveniencia.getValue());
    		}
    		if(txt_pontoEntrada.getValue()!=null) {
    			ficha.setPontoEntrada(txt_pontoEntrada.getValue());
    		}
    		
    		
    		if(rdb_sim_detectado.isSelected()) {
    		   ficha.setDetectadoNoPontoEntrada(true);
    		} else {ficha.setDetectadoNoPontoEntrada(false);}
    		
    		if(dtb_dataEntrada.getValue()!=null) {
    			ficha.setDataEntradaNoPais(dtb_dataEntrada.getValue());
    		}
    		
    		
    		if(txt_MeioTransporte.getValue()!=null) {
    			ficha.setMeioTransporte(txt_MeioTransporte.getValue());
    		}
    		
    		
    	} else {ficha.setViajou(false);}
    	
    	ficha.setUserUpdated(user.getId());
    	
    	//gravar residencia do caso
    	
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
    	
    	//gravar contactos na unidade
    	
    	if(rdb_sim_dentro.isSelected() || rdb_sim_fora.isSelected()) {
    		
    	fichaSecList = fichaSectorService.buscarFichaSectorPorFicha(ficha);
		
		for(int i=0; i<fichaSecList.size();i++) {
			fichaSectorService.delete(fichaSecList.get(i));
		}
		
		 
   		 if(ficha.getFichaContacto()==null) {
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
   			 
   		 } else {fichContacto.setTeveContactoDentro(false);}
   		 
   		 
   		 
   		 if(rdb_sim_fora.isSelected()) {
   			 
   			ListModel<Sector> listSecFora =  lbxSectoresForaEscolhidos.getModel();
			
			
			ListModelList<Sector> lmsector = new ListModelList<Sector>((Collection<? extends Sector>) listSecFora);
			
			List<Sector> sectoresFinaisFora = new ArrayList<Sector>();
			
			for (Sector sec: lmsector){
				
				sectoresFinaisFora.add(sec);
				
	
			}
   			 
   			 Sector novoSector = new Sector();
   			 
   			  
   			 for(int i=0; i<sectoresFinaisFora.size();i++) {
   				  
   					  novoSector = sectoresFinaisFora.get(i);
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
   			 
   		 } else {fichContacto.setTeveContactoFora(false);}
   		 
   		 fichContacto.setUserUpdated(user.getId());
   		 
   		 fichaContactoService.saveOrUpdate(fichContacto);
   		 
   		 ficha.setFichaContacto(fichContacto);
    	
    	
		
	} else {
		
		if(ficha.getFichaContacto()!=null) {
			
			ficha.setFichaContacto(null);
			 
			List<FichaSector> SecApagar = fichaSectorService.buscarFichaSectorPorFicha(ficha);
			 
			 if(SecApagar.isEmpty()==false) {
				 
				 for(int i=0;i<SecApagar.size();i++) {
					 FichaSector apagar = SecApagar.get(i);
					 fichaSectorService.delete(apagar); 
				 }
				 
			 }
			 
		}
		
	}
    	
    	fichaService.update(ficha);
	    
	    cli.Msg("Dados Actualizados com Sucesso!", win); 
		
		
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/ficha_investigacao/ficha_investigacao.zul", target, map);
		
   
    
   }  
    

}
