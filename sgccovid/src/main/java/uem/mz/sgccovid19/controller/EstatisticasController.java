package uem.mz.sgccovid19.controller;

import java.awt.Dialog;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
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
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import net.sf.jasperreports.engine.JRException;
import uem.mz.sgccovid19.entity.Actividade;
import uem.mz.sgccovid19.entity.Classificacao;
import uem.mz.sgccovid19.entity.Distrito;
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
import uem.mz.sgccovid19.util.MasterRep;
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
	private List<Ficha> fichaDistritoList;
	private ListModelList<Ficha> fichaModel;
	
	private Listbox lbxFichas;
	private Listbox lbxFichasDistrito;
	
	private List<EstatisticaDistrito> estatisticaList;
	private ListModelList<EstatisticaDistrito> estatisticaModel;
	
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
	private Label label_investigador;
	
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
	private Label total_genero;
	private Label total_tipo_utente;
	private Label total_distrito;
	private Label total_situacao;
	private Label label_recuperados;
	private Label label_internados;
	private Label label_indeterminados;
	private Label label_obitos;
	
	private String numeroFicha;
	private String genero;
	private UnidadeOrganica uniorg;
	private Classificacao classific;
	private TipoUtente tipoUte;
	private Date dataInicio; 
	private Date dataFim;
	private String estado;
	
	private Datebox dtb_dataInicio;
	
	private Datebox dtb_Fim;
	
	private Distrito disResidencia;
	private List<Distrito> controleDistrito;
	
	private EstatisticaDistrito est;
	
	private String titiloUnidade="";
	
	
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
		
		if(user.getRoles().toString().equals("[Admin]")) {
			buscarUnidadeOrganica();
		} else 	{
			uniorg=user.getUnidade();
			titiloUnidade = user.getUnidade().getDesignacao().toUpperCase();
			}
		
		buscarFichas();
		
		
		pesquisarPorClassificacao();
		pesquisarPorGenero();
		pesquisarPorTipoUtente();
		pesquisarPorDistrito();
		buscarPorSituacao();
		
		
	}
	
	public void pesquisarPorDistrito() {
		controleDistrito = new ArrayList<Distrito>();
		estatisticaList = new ArrayList<EstatisticaDistrito>();
		
		fichaDistritoList = fichaService.buscarFicha();
		int cont;
		int contadorTotal=0;
		
		for(int i=0;i<fichaDistritoList.size();i++) {
			cont=0;
			disResidencia = fichaDistritoList.get(i).getUtente().getDistrito();
			if(controleDistrito.contains(disResidencia)) {
				
			}else {
				controleDistrito.add(disResidencia);
				pesquisaList = fichaService.buscarFichasPorDistrito(disResidencia,uniorg,dataInicio, dataFim);
				cont = pesquisaList.size();
				contadorTotal+=pesquisaList.size();
				
				if(cont>0) {
					est = new EstatisticaDistrito();
					est.setDistritoResidencia(disResidencia);
					est.setQuantidade(cont);
					estatisticaList.add(est);
				}
				
				pesquisaList.clear();
				
			}
		}
		estatisticaModel = new ListModelList<EstatisticaDistrito>(estatisticaList); 
		lbxFichasDistrito.setModel(estatisticaModel);
		total_distrito.setValue("Total: "+contadorTotal);
		
		
	}
	
	public void pesquisarPorClassificacao() {
		numeroFicha = "";
		genero=null;
		tipoUte=null;
		estado=null;
		
		int contador=0;
		
		classList = classificacaoService.buscarClassificacao();
		classific = classList.get(0);
		pesquisaList = fichaService.buscarFichas(numeroFicha, uniorg, genero, classific, tipoUte, dataInicio, dataFim, estado);
		label_suspeitos.setValue(""+pesquisaList.size());
		contador+=pesquisaList.size();
		pesquisaList.clear();
		
		classific = classList.get(1);
		pesquisaList = fichaService.buscarFichas(numeroFicha, uniorg, genero, classific, tipoUte, dataInicio, dataFim, estado);
		label_confirmados.setValue(""+pesquisaList.size());
		contador+=pesquisaList.size();
		pesquisaList.clear();
		
		classific = classList.get(2);
		pesquisaList = fichaService.buscarFichas(numeroFicha, uniorg, genero, classific, tipoUte, dataInicio, dataFim, estado);
		label_testados.setValue(""+pesquisaList.size());
		contador+=pesquisaList.size();
		pesquisaList.clear();
		
		classific = classList.get(3);
		pesquisaList = fichaService.buscarFichas(numeroFicha, uniorg, genero, classific, tipoUte, dataInicio, dataFim, estado);
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
		estado=null;
		
		int contador=0;
		
		tiputList = tipoUtenteService.buscarTipoUtente();
		tipoUte = tiputList.get(0);
		pesquisaList = fichaService.buscarFichas(numeroFicha, uniorg, genero, classific, tipoUte, dataInicio, dataFim, estado);
		label_estudante.setValue(""+pesquisaList.size());
		contador+=pesquisaList.size();
		pesquisaList.clear();
		
		tiputList = tipoUtenteService.buscarTipoUtente();
		tipoUte = tiputList.get(1);
		pesquisaList = fichaService.buscarFichas(numeroFicha, uniorg, genero, classific, tipoUte, dataInicio, dataFim, estado);
		label_docente.setValue(""+pesquisaList.size());
		contador+=pesquisaList.size();
		pesquisaList.clear();
		
		tiputList = tipoUtenteService.buscarTipoUtente();
		tipoUte = tiputList.get(2);
		pesquisaList = fichaService.buscarFichas(numeroFicha, uniorg, genero, classific, tipoUte, dataInicio, dataFim, estado);
		label_cta.setValue(""+pesquisaList.size());
		contador+=pesquisaList.size();
		pesquisaList.clear();
		
		tiputList = tipoUtenteService.buscarTipoUtente();
		tipoUte = tiputList.get(3);
		pesquisaList = fichaService.buscarFichas(numeroFicha, uniorg, genero, classific, tipoUte, dataInicio, dataFim, estado);
		label_investigador.setValue(""+pesquisaList.size());
		contador+=pesquisaList.size();
		pesquisaList.clear();
		
		total_tipo_utente.setValue("Total: "+contador);
		
		tiputList.clear();
		
	}
	
	public void pesquisarPorGenero() {
		numeroFicha = "";
		classific=null;
		tipoUte=null;
		estado=null;
		
		int contador=0;
		String masculino = "Masculino";
		pesquisaList = fichaService.buscarFichas(numeroFicha, uniorg, masculino, classific, tipoUte, dataInicio, dataFim, estado);
		label_masculino.setValue(""+pesquisaList.size());
		contador+=pesquisaList.size();
		pesquisaList.clear();
		
		String feminino = "Feminino";
		pesquisaList = fichaService.buscarFichas(numeroFicha, uniorg, feminino, classific, tipoUte, dataInicio, dataFim, estado);
		label_feminino.setValue(""+pesquisaList.size());
		contador+=pesquisaList.size();
		pesquisaList.clear();
		
		total_genero.setValue("Total: "+contador);
		
	}
	
	public void buscarPorSituacao() {
		numeroFicha = "";
		classific=null;
		tipoUte=null;
		genero=null;
		
		
		int contador=0;
		String Recuperado = "Recuperado";
		pesquisaList = fichaService.buscarFichas(numeroFicha, uniorg, genero, classific, tipoUte, dataInicio, dataFim, Recuperado);
		label_recuperados.setValue(""+pesquisaList.size());
		contador+=pesquisaList.size();
		pesquisaList.clear();
		
		String Internado = "Internado";
		pesquisaList = fichaService.buscarFichas(numeroFicha, uniorg, genero, classific, tipoUte, dataInicio, dataFim, Internado);
		label_internados.setValue(""+pesquisaList.size());
		contador+=pesquisaList.size();
		pesquisaList.clear();
		
		String Indeterminado = "Indeterminado";
		pesquisaList = fichaService.buscarFichas(numeroFicha, uniorg, genero, classific, tipoUte, dataInicio, dataFim, Indeterminado);
		label_indeterminados.setValue(""+pesquisaList.size());
		contador+=pesquisaList.size();
		pesquisaList.clear();
		
		String obito = "Óbito";
		pesquisaList = fichaService.buscarFichas(numeroFicha, uniorg, genero, classific, tipoUte, dataInicio, dataFim, obito);
		label_obitos.setValue(""+pesquisaList.size());
		contador+=pesquisaList.size();
		pesquisaList.clear();
		
		total_situacao.setValue("Total: "+contador);
		
	}
	
	public void buscarFichas() {
		
		if(user.getRoles().toString().equals("[Admin]")) {
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
	
	public void onClick$btn_pesquisar() {
		
		if(user.getRoles().toString().equals("[Admin]")) {
			if(cbx_unidade.getSelectedItem()!=null) {
				uniorg = cbx_unidade.getSelectedItem().getValue();
			}
		}
		
		
		
		if(dtb_dataInicio.getValue()!=null && dtb_Fim.getValue()!=null) {
			
			dataInicio = dtb_dataInicio.getValue();
			dataFim = dtb_Fim.getValue();
			
		}
		pesquisarPorClassificacao();
		pesquisarPorGenero();
		pesquisarPorTipoUtente();
		pesquisarPorDistrito();
		buscarPorSituacao();
	}
	
	//imprimir por classificação
	public void onClick$btn_imprimir() throws JRException{
		
		if(user.getRoles().toString().equals("[Admin]")) {
			if(cbx_unidade.getSelectedItem()!=null) {
				uniorg = cbx_unidade.getSelectedItem().getValue();
			}
		}
	 
		if(dtb_dataInicio.getValue()!=null && dtb_Fim.getValue()!=null) {
			
			dataInicio = dtb_dataInicio.getValue();
			dataFim = dtb_Fim.getValue();
			
		}
	
		fichaList = fichaService.buscarFichas(numeroFicha, uniorg, genero, classific, tipoUte, dataInicio, dataFim, estado);
	
		
		//Manipulações
				List<Classificacao> controleClassifcacao = new ArrayList<Classificacao>();
				List<Classificacao> finalClassifcacao = new ArrayList<Classificacao>();
				
				for(int i=0;i<fichaList.size();i++) {
					
					controleClassifcacao.add(fichaList.get(i).getClassificacao());
					
				}
				
				for(int i=0;i<controleClassifcacao.size();i++) {
					
					
					Classificacao clas = controleClassifcacao.get(i);
					
					if(finalClassifcacao.contains(clas)) {
						
						
					}else {
						long quantidade = Collections.frequency(controleClassifcacao, clas);
						
						clas.setUserCreated(quantidade);
						finalClassifcacao.add(clas);
						
					}
					
					
					
				}
				
				
				
		//Fim manipulações
		
		if (fichaList.isEmpty()) {			
			Clients.showNotification("Informação Vazia", "info", win, "middle_center", 3000);
		} else {
	
			MasterRep mas = new MasterRep();
			Map<String, Object> map = new HashMap<String, Object>();
			int total = fichaList.size();
			
			final Execution ex = Executions.getCurrent();
			InputStream inputV = ex.getDesktop().getWebApp().getResourceAsStream("/images/moz.png");
			map.put("imagemLogo", inputV);
			map.put("total", total);
			map.put("titiloUnidade", titiloUnidade);
			String realPath = ex.getDesktop().getWebApp().getRealPath("/reports/");
			map.put("SUBREPORT_DIR", realPath);
			mas.imprimir("/reports/ficha_classificacao.jrxml", finalClassifcacao, map, win);
		}
		
		fichaList.clear();
		
		
		}
	
	public void onClick$btn_imprimir_tipo_utente() throws JRException{
		
		
		
		if(user.getRoles().toString().equals("[Admin]")) {
			if(cbx_unidade.getSelectedItem()!=null) {
				uniorg = cbx_unidade.getSelectedItem().getValue();
			}
		}
	 
		if(dtb_dataInicio.getValue()!=null && dtb_Fim.getValue()!=null) {
			
			dataInicio = dtb_dataInicio.getValue();
			dataFim = dtb_Fim.getValue();
			
		}
	
		fichaList = fichaService.buscarFichas(numeroFicha, uniorg, genero, classific, tipoUte, dataInicio, dataFim, estado);
	
		//Manipulações
				List<TipoUtente> controleTipoUtente = new ArrayList<TipoUtente>();
				List<TipoUtente> finalTipoUtente = new ArrayList<TipoUtente>();
				
				for(int i=0;i<fichaList.size();i++) {
					
					controleTipoUtente.add(fichaList.get(i).getUtente().getTipo_utente());
					
				}
				
				for(int i=0;i<controleTipoUtente.size();i++) {
					
					TipoUtente tipo = controleTipoUtente.get(i);
					
					if(finalTipoUtente.contains(tipo)) {
						
						
					}else {
						long quantidade = Collections.frequency(controleTipoUtente, tipo);
						
						tipo.setUserCreated(quantidade);
					    finalTipoUtente.add(tipo);
						
					}
					
					
					
				}
				
				
				
	   //Fim manipulações
	 
	
	
	
	
		if (fichaList.isEmpty()) {			
			Clients.showNotification("Informação Vazia", "info", win, "middle_center", 3000);
		} else {
	
			MasterRep mas = new MasterRep();
			Map<String, Object> map = new HashMap<String, Object>();
			int total = fichaList.size();
			
			final Execution ex = Executions.getCurrent();
			InputStream inputV = ex.getDesktop().getWebApp().getResourceAsStream("/images/moz.png");
			map.put("imagemLogo", inputV);
			map.put("total", total);
			map.put("titiloUnidade", titiloUnidade);
			String realPath = ex.getDesktop().getWebApp().getRealPath("/reports/");
			map.put("SUBREPORT_DIR", realPath);
			mas.imprimir("/reports/ficha_categoria.jrxml", finalTipoUtente, map, win);
		}
		
		fichaList.clear();
		
		
	}
	
	public void onClick$btn_imprimir_distrito() throws JRException{
		
		
		if(user.getRoles().toString().equals("[Admin]")) {
			if(cbx_unidade.getSelectedItem()!=null) {
				uniorg = cbx_unidade.getSelectedItem().getValue();
			}
		}
	 
		if(dtb_dataInicio.getValue()!=null && dtb_Fim.getValue()!=null) {
			
			dataInicio = dtb_dataInicio.getValue();
			dataFim = dtb_Fim.getValue();
			
		}
	
		fichaList = fichaService.buscarFichas(numeroFicha, uniorg, genero, classific, tipoUte, dataInicio, dataFim, estado);
		
	 
		//Manipulações
		
		controleDistrito = new ArrayList<Distrito>();
		List<Distrito> finalDistrito = new ArrayList<Distrito>();
		
		for(int i=0;i<fichaList.size();i++) {
			
			controleDistrito.add(fichaList.get(i).getUtente().getDistrito());
			
			
			
		}
		
		for(int i=0;i<controleDistrito.size();i++) {
			
			Distrito dist = controleDistrito.get(i);
			
			if(finalDistrito.contains(dist)) {
				
				
			}else {
				long quantidade = Collections.frequency(controleDistrito, dist);
				
			    dist.setUserCreated(quantidade);
				finalDistrito.add(dist);
				
			}
			
			
			
		}
		
		
		
	   //Fim manipulações
	
	
		if (fichaList.isEmpty()) {			
			Clients.showNotification("Informação Vazia", "info", win, "middle_center", 3000);
		} else {
	
			MasterRep mas = new MasterRep();
			Map<String, Object> map = new HashMap<String, Object>();
			int total = fichaList.size();
			
			final Execution ex = Executions.getCurrent();
			InputStream inputV = ex.getDesktop().getWebApp().getResourceAsStream("/images/moz.png");
			map.put("imagemLogo", inputV);
			map.put("total", total);
			map.put("titiloUnidade", titiloUnidade);
			String realPath = ex.getDesktop().getWebApp().getRealPath("/reports/");
			map.put("SUBREPORT_DIR", realPath);
			mas.imprimir("/reports/ficha_distrito.jrxml", finalDistrito, map, win);
		}
		
		fichaList.clear();
		
		
	}
	
	public void onClick$btn_imprimir_genero() throws JRException{
		
		
		
		if(user.getRoles().toString().equals("[Admin]")) {
			if(cbx_unidade.getSelectedItem()!=null) {
				uniorg = cbx_unidade.getSelectedItem().getValue();
			}
		}
	 
		if(dtb_dataInicio.getValue()!=null && dtb_Fim.getValue()!=null) {
			
			dataInicio = dtb_dataInicio.getValue();
			dataFim = dtb_Fim.getValue();
			
		}
	
		fichaList = fichaService.buscarFichas(numeroFicha, uniorg, genero, classific, tipoUte, dataInicio, dataFim, estado);
	
		//Manipulações
			List<String> Sexo = new ArrayList<String>();
			int masculino = 0;
			int feminino = 0;
			
			for(int i=0;i<fichaList.size();i++) {
				Sexo.add(fichaList.get(i).getUtente().getGenero());
			}
			
			
			masculino = Collections.frequency(Sexo, "Masculino");
			feminino = Collections.frequency(Sexo, "Feminino");
			
	   //Fim manipulações
	 
	
	
	
	
		if (fichaList.isEmpty()) {			
			Clients.showNotification("Informação Vazia", "info", win, "middle_center", 3000);
		} else {
	
			MasterRep mas = new MasterRep();
			Map<String, Object> map = new HashMap<String, Object>();
			int total = fichaList.size();
			
			final Execution ex = Executions.getCurrent();
			InputStream inputV = ex.getDesktop().getWebApp().getResourceAsStream("/images/moz.png");
			map.put("imagemLogo", inputV);
			map.put("total", total);
			map.put("masculino", masculino);
			map.put("feminino", feminino);
			map.put("titiloUnidade", titiloUnidade);
			String realPath = ex.getDesktop().getWebApp().getRealPath("/reports/");
			map.put("SUBREPORT_DIR", realPath);
			mas.imprimir("/reports/ficha_sexo.jrxml", fichaList, map, win);
		}
		
		fichaList.clear();
		
		
	}
	
	
	public void onClick$btn_imprimir_situacao() throws JRException{
		
		
		
		if(user.getRoles().toString().equals("[Admin]")) {
			if(cbx_unidade.getSelectedItem()!=null) {
				uniorg = cbx_unidade.getSelectedItem().getValue();
			}
		}
	 
		if(dtb_dataInicio.getValue()!=null && dtb_Fim.getValue()!=null) {
			
			dataInicio = dtb_dataInicio.getValue();
			dataFim = dtb_Fim.getValue();
			
		}
	
		fichaList = fichaService.buscarFichas(numeroFicha, uniorg, genero, classific, tipoUte, dataInicio, dataFim, estado);
	
	 
		//Manipulações
				List<String> situacaoActual = new ArrayList<String>();
				int recuperados = 0;
				int internados = 0;
				int indeterminados = 0;
				int obitos = 0;
				
				for(int i=0;i<fichaList.size();i++) {
					situacaoActual.add(fichaList.get(i).getEstado());
				}
				
				
				recuperados = Collections.frequency(situacaoActual, "Recuperado");
				internados = Collections.frequency(situacaoActual, "Internado");
				indeterminados = Collections.frequency(situacaoActual, "Indeterminado");
				obitos = Collections.frequency(situacaoActual, "Óbito");
				
		   //Fim manipulações
		
			if (fichaList.isEmpty()) {			
				Clients.showNotification("Informação Vazia", "info", win, "middle_center", 3000);
			} else {
		
				MasterRep mas = new MasterRep();
				Map<String, Object> map = new HashMap<String, Object>();
				int total = fichaList.size();
				
				final Execution ex = Executions.getCurrent();
				InputStream inputV = ex.getDesktop().getWebApp().getResourceAsStream("/images/moz.png");
				map.put("imagemLogo", inputV);
				map.put("total", total);
				map.put("recuperados", recuperados);
				map.put("internados", internados);
				map.put("indeterminados", indeterminados);
				map.put("obitos", obitos);
				map.put("titiloUnidade", titiloUnidade);
				String realPath = ex.getDesktop().getWebApp().getRealPath("/reports/");
				map.put("SUBREPORT_DIR", realPath);
				mas.imprimir("/reports/ficha_situacao.jrxml", fichaList, map, win);
			}
			
			fichaList.clear();
			
		
	}
	
	
	
	
	
	
	
	
	


}
