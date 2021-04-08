package uem.mz.mint.vm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;

import uem.mz.mint.util.Breadcrumb;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class TabelaSalarialVM extends AbstractVM{
	
	@Wire("#mainlayout")
	private Div target;

	@Wire
	private Div main;

	@Wire 
	private Div breadcrumb;

	private List<String> links;
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) throws IOException {

		Selectors.wireComponents(view, this, false);		
		
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

		links = new ArrayList<String>();
		links.add("Parametrizacao");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	
	
	@Command
	public void openAdministracao() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/administracao/administracao.zul", target, map);

		links = new ArrayList<String>();
		links.add("Parametrizacao");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	@Command
	public void openOrganograma() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/organograma/organograma.zul", target, map);

		links = new ArrayList<String>();
		links.add("");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	@Command
	public void openRegClass() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parametrizacao/pagRegistarClasse.zul", target, map);

		links = new ArrayList<String>();
		links.add("Registar Classe");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	@Command
	public void openRegEscal() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parametrizacao/pagRegistarEscalao.zul", target, map);

		links = new ArrayList<String>();
		links.add("Registar Escal�o");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	@Command
	public void openGrupoSalarial() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parametrizacao/registar_grupo_salarial.zul", target, map);

		links = new ArrayList<String>();
		links.add("Registar Grupo Salarial");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	@Command
	public void openNivelAcademico() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parametrizacao/registar_nivel_academico.zul", target, map);

		links = new ArrayList<String>();
		links.add("Registar N�vel Acad�mico");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	
	@Command
	public void openGrauAcademico() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parametrizacao/registar_grau_academico.zul", target, map);

		links = new ArrayList<String>();
		links.add("Registar Grau Acad�mico");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	@Command
	public void openTabelaSalarial() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parametrizacao/registar_tipo_tabela_salarial.zul", target, map);

		links = new ArrayList<String>();
		links.add("Registar Tipo de Tabela Salarial");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	@Command
	public void openCarreira() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parametrizacao/registar_carreira.zul", target, map);

		links = new ArrayList<String>();
		links.add("Registar Carreira");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	@Command
	public void openCategoria() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parametrizacao/registar_categoria.zul", target, map);

		links = new ArrayList<String>();
		links.add("Registar Categoria");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	@Command
	public void openDecreto(){
		
		
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parametrizacao/pagRegistarDecreto.zul", target, map);

		links = new ArrayList<String>();
		links.add("Registar Decreto");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	@Command
	public void openSuplimento(){
		
		
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parametrizacao/registar_suplimento.zul", target, map);

		links = new ArrayList<String>();
		links.add("Registar Suplimento");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	@Command
	public void openRegTabFunc(){
		
		
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parametrizacao/tabela_funcao.zul", target, map);

		links = new ArrayList<String>();
		links.add("Registar Tabela Fun��o");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	@Command
	public void openRegGrupFunc(){
		
		
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parametrizacao/grupo_funcao.zul", target, map);

		links = new ArrayList<String>();
		links.add("Registar Grupo Fun��o");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	@Command
	public void openRegFuncChef(){
		
		
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parametrizacao/funcao_chefia.zul", target, map);

		links = new ArrayList<String>();
		links.add("Registar Fun��o Chefia");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
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
	
	

}
