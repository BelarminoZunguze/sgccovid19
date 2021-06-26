package uem.mz.sgccovid19.controller;

import java.io.InputStream;
import java.text.DateFormat; 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import net.sf.jasperreports.engine.JRException;
import uem.mz.sgccovid19.entity.Ficha;
import uem.mz.sgccovid19.entity.FichaContactoDirecto;
import uem.mz.sgccovid19.entity.FichaSector;
import uem.mz.sgccovid19.entity.Sector;
import uem.mz.sgccovid19.entity.Utente;
import uem.mz.sgccovid19.entity.administracao.User;
import uem.mz.sgccovid19.service.FichaSectorService;
import uem.mz.sgccovid19.service.FichaService;
import uem.mz.sgccovid19.service.UtenteService;
import uem.mz.sgccovid19.util.Breadcrumb;
import uem.mz.sgccovid19.util.MasterRep;
import uem.mz.sgccovid19.util.showClientNotification;

public class DetalhesController extends GenericForwardComposer{
	
	private Window win;
	private Div pesquisar;
	
	private showClientNotification cli = new showClientNotification();
	
	private List<String> links;
	@Wire 
	private Div breadcrumb;
	
	private Div target;
	private Div div_detectado;
	private Div div_meio_transporte;
	private Div div_ponto_entrada;
	private Div div_data_entrada;
	private Div div_proveniencia;
	private Div div_Contactodentro;
	private Div div_ContactoFora;
	
	private User user;
	
	private UtenteService utenteService;
	private List<Utente> utenteList;
	private ListModelList<Utente> utenteModel;
	
	private List<Ficha> fichaList;
	
	private Utente utente;
	private Ficha ficha;
	private FichaService fichaService;
	
	private Label label_nome;
	private Label label_ponto_entrada;
	private Label label_data_entrada;
	private Label label_nomeUnidade;
	private Label label_classificacao;
	private Label label_dataTeste;
	private Label label_dataNotificacao;
	private Label label_caso;
	private Label label_viajou;
	private Label label_Nacionalidade;
	private Label label_Proveniencia;
	private Label label_Sexo;
	private Label label_idade;
	private Label label_meio_transporte;
	private Label label_contacto;
	private Label label_Detectado;
	private Label label_email;
	private Label label_provincia_residencia;
	private Label label_distrito_residencia;
	private Label label_localIsolamento;
	private Label label_endereco;
	private Label label_emIsolamento;
	private Label label_provincia_Isolamento;
	private Label label_distrito_Isolamento;
	private Label label_data_ultima;
	private Label label_data_informou;
	private Label label_deparamento;
	private Label label_departamento_dentro;
	private Label label_sector;
	private Label label_sector_dentro;
	private Label label_outros;
	private Label label_outros_espacos_dentro;
	private Label label_unidade_contacto;
	private Label label_unidade_fora;
	private Label label_deparamento2;
	private Label label_departamento_fora;
	private Label label_sector2;
	private Label label_sector_fora;
	private Label label_outros2;
	private Label label_outros_espacos_fora;
	
	private Div div_labels_isolamento;
	private Div div_card_contactos;
	private Div div_espacos_dentro;
	private Div div_espacos_fora;
	
	private FichaSectorService fichaSectorService;
	private List<Sector> sectoresList;
	private List<FichaSector> fichaSecList;
	private ListModelList<Sector> sectoresModel;
	
	private Listbox lbxSectoresDentro;
	private Listbox lbxSectoresFora;
	
	
	
	
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
		
		//fichContacto = (FichaContactoDirecto) Executions.getCurrent().getArg().get("fichContacto");
		
		fichaSectorService = (FichaSectorService) SpringUtil.getBean("fichaSectorService");
		
		
		}
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		
		String isolamento="";
		String contacto="";
		
		if(ficha.isEmIsolamento()) {
			isolamento="certo";
		} else {isolamento=null;}
		
		if(ficha.getFichaContacto()!=null) {
			contacto="certo";
		}
		
		fichaList = fichaService.buscarFichasPorUtente(utente, isolamento, contacto);
		ficha = fichaList.get(0);
		
		preencherTela();
		
	}
	
	public void preencherTela() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); 
		
		label_nomeUnidade.setValue(ficha.getUtente().getUnidade().getDesignacao());
		label_classificacao.setValue(ficha.getClassificacao().getNome());
		label_nome.setValue(ficha.getUtente().getNome());
		
		if(ficha.getPontoEntrada()!=null) {
			label_ponto_entrada.setValue(ficha.getPontoEntrada());
		}
		label_caso.setValue(ficha.getUtente().getTipo_utente().getDesignacao());
		label_Nacionalidade.setValue(ficha.getUtente().getNacionalidade());
		label_Sexo.setValue(ficha.getUtente().getGenero());
		
		  
		Date dataNasc = ficha.getUtente().getDataNascimento();
		
		  Calendar c = Calendar.getInstance();
		  c.setTime(dataNasc);
		  int year = c.get(Calendar.YEAR);
		  int anoActual = Calendar.getInstance().get(Calendar.YEAR);
		  int idade = anoActual - year;
		  
		  
		
		label_idade.setValue(""+idade+" anos");
		  
		  
		label_contacto.setValue(ficha.getUtente().getContacto());
		if(ficha.getUtente().getEmail()!=null) {
			label_email.setValue(ficha.getUtente().getEmail());
		}
		
		
		if(ficha.isViajou()==true) {
			label_viajou.setValue("Sim");
			if(ficha.getProveniencia()!=null) {
				label_Proveniencia.setValue(ficha.getProveniencia());
			}
			if(ficha.getMeioTransporte()!=null) {
				label_meio_transporte.setValue(ficha.getMeioTransporte());
			}
			
			Date dataEntrada = ficha.getDataEntradaNoPais();
			if(dataEntrada!=null) {
				label_data_entrada.setValue(dateFormat.format(dataEntrada));
			}
			
			
			if(ficha.isDetectadoNoPontoEntrada()==true) {
				label_Detectado.setValue("Sim");	
			} else {label_Detectado.setValue("Não");}
			
		} else {
			label_viajou.setValue("Não");
			div_detectado.setVisible(false);
			div_meio_transporte.setVisible(false);
			div_ponto_entrada.setVisible(false);
			div_data_entrada.setVisible(false);
			div_proveniencia.setVisible(false);
			}
		
		
		label_provincia_residencia.setValue(ficha.getUtente().getDistrito().getProvincia().getDesignacao());
		label_distrito_residencia.setValue(ficha.getUtente().getDistrito().getDesignacao());
		label_endereco.setValue(ficha.getUtente().getEndereco());
		
		
		
		if(ficha.isEmIsolamento()==true) {
			label_emIsolamento.setValue("Sim");
			
			if(ficha.getLocal_isolamento()!=null) {
				label_localIsolamento.setValue(ficha.getLocal_isolamento());
			}
			
			if(ficha.getDistrito_isolamento().getProvincia()!=null) {
				label_provincia_Isolamento.setValue(ficha.getDistrito_isolamento().getProvincia().getDesignacao());
			}
			if(ficha.getDistrito_isolamento()!=null) {
				label_distrito_Isolamento.setValue(ficha.getDistrito_isolamento().getDesignacao());
			}
			
		} else {
			label_emIsolamento.setValue("Não");
			div_labels_isolamento.setVisible(false);
			}
		
		
		//Começa
		if(ficha.getFichaContacto()!=null) {
			
			fichaSecList = fichaSectorService.buscarFichaSectorPorFicha(ficha);
			String unidadeUser = utente.getUnidade().getDesignacao();
			
			
			if(ficha.getFichaContacto().isTeveContactoDentro()==true) {
				
				div_espacos_dentro.setVisible(true);
				
				List<Sector> sectoresDentro = new ArrayList<Sector>();
				
				
				for(int i=0;i<fichaSecList.size();i++) {
					
					String unidadeSector = fichaSecList.get(i).getSector().getDepartamento().getUnidade_organica().getDesignacao();
					
					if(unidadeSector.equals(unidadeUser)) {
						sectoresDentro.add(fichaSecList.get(i).getSector());
						
					}
					
				}
				
				
				ListModelList<Sector >sectoresModelDentro = new ListModelList<Sector>(sectoresDentro);
				lbxSectoresDentro.setModel(sectoresModelDentro);
				
				
				if(ficha.getFichaContacto().getOutrosEspacosDentro()!=null) {
					
					label_outros_espacos_dentro.setValue(ficha.getFichaContacto().getOutrosEspacosDentro());
				}
				
			} else {
				
				div_espacos_dentro.setVisible(false);
				
			}
			
			
			if(ficha.getFichaContacto().isTeveContactoFora()==true) {
				
				div_espacos_fora.setVisible(true);
				
				sectoresList = new ArrayList<Sector>();
				
				
				
				for(int i=0;i<fichaSecList.size();i++) {
					
					String unidadeSector = fichaSecList.get(i).getSector().getDepartamento().getUnidade_organica().getDesignacao();
					
					if(unidadeSector.equals(unidadeUser)==false) {
						sectoresList.add(fichaSecList.get(i).getSector());
					}
					
				}
				
				sectoresModel = new ListModelList<Sector>(sectoresList);
				lbxSectoresFora.setModel(sectoresModel);
				
				
				
				
				if(ficha.getFichaContacto().getOutrosEspacosFora()!=null) {
					
					label_outros_espacos_fora.setValue(ficha.getFichaContacto().getOutrosEspacosFora());
					
				}
			} else {
				
				div_espacos_fora.setVisible(false);
				
			}
			
		} else {div_card_contactos.setVisible(false);}
		
		
		
		//Termina
		
		
		
		
		Date dataTeste = ficha.getDataTeste();
		if(ficha.getDataTeste()!=null) {
			label_dataTeste.setValue(dateFormat.format(dataTeste));
		}
		
		Date dataNotificacao = ficha.getDataNotificacao();
		if(ficha.getDataNotificacao()!=null) {
			label_dataNotificacao.setValue(dateFormat.format(dataNotificacao));
		}
		
		Date dataUltima = ficha.getDataUltimaVezNaUnidade();
		if(ficha.getDataUltimaVezNaUnidade()!=null) {
			label_data_ultima.setValue(dateFormat.format(dataUltima));
		}
		
		Date dataInformou = ficha.getDataUltimaVezNaUnidade();
		if(ficha.getDataUltimaVezNaUnidade()!=null) {
			label_data_informou.setValue(dateFormat.format(dataInformou));
		}
		
		
	}
	
	public void buscarUtente() {
		utenteList = utenteService.buscarUtente();
		utenteModel = new ListModelList<Utente>(utenteList);
		
	}
	
	
	
    
	public void onClick$btn_editar() {
 	   	
 	   	
		final HashMap<String, Object> map = new HashMap<String, Object>();
   		map.put("target", target);
   		map.put("ficha", ficha);
   		map.put("utente", utente);
   		target.getChildren().clear();
   		Executions.createComponents("views/ficha_investigacao/edicao_dados.zul", target, map);
		
		

  		
  	}
     
     public void onClick$btn_confirmar_ficha() {
    	 
    	 
    	cli.Msg("Ficha de Investigação confirmada com Sucesso!", win); 
    	
    	final HashMap<String, Object> map = new HashMap<String, Object>();
 		map.put("target", target);
 		target.getChildren().clear();
 		Executions.createComponents("views/ficha_investigacao/ficha_investigacao.zul", target, map);

 		

   		
   	}
     
     public void onClick$btn_imprimir() throws JRException{
    	 
    	 fichaList = new ArrayList<Ficha>();
    	 fichaList.add(ficha);
    	 
    	 if (fichaList.isEmpty()) {			
 			Clients.showNotification("Informação Vazia", "info", win, "middle_center", 3000);
 		} else {

 			MasterRep mas = new MasterRep();
 			Map<String, Object> map = new HashMap<String, Object>();
 			
 			final Execution ex = Executions.getCurrent();
 			InputStream inputV = ex.getDesktop().getWebApp().getResourceAsStream("/images/moz.png");
 			map.put("imagemLogo", inputV);
 			String realPath = ex.getDesktop().getWebApp().getRealPath("/reports/");
 			map.put("SUBREPORT_DIR", realPath);
 			mas.imprimir("/reports/FichaNotificacao.jrxml", fichaList, map, win);
 		}
 		
 		fichaList.clear();
    	 
    	 
     }
     
     public void onClick$btn_voltar() {
    	
     	final HashMap<String, Object> map = new HashMap<String, Object>();
  		map.put("target", target);
  		target.getChildren().clear();
  		Executions.createComponents("views/ficha_investigacao/ficha_investigacao.zul", target, map);

  		

    		
    	}
     
     
   

}
