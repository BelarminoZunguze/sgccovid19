package uem.mz.sgccovid19.vm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;

import uem.mz.sgccovid19.util.Breadcrumb;
import uem.mz.sgccovid19.entity.Ficha;
import uem.mz.sgccovid19.entity.UnidadeOrganica;
import uem.mz.sgccovid19.entity.administracao.User;
import uem.mz.sgccovid19.entity.monitoria.FichaMonitoria;
import uem.mz.sgccovid19.service.FichaMonitoriaService;
import uem.mz.sgccovid19.service.FichaService;
import uem.mz.sgccovid19.service.UnidadeOrganicaService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ParametrizacaoVM extends AbstractVM{
	
	@Wire("#mainlayout")
	private Div target;

	@Wire
	private Div main;

	@Wire 
	private Div breadcrumb;

	private List<String> links;
	
	private FichaService fichaService;
	private List<Ficha> fichaList;
	private List<Ficha> UnidadefichaList;
	
	private FichaMonitoriaService fichaMonitoriaService;
	private List<FichaMonitoria> fichMonList;
	private List<FichaMonitoria> UnidadefichMonList;
	
	private UnidadeOrganicaService unidadeOrganicaService;
	private List<UnidadeOrganica> uniOrgList;
	private UnidadeOrganica uniorg;
	
	private User user;
	
	private int numeroUnidade; 
	
	private int numero;
	private int numeroMonitoria;
	private int numeroMonUnidade;
	
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) throws IOException {

		Selectors.wireComponents(view, this, false);
		
		user = (User) Executions.getCurrent().getDesktop().getSession().getAttribute("utilizadorAutenticado");
		//User loggeduser = userService.getUser(authentication.getName());
		
		
		if(user.getRoles().toString().equals("[Admin]")) {
			
			numero = buscarFichas();
		} else {
			numeroUnidade = buscarFichasUnidade();
		}
		
		if(user.getRoles().toString().equals("[Admin]")) {
			numeroMonitoria = buscarFichasMonitoria();
		} else {
			numeroMonUnidade = buscarFichasMonitoriaUnidade();
		}
		
	}
	
	@Init(superclass = true)
	public void init(@ContextParam(ContextType.VIEW) Component view) {
		
		this.ui = view;

	}
	
	@Command
	public void openParametrizetion() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parametrizacao/parametrizacao.zul", target, map);

	}
	
	
	
	
	
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	
	public int getNumeroMonitoria() {
		return numeroMonitoria;
	}

	public void setNumeroMonitoria(int numeroMonitoria) {
		this.numeroMonitoria = numeroMonitoria;
	}
	
	
	public int getNumeroUnidade() {
		return numeroUnidade;
	}

	public void setNumeroUnidade(int numeroUnidade) {
		this.numeroUnidade = numeroUnidade;
	}

	public int getNumeroMonUnidade() {
		return numeroMonUnidade;
	}

	public void setNumeroMonUnidade(int numeroMonUnidade) {
		this.numeroMonUnidade = numeroMonUnidade;
	}

	
	public int buscarFichasUnidade() {
		fichaService = (FichaService) SpringUtil.getBean("fichaService");
		UnidadefichaList = fichaService.buscarFichasPorUnidade(user.getUnidade());
		return UnidadefichaList.size();
	}

	public int buscarFichas() {
		fichaService = (FichaService) SpringUtil.getBean("fichaService");
		fichaList = fichaService.buscarFicha();
		return fichaList.size();
	}
	
	public int buscarFichasMonitoria() {
		fichaMonitoriaService = (FichaMonitoriaService) SpringUtil.getBean("fichaMonitoriaService");
		fichMonList = fichaMonitoriaService.buscarFichaMonitoria();
		return fichMonList.size();
	}
	
	public int buscarFichasMonitoriaUnidade() {
		fichaMonitoriaService = (FichaMonitoriaService) SpringUtil.getBean("fichaMonitoriaService");
		UnidadefichMonList = fichaMonitoriaService.buscarFichasMonPorUnidade(user.getUnidade());
		return UnidadefichMonList.size();
	}
	
	
	@Command
	public void openFicha() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/ficha_investigacao/ficha_investigacao.zul", target, map);
		
		
	}
	
	@Command
	public void openEstatisticaFicha() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/ficha_investigacao/estatistica_ficha.zul", target, map);
		
		
	}
	
	@Command
	public void openEstatisticas() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/ficha_investigacao/estatisticas.zul", target, map);
	
		
	}
	
	@Command
	public void openMonitoria() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/monitoria/ficha_monitoria.zul", target, map);

	}
	
	@Command
	public void openEstatisticaMonitoria() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/monitoria/estatistica_monitoria.zul", target, map);

	}
	
	@Command
	public void openAdministracao() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/administracao/administracao.zul", target, map);

		
	}
	
	
	
	@Command
	public void openRegClass() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parametrizacao/pagRegistarClasse.zul", target, map);

		
	}
	
	
	
	@Command
	public void openRegInst() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/organograma/registar_instituicao.zul", target, map);

		
	}
	
	@Command
	public void openRegUniOrg() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/organograma/registar_unidade_organica.zul", target, map);

		
	}
	
	@Command
	public void openRegProv() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/organograma/registar_provincia.zul", target, map);

		
	}
	
	@Command
	public void openRegDep() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/organograma/registar_departamento.zul", target, map);

		
	}
	
	@Command
	public void openVistoDirectorUnidade() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/DirectorUnidade/vistodirectorunidade.zul", target, map);

		links = new ArrayList<String>();
		links.add("Listar Contratos");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	@Command
	public void openVistoAgenteDRH() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/DRHCentral/vistoagenteDRH.zul", target, map);

		links = new ArrayList<String>();
		links.add("Listar Contratos");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	@Command
	public void openContraDocente() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/DRHunidade/contratodocente.zul", target, map);

		links = new ArrayList<String>();
		links.add("Contrato Docente");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	@Command
	public void openEnquadrarContrato() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/DRHUnidade/enquadrarcontrato.zul", target, map);

		links = new ArrayList<String>();
		links.add("Enquadrar Contrato");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	@Command
	public void openRegSet() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/organograma/registar_sector.zul", target, map);

		links = new ArrayList<String>();
		links.add("Registar Sector");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	

}
