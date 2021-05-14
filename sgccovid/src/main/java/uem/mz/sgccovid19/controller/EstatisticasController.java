package uem.mz.sgccovid19.controller;

import java.awt.Dialog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import uem.mz.sgccovid19.entity.Classificacao;
import uem.mz.sgccovid19.entity.Ficha;
import uem.mz.sgccovid19.entity.TipoUtente;
import uem.mz.sgccovid19.entity.UnidadeOrganica;
import uem.mz.sgccovid19.entity.Utente;
import uem.mz.sgccovid19.entity.administracao.User;
import uem.mz.sgccovid19.service.ClassificacaoService;
import uem.mz.sgccovid19.service.FichaService;
import uem.mz.sgccovid19.service.TipoUtenteService;
import uem.mz.sgccovid19.service.UnidadeOrganicaService;
import uem.mz.sgccovid19.util.Breadcrumb;
import uem.mz.sgccovid19.util.showClientNotification;

public class EstatisticasController extends GenericForwardComposer{
	

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
	
	private Label label_suspeitos;
	
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
		//pesquisarPorClassificacao();
		
	}
	
	public void pesquisarPorClassificacao() {
		numeroFicha = "";
		if(user.getId()!=1) {
			uniorg=user.getUnidade();
		}
		classList = classificacaoService.buscarClassificacao();
		classific = classList.get(0);
		pesquisaList = fichaService.buscarFichas(numeroFicha, uniorg, genero, classific, tipoUte);
		label_suspeitos.setValue(""+pesquisaList.size());
		pesquisaList.clear();
		classList.clear();
		
		/*
		classific = classList.get(1);
		classific = classList.get(2);
		classific = classList.get(3);
		*/
		
	}
	
	public void buscarFichas() {
		
		if(user.getId()==1) {
			fichaList = fichaService.buscarFicha();
			/*fichaModel = new ListModelList<Ficha>(fichaList);
			lbxFichas.setModel(fichaModel);*/
		} else {
			uniOrgList = unidadeOrganicaService.buscarUnidadeOrganica();
			fichaList = fichaService.buscarFichasPorUnidade(user.getUnidade());
			/*fichaModel = new ListModelList<Ficha>(fichaList);
			lbxFichas.setModel(fichaModel);*/
		}
		
		total_resultados.setValue("Total: "+fichaList.size());
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
		classList.clear();
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
		utente=ficha.getUtente();
		
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("ficha", ficha);
		map.put("utente", utente);
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
	


}
