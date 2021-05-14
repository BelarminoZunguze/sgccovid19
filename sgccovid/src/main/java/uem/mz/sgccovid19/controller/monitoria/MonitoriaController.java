package uem.mz.sgccovid19.controller.monitoria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import uem.mz.sgccovid19.entity.Ficha;
import uem.mz.sgccovid19.entity.UnidadeOrganica;
import uem.mz.sgccovid19.entity.administracao.User;
import uem.mz.sgccovid19.entity.monitoria.FichaMonitoria;
import uem.mz.sgccovid19.service.FichaMonitoriaService;
import uem.mz.sgccovid19.service.FichaService;
import uem.mz.sgccovid19.service.UnidadeOrganicaService;
import uem.mz.sgccovid19.util.Breadcrumb;
import uem.mz.sgccovid19.util.showClientNotification;

public class MonitoriaController extends GenericForwardComposer{
	
	private Window win;
	private Div pesquisar;
	
	private showClientNotification cli = new showClientNotification();
	
	private List<String> links;
	@Wire 
	private Div breadcrumb;
	
	private Div target;
	
	private User user;
	
	private Textbox txt_nrFicha;
	
	private FichaService fichaService;
	private List<Ficha> fichaList;
	private ListModelList<Ficha> fichaModel;
	
	private Listbox lbxFichas;
	
	private FichaMonitoriaService fichaMonitoriaService;
	private List<FichaMonitoria> fichMonList;
	private List<FichaMonitoria> pesquisaList;
	private ListModelList<FichaMonitoria> fichMonModel;
	
	private UnidadeOrganicaService unidadeOrganicaService;
	private List<UnidadeOrganica> uniOrgList;
	private ListModelList<UnidadeOrganica> uniOrgModel;
	
	private Combobox cbx_unidade;
	
	private FichaMonitoria fichMon;
	
	
	private UnidadeOrganica uniorg;
	
	private Label total_resultados;
	
	private String numeroFicha;
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		user = (User) Executions.getCurrent().getDesktop().getSession().getAttribute("utilizadorAutenticado");
		
		target = (Div) Executions.getCurrent().getArg().get("target");
		
		fichaMonitoriaService = (FichaMonitoriaService) SpringUtil.getBean("fichaMonitoriaService");
		
		unidadeOrganicaService = (UnidadeOrganicaService) SpringUtil.getBean("unidadeOrganicaService");
		
		
		}
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		if(user.getId()==1) {
			buscarUnidadeOrganica();
		}
		
		buscarFichas();
		
	}
	
	private void buscarUnidadeOrganica(){    	  
	  	  uniOrgList = unidadeOrganicaService.buscarUnidadeOrganica();
	  	  uniOrgModel = new ListModelList<UnidadeOrganica>(uniOrgList);
	  	  cbx_unidade.setModel(uniOrgModel);    	  
	}
	
	
	public void buscarFichas() {
		
		if(user.getId()==1) {
			fichMonList = fichaMonitoriaService.buscarFichaMonitoria();
			
		} else {
			fichMonList = fichaMonitoriaService.buscarFichasMonPorUnidade(user.getUnidade());
			
		}
		fichMonModel = new ListModelList<FichaMonitoria>(fichMonList);
		lbxFichas.setModel(fichMonModel);
		total_resultados.setValue("Total de Resultados: "+fichMonList.size());
		fichMonList.clear();
		
	}
	
	public void onClick$btn_pesquisar() {
		numeroFicha = txt_nrFicha.getValue();
		
		if(user.getId()==1) {
			if(cbx_unidade.getSelectedItem()!=null) {
				uniorg = cbx_unidade.getSelectedItem().getValue();
			}
		} else {uniorg=user.getUnidade();}
		
		
		pesquisaList = fichaMonitoriaService.buscarFichaMonitoria(numeroFicha, uniorg);
		fichMonModel = new ListModelList<FichaMonitoria>(pesquisaList);
		lbxFichas.setModel(fichMonModel);
		total_resultados.setValue("Total de Resultados: "+pesquisaList.size());
		pesquisaList.clear();
	}
	
	public void onClickApagar(ForwardEvent evt){
		
		fichMon = (FichaMonitoria) evt.getData();
		
		Messagebox.show("Tem a certeza que pretende apagar?", "Confirm Dialog", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
		    public void onEvent(Event evt) throws InterruptedException {
		        if (evt.getName().equals("onOK")) {
		            alert("Ficha apagada com sucesso!");
		            //primeiro apagar respostas que dependem dessa ficha monitoria
		            /*fichaMonitoriaService.delete(fichaMon);
		            buscarFichas();
		            */
		        }  
		    }
		});
		
		
		
	}
	
	
	
	public void onClick$btn_nova_ficha_monitoria() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/monitoria/comunicacao.zul",
				target, map);

		
		links = new ArrayList<String>();
		links.add("Nova Ficha");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	public void onClickVerFicha(ForwardEvent evt){
    	
		fichMon = (FichaMonitoria) evt.getData();
		
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("fichMon", fichMon);
		target.getChildren().clear();
		Executions.createComponents("views/monitoria/detalhes_monitoria.zul", target, map);

		links = new ArrayList<String>();
		links.add("Detalhes da Ficha");
		Breadcrumb.drawn(breadcrumb, "", links);
		
	}
	
	
	
	

}
