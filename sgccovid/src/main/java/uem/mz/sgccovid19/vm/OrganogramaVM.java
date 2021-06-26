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
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;

import uem.mz.sgccovid19.util.Breadcrumb;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class OrganogramaVM extends AbstractVM{
	
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
	public void openRegInst() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parametrizacao/registar_instituicao.zul", target, map);

		
	}
	
	@Command
	public void openRegUniOrg() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parametrizacao/registar_unidade_organica.zul", target, map);

		
	}
	
	@Command
	public void openRegProv() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parametrizacao/registar_provincia.zul", target, map);

		
	}
	
	@Command
	public void openRegistarDistrito() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parametrizacao/registar_distrito.zul", target, map);

		
	}
	
	@Command
	public void openRegDep() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parametrizacao/registar_departamento.zul", target, map);

		
	}
	
	@Command
	public void openRegSet() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parametrizacao/registar_sector.zul", target, map);

		
	}
	
	@Command
	public void openCategoria() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parametrizacao/categoria.zul", target, map);

		
	}
	
	@Command
	public void openClassificacao() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parametrizacao/classificacao.zul", target, map);

		
	}
	
	@Command
	public void openLocalIsolamento() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/parametrizacao/local_isolamento.zul", target, map);

		
	}
	
	

}
