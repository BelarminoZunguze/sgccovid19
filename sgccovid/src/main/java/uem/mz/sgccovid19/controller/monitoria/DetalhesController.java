package uem.mz.sgccovid19.controller.monitoria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
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

public class DetalhesController extends GenericForwardComposer{
	

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
	
	private Textbox txt_constrangimentos;
	private Textbox txt_recomendacoes;
	private Textbox txt_desafios;
	private Textbox txt_quantPessoas;
	private Textbox txt_quantNiveis;
	
	private Label label_constrangimentos;
	private Label label_recomendacoes;
	private Label label_desafios;
	private Label label_quant_pessoas;
	private Label label_quant_niveis;
	
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
		
		fichRespostasService= (FichaMonitoriaRespostasService) SpringUtil.getBean("fichRespostasService");
		//fichRespList = (List<FichaMonitoriaRespostas>) Executions.getCurrent().getArg().get("fichRespList");
		
		
		}
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		
		buscarTodosDados(); 
		
	}
	
	
	
	public void buscarTodosDados() {
		
		fichRespList = fichRespostasService.buscarFichaMonitoriaRespostasPorId(fichMon.getId());
		fichRespModel = new ListModelList<FichaMonitoriaRespostas>(fichRespList);
		lbxFichas.setModel(fichRespModel);
		label_constrangimentos.setValue(fichMon.getConstrangimentos());
		label_recomendacoes.setValue(fichMon.getRecomendacoes());
		label_desafios.setValue(fichMon.getDesafios());
		label_quant_pessoas.setValue(fichMon.getNumPessoas());
		label_quant_niveis.setValue(fichMon.getNumNiveis());
	}
	
	
	
	
   
   public void onClick$btn_confirmar_ficha() {
	   
	   fichMon.setConstrangimentos(txt_constrangimentos.getValue());
	   fichMon.setRecomendacoes(txt_recomendacoes.getValue());
	   fichMon.setDesafios(txt_desafios.getValue());
	   fichMon.setNumPessoas(txt_quantPessoas.getValue());
	   fichMon.setNumNiveis(txt_quantNiveis.getValue());
	   fichMon.setUnidade(user.getUnidade());
	   
	   fichaMonitoriaService.saveOrUpdate(fichMon);
	   
	    cli.Msg("Ficha gravada com sucesso", win);
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/monitoria/ficha_monitoria.zul", target, map);

		links = new ArrayList<String>();
		links.add("Confirmar");
		Breadcrumb.drawn(breadcrumb, "", links);

		
	}
   

}
