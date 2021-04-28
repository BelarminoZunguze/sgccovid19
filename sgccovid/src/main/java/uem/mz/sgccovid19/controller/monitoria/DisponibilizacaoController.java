package uem.mz.sgccovid19.controller.monitoria;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import uem.mz.sgccovid19.entity.Actividade;
import uem.mz.sgccovid19.entity.Ficha;
import uem.mz.sgccovid19.entity.Indicador;
import uem.mz.sgccovid19.entity.administracao.User;
import uem.mz.sgccovid19.entity.monitoria.FichaMonitoria;
import uem.mz.sgccovid19.entity.monitoria.FichaMonitoriaRespostas;
import uem.mz.sgccovid19.service.ActividadeService;
import uem.mz.sgccovid19.service.FichaMonitoriaRespostasService;
import uem.mz.sgccovid19.service.FichaMonitoriaService;
import uem.mz.sgccovid19.service.FichaService;
import uem.mz.sgccovid19.service.IndicadorService;
import uem.mz.sgccovid19.util.Breadcrumb;
import uem.mz.sgccovid19.util.showClientNotification;

public class DisponibilizacaoController extends GenericForwardComposer{
	
	private Window win;
	private Div pesquisar;
	
	private showClientNotification cli = new showClientNotification();
	
	private List<String> links;
	@Wire 
	private Div breadcrumb;
	
	private Div target;
	
	private User user;
	
	private FichaService fichaService;
	private List<Ficha> fichaList;
	private ListModelList<Ficha> fichaModel;
	
	
	private ActividadeService actividadeSerice;
	private List<Actividade> actividadeList;
	private ListModelList<Actividade> actividadeModel;
	
	private IndicadorService indicadorService;
	private List<Indicador> indicadorList;
	private ListModelList<Indicador> indicadorModel;
	
	private FichaMonitoriaService fichaMonitoriaService;
	private List<FichaMonitoria> fichMonList;
	private ListModelList<FichaMonitoria> fichMonModel;
	private FichaMonitoria fichMon;
	
	private FichaMonitoriaRespostasService fichRespostasService;
	private List<FichaMonitoriaRespostas> fichRespList;
	private ListModelList<FichaMonitoriaRespostas> fichRespModel;
	private FichaMonitoriaRespostas fichResp;
	
	
	
	private Listbox lbxFichas;
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		user = (User) Executions.getCurrent().getDesktop().getSession().getAttribute("utilizadorAutenticado");
		
		target = (Div) Executions.getCurrent().getArg().get("target");
		
		fichaService = (FichaService) SpringUtil.getBean("fichaService");
		
		actividadeSerice = (ActividadeService) SpringUtil.getBean("actividadeService");
		
		
		indicadorService = (IndicadorService) SpringUtil.getBean("indicadorService");
		
		fichaMonitoriaService = (FichaMonitoriaService) SpringUtil.getBean("fichaMonitoriaService");
		
		fichMon = (FichaMonitoria) Executions.getCurrent().getArg().get("fichMon");
		
		fichRespostasService = (FichaMonitoriaRespostasService) SpringUtil.getBean("fichRespostasService");
		
		//fichRespList = (List<FichaMonitoriaRespostas>) Executions.getCurrent().getArg().get("fichRespList");
		
		}
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		//buscarActividade();
		//buscarIndicador();
		buscarIndicadorPorId(); 
		
	}
	
	
	
	public void buscarActividade() {
		actividadeList = actividadeSerice.buscarActividade();
		actividadeModel = new ListModelList<Actividade>(actividadeList);
		//lbxFichas.setModel(actividadeModel);
	}
	
	/*
	public void buscarIndicador() {
		indicadorList = indicadorService.buscarIndicador();
		indicadorModel = new ListModelList<Indicador>(indicadorList);
		lbxFichas.setModel(indicadorModel);
	}
	*/
	
	
	public void buscarIndicadorPorId() {
		indicadorList = indicadorService.buscarIndicadorPorId((long)(2));
		indicadorModel = new ListModelList<Indicador>(indicadorList);
		lbxFichas.setModel(indicadorModel);
	}
	
	public void onCheckTestar(ForwardEvent evt){
    	
    	Radio rdb = (Radio)evt.getOrigin().getTarget();
    	
    	Listitem litem = (Listitem)rdb.getParent().getParent().getParent();
    	
    	Indicador selected = (Indicador)litem.getValue();
    	
    	fichResp = new FichaMonitoriaRespostas();
    	
		fichResp.setIndicador(selected);
		
		fichResp.setFichaMonitoria(fichMon);
		
		if(rdb.getLabel().equals("Sim")) {
			
			fichResp.setPontoSituacao(true);
			
		} else {fichResp.setPontoSituacao(false);}
		
		fichResp.setUserCreated(user.getId());
		fichResp.setUserUpdated(user.getId());
		
		fichRespostasService.saveOrUpdate(fichResp);
		
	}
    
    
    public void onChangingTestar(InputEvent e) {
		
    	String obs = (String)e.getValue();
    	
    	Textbox text = (Textbox)e.getTarget();
    	
    	Listitem litem = (Listitem)text.getParent().getParent();
    	
    	Indicador selected = (Indicador)litem.getValue();
    	
    	fichRespList = fichRespostasService.buscarFichasPorIndicador(selected.getId());
    	
    	fichResp = fichRespList.get(fichRespList.size()-1);
    	
    	System.out.println("ficha: monitoria: "+fichResp.getFichaMonitoria().getId());
    	
    	fichResp.setObservacoes(obs);
    	
    	fichRespostasService.update(fichResp);
    	
    	
	}
	
	
	
	
   public void onClick$btn_proximo2() {
	   
	   
	   fichMon.setNumeroFicha("M"+fichMon.getId());
	   fichaMonitoriaService.saveOrUpdate(fichMon);
	   
   	
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("fichMon", fichMon);
		target.getChildren().clear();
		Executions.createComponents("views/monitoria/reducao_contactos.zul", target, map);

		links = new ArrayList<String>();
		links.add("Redução de Contactos Interpessoais");
		Breadcrumb.drawn(breadcrumb, "", links);

		
	}
   
   
   
   

}
