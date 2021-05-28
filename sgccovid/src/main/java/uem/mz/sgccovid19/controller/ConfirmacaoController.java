package uem.mz.sgccovid19.controller;

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

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import uem.mz.sgccovid19.entity.Ficha;
import uem.mz.sgccovid19.entity.FichaContactoDirecto;
import uem.mz.sgccovid19.entity.Utente;
import uem.mz.sgccovid19.entity.administracao.User;
import uem.mz.sgccovid19.service.FichaService;
import uem.mz.sgccovid19.service.UtenteService;
import uem.mz.sgccovid19.util.Breadcrumb;
import uem.mz.sgccovid19.util.showClientNotification;

public class ConfirmacaoController extends GenericForwardComposer{
	
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
	private Label label_outros_espacos;
	private Label label_unidade_contacto;
	private Label label_unidade_fora;
	private Label label_deparamento2;
	private Label label_departamento_fora;
	private Label label_sector2;
	private Label label_sector_fora;
	private Label label_outros2;
	private Label label_outros_espacos2;
	private Label label_localIsolamento;
	
	private FichaContactoDirecto fichContacto;
	
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
		
		fichContacto = (FichaContactoDirecto) Executions.getCurrent().getArg().get("fichContacto");
		
		}
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		preencherTela();
		
	}
	
	public void preencherTela() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); 
		
		label_nomeUnidade.setValue(ficha.getUtente().getUnidade().getDesignacao());
		label_classificacao.setValue(ficha.getClassificacao().getNome());
		label_nome.setValue(ficha.getUtente().getNome());
		label_ponto_entrada.setValue(ficha.getPontoEntrada());
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
		label_email.setValue(ficha.getUtente().getEmail());
		
		if(ficha.isViajou()==true) {
			label_viajou.setValue("Sim");
			
			if(ficha.getProveniencia()!=null) {
				label_Proveniencia.setValue(ficha.getProveniencia());
			}
			
			if(ficha.getMeioTransporte()!=null) {
				label_meio_transporte.setValue(ficha.getMeioTransporte());
			}
			
			if(ficha.getDataEntradaNoPais()!=null) {
				Date dataEntrada = ficha.getDataEntradaNoPais();
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
		
		if(ficha.getUtente().getDistrito().getDesignacao()!=null) {
			label_provincia_residencia.setValue(ficha.getUtente().getDistrito().getProvincia().getDesignacao());
			label_distrito_residencia.setValue(ficha.getUtente().getDistrito().getDesignacao());
			label_endereco.setValue(ficha.getUtente().getEndereco());
			
		}
		
		if(ficha.isEmIsolamento()==true) {
			label_emIsolamento.setValue("Sim");
			if(ficha.getDistrito_isolamento()!=null) {
				label_provincia_Isolamento.setValue(ficha.getDistrito_isolamento().getProvincia().getDesignacao());
				label_distrito_Isolamento.setValue(ficha.getDistrito_isolamento().getDesignacao());
			}
			
			if(ficha.getLocal_isolamento()!=null) {
				label_localIsolamento.setValue(ficha.getLocal_isolamento());
			}
			
		} else {label_emIsolamento.setValue("Não");}
		
		
		
		if(ficha.getFichaContacto()!=null) {
			
			if(ficha.getFichaContacto().isTeveContactoDentro()==true) {
				label_deparamento.setValue("Sim");
				
				if(ficha.getFichaContacto().getDepartamentoDentro()!=null) {
					label_departamento_dentro.setValue(ficha.getFichaContacto().getDepartamentoDentro().getDesignacao());
				}
				
				label_sector.setValue("Sim");
				
				if(ficha.getFichaContacto().getSectorDentro()!=null) {
					label_sector_dentro.setValue(ficha.getFichaContacto().getSectorDentro().getDesignacao());
				}
				
				
				if(ficha.getFichaContacto().getOutrosEspacosDentro()==null) {
					label_outros.setValue("Não");
					
				} else {
					label_outros.setValue("Sim");
					label_outros_espacos.setValue(ficha.getFichaContacto().getOutrosEspacosDentro());
				}
				
			} else {div_Contactodentro.setVisible(false);}
			
			
			if(ficha.getFichaContacto().isTeveContactoFora()==true) {
				label_unidade_contacto.setValue("Sim");
				
				if(ficha.getFichaContacto().getUnidadeFora()!=null) {
					label_unidade_fora.setValue(ficha.getFichaContacto().getUnidadeFora().getDesignacao());
				}
				
				
				label_deparamento2.setValue("Sim");
				
				if(ficha.getFichaContacto().getDepartamentoFora()!=null) {
					label_departamento_fora.setValue(ficha.getFichaContacto().getDepartamentoFora().getDesignacao());
				}
				label_sector2.setValue("Sim");
				
				if(ficha.getFichaContacto().getSectorFora()!=null) {
					label_sector_fora.setValue(ficha.getFichaContacto().getSectorFora().getDesignacao());
				}
				
				
				if(ficha.getFichaContacto().getOutrosEspacosFora()!=null) {
					label_outros2.setValue("Sim");
					label_outros_espacos2.setValue(ficha.getFichaContacto().getOutrosEspacosFora());
				}
			} else {div_ContactoFora.setVisible(false);}
			
		} else {
			div_Contactodentro.setVisible(false);
			div_ContactoFora.setVisible(false);
		}
		
		
		
		
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
	
	
	
    
	public void onClick$btn_Editar() {
 	   	
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
        
     public void onClickEditarDados(ForwardEvent evt) {
  	   	
   		final HashMap<String, Object> map = new HashMap<String, Object>();
   		map.put("target", target);
   		map.put("ficha", ficha);
   		map.put("utente", utente);
   		target.getChildren().clear();
   		Executions.createComponents("views/ficha_investigacao/edicao_dados.zul", target, map);
		
		

   		
   	}
      
      
      
      
      
      
     
     
   

}
