
package uem.mz.mint.vm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import uem.mz.mint.service.impl.LogServiceImpl;
import uem.mz.mint.util.Breadcrumb;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Ol;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class MainVM extends PagVM {

	private static final long serialVersionUID = 1L;

	@Wire("#mainlayout")
	private Div target;

	@Wire("#breadcrumb")
	private Ol ol;
	
	@Wire 
	private Div breadcrumb;

	@WireVariable
	private LogServiceImpl logService;

	@Wire
	private Image imgPfl;

	@Wire("#sidebar #imgPflSide")
	private Image imgPflSide;

	// SideBar Menus

	private String initPage;

	private String hoursPage;

	private String recruitPage;

	private String perfomPage;

	private String leavePage;

	private String trainPage;

	private String recPage;

	private String aproovPage;

	private String morePage;
	
	private List<String> links;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("dashboard.zul", target, map);

		links = new ArrayList<String>();
		//links.add("Painel Inicial");
		//links.add("Meu Painel Inicial");
		Breadcrumb.drawn(breadcrumb, "", links);
	

		//if (target != null) {

			//////////////////imgPflSide.invalidate();
		//}

	}
	
	

	@Init
	public void init() {
		menuReset();
		setInitPage("active");
		loggeduser = userService.getUser(authentication.getName());
		Executions.getCurrent().getDesktop().getSession()
				.setAttribute("utilizadorAutenticado", loggeduser);
			

	}
	
		
	@Command
	public void openAdministracao() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/administracao/administracao.zul", target, map);

		links = new ArrayList<String>();
		links.add("Administracao");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	@Command
	public void openOrganograma() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/organograma/organograma.zul", target, map);

		links = new ArrayList<String>();
		links.add("Organigrama");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	@Command
	public void openTabelaSalarialDashboard() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("/mint/src/main/webapp/views/tabela_salarial/tabela_salarial_dashboard.zul", target, map);

		links = new ArrayList<String>();
		links.add("Administracao");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
		
		
	@Command
    public void openHistorico() {
		
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/unidade/historico.zul", target, map);

		links = new ArrayList<String>();
		links.add("HISTORICO");
		Breadcrumb.drawn(breadcrumb, "", links);

		
	}
	
	@Command
	public void openFuncionario() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/funcionario/funcionario.zul", target, map);

		links = new ArrayList<String>();
		links.add("Administracao");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	@Command
	public void openParametrizacao() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parametrizacaoContratos/parametrizacao_contratos.zul", target, map);

		links = new ArrayList<String>();
		links.add("Parametrização");
		Breadcrumb.drawn(breadcrumb, "", links);
	}

	
	//Parametrizacao
	@Command
	public void openParametrizetion() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parametrizacao/parametrizacao.zul", target, map);

		links = new ArrayList<String>();
		links.add("Parametrizacao");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	@Command
	public void openHome() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("dashboard.zul", target, map);

		links = new ArrayList<String>();
		//links.add("Painel Inicial");
		//links.add("Meu Painel Inicial");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	@Command
	public void openHomeReitor() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/reitor/reitor.zul", target, map);

		links = new ArrayList<String>();
		links.add("Contratos");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	@Command
	public void openHomeUnidade() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/unidade/unidade.zul", target, map);

		links = new ArrayList<String>();
		links.add("Contratos");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	@Command
	public void openForm() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("/views/parametrizacao/pagRegistarFuncao.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Pagina Inicial", links);

		menuReset();
		setInitPage("active");
	}
	
	@Command
	public void openRegistarClasse() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("/views/parametrizacao/registar_classe.zul", target, map);

		links = new ArrayList<String>();
		links.add("Parametrizacao");
		drawnBreadcrumb("fa fa-sort", "Registar Classe", links);

		menuReset();
		setInitPage("active");
	}

	@Command
	public void sideBarMore() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/more.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void anexarDocumentos() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Pre-registo/upload_docs.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void registarEstudante() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions
				.createComponents("views/funcionario/create.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void universidade() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents(
				"views/Parametrizacao/registar_universidade.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void unidadeOrganica() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents(
				"views/Parametrizacao/registar_unidade_organica.zul", target,
				map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void registarCurso() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_curso.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void registarDisciplina() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents(
				"views/Parametrizacao/registar_disciplina.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarGrupos() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents(
				"views/Parametrizacao/registar_grupo.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void registarAgente() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_agente.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarInstituicao() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_instituicao.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	

	@Command
	public void registarPapel() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_papel.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void registarConta() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_conta.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void registarPais() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_pais.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void registarProvincia() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents(
				"views/Parametrizacao/registar_provincia.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void registarDistrito() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents(
				"views/Parametrizacao/registar_distrito.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void registarPreUniversitaria() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents(
				"views/Parametrizacao/registar_preuniversitarias.zul", target,
				map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void registarLocal() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registo_local.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarSubLocal() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_sub_local.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarSala() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_sala.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarTipoData() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_tipo_data.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void registarData() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_data.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	
	@Command
	public void registarLocalValidacao() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/Parametrizacao/registar_localValidacao.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	//Administracao
	@Command
	public void openUtilizador() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents(
				"views/administracao/registar_utilizador.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void openPerfil() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/administracao/gerir_perfil.zul", target,
				map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	@Command
	public void openPermissoes() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/administracao/gerir_permissoes.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}

	@Command
	public void openModulos() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/administracao/gerir_modulos.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);

		menuReset();
		setMorePage("active");
	}
	
	//Organigrama
	@Command
	public void openRegInst() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/organograma/registar_instituicao.zul", target, map);

		links = new ArrayList<String>();
		links.add("Registar Institui��o ");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	@Command
	public void openRegUniOrg() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/organograma/registar_unidade_organica.zul", target, map);

		links = new ArrayList<String>();
		links.add("Registar Unidade Org�nica");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	@Command
	public void openRegProv() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/organograma/registar_provincia.zul", target, map);

		links = new ArrayList<String>();
		links.add("Registar Prov�ncia");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	@Command
	public void openRegDep() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/organograma/registar_departamento.zul", target, map);

		links = new ArrayList<String>();
		links.add("Registar Departamento");
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
	
	@Command
	public void openTabelaSalarial() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/tabela_salarial/ciclo.zul", target, map);

		links = new ArrayList<String>();
		links.add("Configurar Tabela Salarial");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	@Command
	public void openRegistarFuncionario() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/funcionario/registar_funcionario.zul", target, map);

		links = new ArrayList<String>();
		links.add("Registar Funcionário");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	
	
	private void menuReset() {

		setInitPage("");
		setHoursPage("");
		setRecruitPage("");
		setRecPage("");
		setPerfomPage("");
		setLeavePage("");
		setTrainPage("");
		setAproovPage("");
		setMorePage("");
	}

	public String getInitPage() {
		return initPage;
	}

	public void setInitPage(String initPage) {
		this.initPage = initPage;
	}

	public String getHoursPage() {
		return hoursPage;
	}

	public void setHoursPage(String hoursPage) {
		this.hoursPage = hoursPage;
	}

	public String getRecruitPage() {
		return recruitPage;
	}

	public void setRecruitPage(String recruitPage) {
		this.recruitPage = recruitPage;
	}

	public String getPerfomPage() {
		return perfomPage;
	}

	public void setPerfomPage(String perfomPage) {
		this.perfomPage = perfomPage;
	}

	public String getLeavePage() {
		return leavePage;
	}

	public void setLeavePage(String leavePage) {
		this.leavePage = leavePage;
	}

	public String getTrainPage() {
		return trainPage;
	}

	public void setTrainPage(String trainPage) {
		this.trainPage = trainPage;
	}

	public String getRecPage() {
		return recPage;
	}

	public void setRecPage(String recPage) {
		this.recPage = recPage;
	}

	public String getAproovPage() {
		return aproovPage;
	}

	public void setAproovPage(String aproovPage) {
		this.aproovPage = aproovPage;
	}

	public String getMorePage() {
		return morePage;
	}

	public void setMorePage(String morePage) {
		this.morePage = morePage;
	}
}