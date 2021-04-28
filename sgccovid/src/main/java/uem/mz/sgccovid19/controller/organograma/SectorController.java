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
import uem.mz.sgccovid19.entity.Sector;
import uem.mz.sgccovid19.entity.UnidadeOrganica;
import uem.mz.sgccovid19.entity.administracao.User;
import uem.mz.sgccovid19.service.DepartamentoService;
import uem.mz.sgccovid19.service.InstituicaoService;
import uem.mz.sgccovid19.service.SectorService;
import uem.mz.sgccovid19.service.UnidadeOrganicaService;
import uem.mz.sgccovid19.util.MasterRep;

public class SectorController  extends GenericForwardComposer{
	
	private Textbox txt_codigo;
	private Textbox txt_designacao;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_visualiza;
	private Button btn_cancelar;
	private Listbox lbxSet;
	private Div registar;
	private Window win;
	
	private Combobox cbx_dep;
	private Combobox cbx_inst;
	private Combobox cbx_uniOrg;
	
	private SectorService sectorService;
	private DepartamentoService departamentoService;
	private InstituicaoService instituicaoService;
	private UnidadeOrganicaService unidadeOrganicaService;
	
	private List<Sector> setList;
	private ListModelList<Sector> setModel;
	
	private List<Instituicao> instList;
	private ListModelList<Instituicao> instModel;
	
	private List<UnidadeOrganica> uniOrgList;
	private ListModelList<UnidadeOrganica> uniOrgModel;
	
	private List<Departamento> depList;
	private ListModelList<Departamento> depModel;
	
	private Sector set;
	private User user;
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);

		user = (User) Executions.getCurrent().getDesktop().getSession().getAttribute("utilizadorAutenticado");
		sectorService = (SectorService) SpringUtil.getBean("sectorService");
		departamentoService = (DepartamentoService) SpringUtil.getBean("departamentoService");
		instituicaoService = (InstituicaoService) SpringUtil.getBean("instituicaoService");
		unidadeOrganicaService = (UnidadeOrganicaService) SpringUtil.getBean("unidadeOrganicaService");
		
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		buscarSector();	
		prencherInstituicao();
	}    
	
	
	private void limpaCampos(){
		txt_codigo.setRawValue(null);
		txt_designacao.setRawValue(null);
		cbx_dep.setRawValue(null);
		cbx_inst.setRawValue(null);
		cbx_uniOrg.setRawValue(null);
		btn_actualizar.setVisible(false);
		btn_gravar.setVisible(true);
		
	}
	
	public void onClick$btn_visualiza() {		
		registar.setVisible(true);
		btn_visualiza.setVisible(false);		
	}
	
	public void onClick$btn_gravar() {

		set = new Sector();
		set.setDesignacao(txt_designacao.getValue());
		set.setCodigo(txt_codigo.getValue());
		set.setUserCreated(user.getId());
		set.setUserUpdated(user.getId());
		
		set.setDepartamento((Departamento)cbx_dep.getSelectedItem().getValue());
		
		sectorService.saveOrUpdate(set);
		Clients.showNotification(set.getDesignacao() + " registado com sucesso!", "info", win, "before-center", -1);
		limpaCampos();
		buscarSector();
	}
	
	public void onClickEditar_Classe(ForwardEvent e){
		set = (Sector) e.getData();
		selecionarSector(set);	
		
	}
	
	
	public void onClick$btn_actualizar() {

		set.setDesignacao(txt_designacao.getValue());
		set.setCodigo(txt_codigo.getValue());
		set.setUserUpdated(user.getId());
		
		set.setDepartamento((Departamento)cbx_dep.getSelectedItem().getValue());
		
		sectorService.saveOrUpdate(set);
		Clients.showNotification(set.getDesignacao() + " actualizado com sucesso!", "info", win, "before-center", -1);
		limpaCampos();
		buscarSector();
	}
	
	public void onClick$btn_cancelar() {
			
			limpaCampos();
		}
	
	private void prencherInstituicao(){    	  
		instList = instituicaoService.buscarInstituicao();
	  	  cbx_inst.setModel(new ListModelList<Instituicao>(instList));    	  
		 }
	
	private void prencherUnidadeOrg(){    	  
  	  uniOrgList = unidadeOrganicaService.buscarUnidadeOrganica();
  	  cbx_uniOrg.setModel(new ListModelList<UnidadeOrganica>(uniOrgList));    	  
	 }
	
	public  void onSelect$cbx_inst(){
		cbx_uniOrg.setRawValue(null);
		cbx_uniOrg.getItems().clear();
		
		cbx_uniOrg.setModel(new ListModelList<UnidadeOrganica>(unidadeOrganicaService.buscarUnidadePorInst((Instituicao) cbx_inst.getSelectedItem().getValue())));
	}
	public  void onSelect$cbx_uniOrg(){
		cbx_dep.setRawValue(null);
		cbx_dep.getItems().clear();
		
		cbx_dep.setModel(new ListModelList<Departamento>(departamentoService.buscarDepartamentoPorUnidade((UnidadeOrganica) cbx_uniOrg.getSelectedItem().getValue())));
	}
	

	
	private void buscarSector(){			
		setList = sectorService.buscarSector();
		setModel = new ListModelList<Sector>(setList);
		lbxSet.setModel(setModel);
	}
	
	public void onSelect$lbxSet(){
		set = (Sector) lbxSet.getSelectedItem().getValue();
		selecionarSector(set);		
	}
	
	private void selecionarSector(Sector set){
		txt_codigo.setValue(set.getCodigo());
		txt_designacao.setValue(set.getDesignacao());
		cbx_dep.setValue(set.getDepartamento().getDesignacao());
		cbx_uniOrg.setValue(set.getDepartamento().getUnidade_organica().getDesignacao());
		cbx_inst.setValue(set.getDepartamento().getUnidade_organica().getInstituicao().getDesignacao());
		btn_actualizar.setVisible(true);
		btn_gravar.setVisible(false);		
		registar.setVisible(true);
		btn_visualiza.setVisible(false);
	}
	
	private void prencherDepartamento(){    	  
	  	  depList = departamentoService.buscarDepartamento();
	  	  cbx_dep.setModel(new ListModelList<Departamento>(depList));    	  
		 }
	
	public void onClick$btn_imprimir(Event e) throws JRException{
		if (setList.isEmpty()) {			
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
			mas.imprimir("/reports/reportSector.jrxml", setList, map, win);
		}		
	}


}
