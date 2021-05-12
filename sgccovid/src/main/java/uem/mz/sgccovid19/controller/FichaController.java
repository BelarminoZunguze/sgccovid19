package uem.mz.sgccovid19.controller;

import java.awt.Dialog;
import java.io.InputStream;  
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

import org.zkoss.bind.annotation.Command;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import net.sf.jasperreports.engine.JRException;
import uem.mz.sgccovid19.entity.Classificacao;
import uem.mz.sgccovid19.entity.Departamento;
import uem.mz.sgccovid19.entity.Distrito;
import uem.mz.sgccovid19.entity.Ficha;
import uem.mz.sgccovid19.entity.Indicador;
import uem.mz.sgccovid19.entity.Provincia;
import uem.mz.sgccovid19.entity.Sector;
import uem.mz.sgccovid19.entity.TipoUtente;
import uem.mz.sgccovid19.entity.UnidadeOrganica;
import uem.mz.sgccovid19.entity.Utente;
import uem.mz.sgccovid19.entity.administracao.User;
import uem.mz.sgccovid19.entity.monitoria.FichaMonitoriaRespostas;
import uem.mz.sgccovid19.service.ClassificacaoService;
import uem.mz.sgccovid19.service.DistritoService;
import uem.mz.sgccovid19.service.FichaService;
import uem.mz.sgccovid19.service.ProvinciaService;
import uem.mz.sgccovid19.service.SectorService;
import uem.mz.sgccovid19.service.TipoUtenteService;
import uem.mz.sgccovid19.service.UnidadeOrganicaService;
import uem.mz.sgccovid19.service.UtenteService;
import uem.mz.sgccovid19.util.Breadcrumb;
import uem.mz.sgccovid19.util.MasterRep;
import uem.mz.sgccovid19.util.showClientNotification;

public class FichaController extends GenericForwardComposer{
	
	
	private Window win;
	private Div pesquisar;
	
	private showClientNotification cli = new showClientNotification();
	
	private Dialog resultado;
	
	private List<String> links;
	@Wire 
	private Div breadcrumb;
	
	private Div target;
	
	private User user;
	
	private FichaService fichaService;
	private List<Ficha> fichaList;
	private List<Ficha> pesquisaList;
	private ListModelList<Ficha> fichaModel;
	
	private Listbox lbxFichas;
	
	private Ficha ficha;
	
	private Utente utente;
	
	
	private Textbox txt_nrFicha;
	
	private UnidadeOrganicaService unidadeOrganicaService;
	private List<UnidadeOrganica> uniOrgList;
	private ListModelList<UnidadeOrganica> uniOrgModel;
	
	private ClassificacaoService classificacaoService;
	private List<Classificacao> classList;
	private ListModelList<Classificacao> classModel;
	
	private TipoUtenteService tipoUtenteService;
	private List<TipoUtente> tiputList;
	private ListModelList<TipoUtente> tiputModel;
	
	private Combobox cbxTipoUtente;
	
	private Combobox cbx_unidade;
	
	private Combobox cbx_classificacao;
	
	private Combobox cbx_genero;
	
	private Datebox dtb_dataSubmissao;
	
	private Combobox cbx_departamento;
	
	private Label total_resultados;
	
	private String numeroFicha;
	private String genero;
	private UnidadeOrganica uniorg;
	private Classificacao classific;
	private TipoUtente tipoUte;
	
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		user = (User) Executions.getCurrent().getDesktop().getSession().getAttribute("utilizadorAutenticado");
		
		target = (Div) Executions.getCurrent().getArg().get("target");
		
		fichaService = (FichaService) SpringUtil.getBean("fichaService");
		
		unidadeOrganicaService = (UnidadeOrganicaService) SpringUtil.getBean("unidadeOrganicaService");
		
		classificacaoService = (ClassificacaoService) SpringUtil.getBean("classificacaoService");
		
		tipoUtenteService = (TipoUtenteService) SpringUtil.getBean("tipoUtenteService");
		
		
		}
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		if(user.getId()==1) {
			buscarUnidadeOrganica();
		}
		buscarClassificacao();
		buscarTipoUtente();
		buscarFichas();
		
	}
	
	public void buscarFichas() {
		
		if(user.getId()==1) {
			fichaList = fichaService.buscarFicha();
			fichaModel = new ListModelList<Ficha>(fichaList);
			lbxFichas.setModel(fichaModel);
		} else {
			uniOrgList = unidadeOrganicaService.buscarUnidadeOrganica();
			fichaList = fichaService.buscarFichasPorUnidade(user.getUnidade());
			fichaModel = new ListModelList<Ficha>(fichaList);
			lbxFichas.setModel(fichaModel);
		}
		
		total_resultados.setValue("Total de Resultados: "+fichaList.size());
		fichaList.clear();
	}
	
	private void buscarUnidadeOrganica(){    	  
	  	  uniOrgList = unidadeOrganicaService.buscarUnidadeOrganica();
	  	  uniOrgModel = new ListModelList<UnidadeOrganica>(uniOrgList);
	  	  cbx_unidade.setModel(uniOrgModel);    	  
	}
	
	public void buscarClassificacao() {
		classList = classificacaoService.buscarClassificacao();
		classModel = new ListModelList<Classificacao>(classList);
		cbx_classificacao.setModel(classModel);
	}
	
	public void buscarTipoUtente() {
		tiputList = tipoUtenteService.buscarTipoUtente();
		tiputModel = new ListModelList<TipoUtente>(tiputList);
		cbxTipoUtente.setModel(tiputModel);
	}
	
	public void onClick$btn_pesquisar() {
		numeroFicha = txt_nrFicha.getValue();
		
		if(user.getId()==1) {
			if(cbx_unidade.getSelectedItem()!=null) {
				uniorg = cbx_unidade.getSelectedItem().getValue();
			}
		} else {uniorg=user.getUnidade();}
		
		
		if(cbx_classificacao.getSelectedItem()!=null) {
			classific = cbx_classificacao.getSelectedItem().getValue();
		}
		
		if(cbx_genero.getSelectedItem()!=null) {
			genero = cbx_genero.getSelectedItem().getValue();
		}
		
		if(cbxTipoUtente.getSelectedItem()!=null) {
			tipoUte = cbxTipoUtente.getSelectedItem().getValue();
		}
		
		pesquisaList = fichaService.buscarFichas(numeroFicha, uniorg, genero, classific, tipoUte);
		fichaModel = new ListModelList<Ficha>(pesquisaList);
		lbxFichas.setModel(fichaModel);
		total_resultados.setValue("Total de Resultados: "+pesquisaList.size());
		pesquisaList.clear();
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
	
	
	public void onClickVerFicha(ForwardEvent evt){
    	
		ficha = (Ficha) evt.getData();
		
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("ficha", ficha);
		target.getChildren().clear();
		Executions.createComponents("views/ficha_investigacao/detalhes.zul", target, map);

		links = new ArrayList<String>();
		links.add("Detalhes da Ficha");
		Breadcrumb.drawn(breadcrumb, "", links);
		
	}
	
	public void onClickEditarFicha(ForwardEvent evt){
    	
		ficha = (Ficha) evt.getData();
		utente = ficha.getUtente();
		
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("ficha", ficha);
		map.put("utente", utente);
		target.getChildren().clear();
		Executions.createComponents("views/ficha_investigacao/edicao_dados.zul", target, map);
		
		links = new ArrayList<String>();
		links.add("Actualizar dados");
		Breadcrumb.drawn(breadcrumb, "", links);
		
	}
	
	public void onClickApagar(ForwardEvent evt){
		ficha = (Ficha) evt.getData();
		
		Messagebox.show("Tem a certeza que pretende apagar?", "Confirm Dialog", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
		    public void onEvent(Event evt) throws InterruptedException {
		        if (evt.getName().equals("onOK")) {
		            alert("Ficha apagada com sucesso!");
		            fichaService.delete(ficha);
		            buscarFichas();
		        }  
		    }
		});
		
		
		
	}
	
	
        
   
     
   
     
     
     
     
     
   
   
   
   
   
    
    
   
   

}
