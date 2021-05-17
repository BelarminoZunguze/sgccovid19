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
public class AdministracaoVM extends AbstractVM{
	
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
	public void openNotificacao() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/ficha_investigacao/notificacao.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Notificação");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	@Command
	public void openDadosNotificacao() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/ficha_investigacao/dados_notificacao.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Dados da Notificacao");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	
	
	@Command
	public void openDadosCaso() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/ficha_investigacao/dados_caso.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Informações sobre o caso");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	
	
	
	
	@Command
	public void openResidenciaDoCaso(){

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/ficha_investigacao/residencia_caso.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Informação da residência do caso");
		Breadcrumb.drawn(breadcrumb, "", links);
	}
	
	@Command
	public void openContactosUnidade(){

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/ficha_investigacao/contactos_Unidade.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Seguimento de contactos na unidade");
		Breadcrumb.drawn(breadcrumb, "", links);
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

		
	}
	
	@Command
	public void openUtilizador() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents(
				"views/administracao/registar_utilizador.zul", target, map);

		
	}
	
	@Command
	public void openPerfil() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/administracao/gerir_perfil.zul", target,
				map);

		
	}
	
	@Command
	public void openPermissoes() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/administracao/gerir_permissoes.zul",
				target, map);

		
	}
	
	@Command
	public void openModulos() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/administracao/gerir_modulos.zul",
				target, map);

		
	}
	

}
