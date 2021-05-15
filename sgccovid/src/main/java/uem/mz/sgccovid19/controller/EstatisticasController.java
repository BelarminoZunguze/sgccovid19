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
import uem.mz.sgccovid19.entity.EstatisticaDistrito;
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
	
	private List<EstatisticaDistrito> estatisticaList;
	
	private Ficha ficha;
	
	private Utente utente;
	
	private Label label_suspeitos;
	private Label label_confirmados;
	private Label label_testados;
	private Label label_contactos;
	private Label label_masculino;
	private Label label_feminino;
	private Label label_estudante;
	private Label label_docente;
	private Label label_cta;
	
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
		
		buscarFichas();
		
		if(user.getId()!=1) {
			uniorg=user.getUnidade();
		}
		pesquisarPorClassificacao();
		pesquisarPorGenero();
		pesquisarPorTipoUtente();
		
	}
	
	public void pesquisarPorDistrito() {
		
	}
	
	public void pesquisarPorClassificacao() {
		numeroFicha = "";
		genero=null;
		tipoUte=null;
		int contador=0;
		/*
		if(user.getId()!=1) {
			uniorg=user.getUnidade();
		}*/
		classList = classificacaoService.buscarClassificacao();
		classific = classList.get(0);
		pesquisaList = fichaService.buscarFichas(numeroFicha, uniorg, genero, classific, tipoUte);
		label_suspeitos.setValue(""+pesquisaList.size());
		contador+=pesquisaList.size();
		pesquisaList.clear();
		
		classific = classList.get(1);
		pesquisaList = fichaService.buscarFichas(numeroFicha, uniorg, genero, classific, tipoUte);
		label_confirmados.setValue(""+pesquisaList.size());
		contador+=pesquisaList.size();
		pesquisaList.clear();
		
		classific = classList.get(2);
		pesquisaList = fichaService.buscarFichas(numeroFicha, uniorg, genero, classific, tipoUte);
		label_testados.setValue(""+pesquisaList.size());
		contador+=pesquisaList.size();
		pesquisaList.clear();
		
		classific = classList.get(3);
		pesquisaList = fichaService.buscarFichas(numeroFicha, uniorg, genero, classific, tipoUte);
		label_contactos.setValue(""+pesquisaList.size());
		contador+=pesquisaList.size();
		pesquisaList.clear();
		
		total_resultados.setValue("Total: "+contador);
		classList.clear();
		
	}
	
	public void pesquisarPorTipoUtente() {
		numeroFicha = "";
		genero=null;
		classific=null;
		
		/*
		if(user.getId()!=1) {
			uniorg=user.getUnidade();
		}*/
		
		tiputList = tipoUtenteService.buscarTipoUtente();
		tipoUte = tiputList.get(0);
		pesquisaList = fichaService.buscarFichas(numeroFicha, uniorg, genero, classific, tipoUte);
		label_estudante.setValue(""+pesquisaList.size());
		pesquisaList.clear();
		
		tiputList = tipoUtenteService.buscarTipoUtente();
		tipoUte = tiputList.get(1);
		pesquisaList = fichaService.buscarFichas(numeroFicha, uniorg, genero, classific, tipoUte);
		label_docente.setValue(""+pesquisaList.size());
		pesquisaList.clear();
		
		tiputList = tipoUtenteService.buscarTipoUtente();
		tipoUte = tiputList.get(2);
		pesquisaList = fichaService.buscarFichas(numeroFicha, uniorg, genero, classific, tipoUte);
		label_cta.setValue(""+pesquisaList.size());
		pesquisaList.clear();
		
		
		tiputList.clear();
		
	}
	
	public void pesquisarPorGenero() {
		numeroFicha = "";
		classific=null;
		tipoUte=null;
		
		/*
		if(user.getId()!=1) {
			uniorg=user.getUnidade();
		}*/
		
		String masculino = "Masculino";
		pesquisaList = fichaService.buscarFichas(numeroFicha, uniorg, masculino, classific, tipoUte);
		label_masculino.setValue(""+pesquisaList.size());
		pesquisaList.clear();
		
		String feminino = "Feminino";
		pesquisaList = fichaService.buscarFichas(numeroFicha, uniorg, feminino, classific, tipoUte);
		label_feminino.setValue(""+pesquisaList.size());
		pesquisaList.clear();
		
		
		
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
	
	public void onSelect$cbx_unidade() {
		uniorg = cbx_unidade.getSelectedItem().getValue();
		pesquisarPorClassificacao();
		pesquisarPorGenero();
		pesquisarPorTipoUtente();
	}
	
	
	
	
	
	
	
	
	


}
