package uem.mz.sgccovid19.controller.organograma;

import java.io.InputStream;  
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import uem.mz.sgccovid19.entity.Departamento;
import uem.mz.sgccovid19.entity.Instituicao;
import uem.mz.sgccovid19.entity.Provincia;
import uem.mz.sgccovid19.entity.UnidadeOrganica;
import uem.mz.sgccovid19.entity.administracao.User;
import uem.mz.sgccovid19.service.DepartamentoService;
import uem.mz.sgccovid19.service.InstituicaoService;
import uem.mz.sgccovid19.service.ProvinciaService;
import uem.mz.sgccovid19.service.UnidadeOrganicaService;
import uem.mz.sgccovid19.util.MasterRep;

public class DepartamentoController  extends GenericForwardComposer{
	
	private Textbox txt_codigo;
	private Textbox txt_designacao;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_visualiza;
	private Button btn_cancelar;
	private Div registar;
	private Listbox lbxDep;
	private Window win;
	
	private Combobox cbx_prov;
	private Combobox cbx_inst;
	private Combobox cbx_uniOrg;
	
	private DepartamentoService departamentoService;
	
	private InstituicaoService instituicaoService;
	private UnidadeOrganicaService unidadeOrganicaService;
	
	private ProvinciaService provinciaService;

	private List<Departamento> depList;
	private ListModelList<Departamento> depModel;
	
	private List<Instituicao> instList;
	private ListModelList<Instituicao> instModel;
	
	private List<UnidadeOrganica> uniOrgList;
	private ListModelList<UnidadeOrganica> uniOrgModel;
	
	private List<Provincia> provList;
	private ListModelList<Provincia> provModel;
	
	private Departamento dep;
	private User user;
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);

		user = (User) Executions.getCurrent().getDesktop().getSession().getAttribute("utilizadorAutenticado");
		departamentoService = (DepartamentoService) SpringUtil.getBean("departamentoService");
		provinciaService = (ProvinciaService) SpringUtil.getBean("provinciaService");
		unidadeOrganicaService = (UnidadeOrganicaService) SpringUtil.getBean("unidadeOrganicaService");
		instituicaoService = (InstituicaoService) SpringUtil.getBean("instituicaoService");
		
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		buscarDepartamento();
		prencherProvincia();
		prencherInstituicao();
	}    
	
	
	private void limpaCampos(){
		txt_codigo.setRawValue(null);
		txt_designacao.setRawValue(null);
		cbx_prov.setRawValue(null);
		cbx_uniOrg.setRawValue(null);
		cbx_inst.setRawValue(null);
		btn_actualizar.setVisible(false);
		btn_gravar.setVisible(true);
		
	}
	
	public void onClick$btn_visualiza() {		
		registar.setVisible(true);
		btn_visualiza.setVisible(false);		
	}
	
	public void onClick$btn_gravar() {

		dep = new Departamento();
		dep.setDesignacao(txt_designacao.getValue());
		dep.setCodigo(txt_codigo.getValue());
		dep.setUserCreated(user.getId());
		dep.setUserUpdated(user.getId());
		
		dep.setUnidade_organica((UnidadeOrganica)cbx_uniOrg.getSelectedItem().getValue());
		dep.setProvincia((Provincia)cbx_prov.getSelectedItem().getValue());
		
		departamentoService.saveOrUpdate(dep);
		Clients.showNotification(dep.getDesignacao() + " registado com sucesso!", "info", win, "before-center", -1);
		limpaCampos();
		buscarDepartamento();
	}
	
	public void onClickEditar_Departamento(ForwardEvent e){
		dep = (Departamento) e.getData();
		selecionarDepartamento(dep);
			
	}
	
	
	public void onClick$btn_actualizar() {

		dep.setDesignacao(txt_designacao.getValue());
		dep.setCodigo(txt_codigo.getValue());
		dep.setUserUpdated(user.getId());
		
		dep.setUnidade_organica((UnidadeOrganica)cbx_uniOrg.getSelectedItem().getValue());
		dep.setProvincia((Provincia)cbx_prov.getSelectedItem().getValue());
		
		departamentoService.saveOrUpdate(dep);
		Clients.showNotification(dep.getDesignacao() + " actualizado com sucesso!", "info", win, "before-center", -1);
		limpaCampos();
		buscarDepartamento();
	}
	
	public  void onSelect$cbx_inst(){
		cbx_uniOrg.setRawValue(null);
		cbx_uniOrg.getItems().clear();
		
		cbx_uniOrg.setModel(new ListModelList<UnidadeOrganica>(unidadeOrganicaService.buscarUnidadePorInst((Instituicao) cbx_inst.getSelectedItem().getValue())));
	}
	
	public void onClick$btn_cancelar() {
			
			limpaCampos();
		}
	
		
	private void buscarDepartamento(){			
		depList = departamentoService.buscarDepartamento();
		depModel = new ListModelList<Departamento>(depList);
		lbxDep.setModel(depModel);
	}
	
	
	public void onSelect$lbxCategoria(){
		dep = (Departamento) lbxDep.getSelectedItem().getValue();
		selecionarDepartamento(dep);		
	}
	
	private void selecionarDepartamento(Departamento dep){
		txt_codigo.setValue(dep.getCodigo());
		txt_designacao.setValue(dep.getDesignacao());
		cbx_prov.setValue(dep.getProvincia().getDesignacao());
        cbx_inst.setValue(dep.getUnidade_organica().getInstituicao().getDesignacao());
        cbx_uniOrg.setValue(dep.getUnidade_organica().getDesignacao());
		cbx_uniOrg.setValue(dep.getUnidade_organica().getDesignacao());
		btn_actualizar.setVisible(true);
		btn_gravar.setVisible(false);		
		registar.setVisible(true);
		btn_visualiza.setVisible(false);
	}
	
	private void prencherProvincia(){    	  
  	  provList = provinciaService.buscarProvincia();
  	  cbx_prov.setModel(new ListModelList<Provincia>(provList));    	  
	 }
	
	private void prencherInstituicao(){    	  
		instList = instituicaoService.buscarInstituicao();
	  	  cbx_inst.setModel(new ListModelList<Instituicao>(instList));    	  
		 }
	
	private void prencherUnidadeOrg(){    	  
  	  uniOrgList = unidadeOrganicaService.buscarUnidadeOrganica();
  	  cbx_uniOrg.setModel(new ListModelList<UnidadeOrganica>(uniOrgList));    	  
	 }
	
	public void onClick$btn_imprimir(Event e) throws JRException{
		if (depList.isEmpty()) {			
			Clients.showNotification("Informa��o Vazia", "info", win, "middle_center", 3000);
		} else {

			MasterRep mas = new MasterRep();
			Map<String, Object> map = new HashMap<String, Object>();
			
			final Execution ex = Executions.getCurrent();
			InputStream inputV = ex.getDesktop().getWebApp().getResourceAsStream("/images/moz.png");
			map.put("imagemLogo", inputV);
			//map.put("titulo", "LISTA DE ETAPAS");
			String realPath = ex.getDesktop().getWebApp().getRealPath("/reports/");
			map.put("SUBREPORT_DIR", realPath);
			mas.imprimir("/reports/reportDepartamento.jrxml", depList, map, win);
		}		
	}

}
