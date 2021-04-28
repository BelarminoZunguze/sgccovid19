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
import uem.mz.sgccovid19.service.InstituicaoService;
import uem.mz.sgccovid19.service.SectorService;
import uem.mz.sgccovid19.service.UnidadeOrganicaService;
import uem.mz.sgccovid19.util.MasterRep;

public class UnidadeOrganicaController  extends GenericForwardComposer{
	
	private Textbox txt_codigo;
	private Textbox txt_designacao;
	private Textbox txt_sigla;
	private Listbox lbxSet;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_visualiza;
	private Button btn_cancelar;
	private Div registar;
	private Window win;
	
	private Combobox cbx_inst;
	
    private UnidadeOrganicaService unidadeOrganicaService;
    private InstituicaoService instituicaoService;
	
	private List<UnidadeOrganica> uniOrgList;
	private ListModelList<UnidadeOrganica> uniOrgModel;
	
	private List<Instituicao> instList; 
	private ListModelList<Instituicao> instModel;
	
	private UnidadeOrganica uniOrg;
	
	private User user;
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);

		user = (User) Executions.getCurrent().getDesktop().getSession().getAttribute("utilizadorAutenticado");
		unidadeOrganicaService = (UnidadeOrganicaService) SpringUtil.getBean("unidadeOrganicaService");
		instituicaoService = (InstituicaoService) SpringUtil.getBean("instituicaoService");
		
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		buscarUnidadeOrganica();
		prencherInstituicao();
	}    
	
	
	private void limpaCampos(){
		txt_codigo.setRawValue(null);
		txt_sigla.setRawValue(null);
		txt_designacao.setRawValue(null);
		cbx_inst.setRawValue(null);
		btn_actualizar.setVisible(false);
		btn_gravar.setVisible(true);
		
	}
	
	public void onClick$btn_visualiza() {		
		registar.setVisible(true);
		btn_visualiza.setVisible(false);		
	}
	
	public void onClick$btn_gravar() {

		uniOrg = new UnidadeOrganica();
		uniOrg.setDesignacao(txt_designacao.getValue());
		uniOrg.setSigla(txt_sigla.getValue());
		uniOrg.setCodigo(txt_codigo.getValue());
		uniOrg.setUserCreated(user.getId());
		uniOrg.setUserUpdated(user.getId());
		
		uniOrg.setInstituicao((Instituicao)cbx_inst.getSelectedItem().getValue());
		
		unidadeOrganicaService.saveOrUpdate(uniOrg);
		Clients.showNotification(uniOrg.getDesignacao() + " registado com sucesso!", "info", win, "before-center", -1);
		limpaCampos();
		buscarUnidadeOrganica();
	}
	
	public void onClickEditar_Classe(ForwardEvent e){
		uniOrg = (UnidadeOrganica) e.getData();
		selecionarUnidadeOrganica(uniOrg);

	}
	
	
	public void onClick$btn_actualizar() {

		uniOrg.setDesignacao(txt_designacao.getValue());
		uniOrg.setSigla(txt_sigla.getValue());
		uniOrg.setCodigo(txt_codigo.getValue());
		uniOrg.setUserUpdated(user.getId());
		
		uniOrg.setInstituicao((Instituicao)cbx_inst.getSelectedItem().getValue());
		
		unidadeOrganicaService.saveOrUpdate(uniOrg);
		Clients.showNotification(uniOrg.getDesignacao() + " actualizado com sucesso!", "info", win, "before-center", -1);
		limpaCampos();
		buscarUnidadeOrganica();
	}
	
	public void onClick$btn_cancelar() {
			
			limpaCampos();
		}
	
	

	
	private void buscarUnidadeOrganica(){			
		uniOrgList = unidadeOrganicaService.buscarUnidadeOrganica();
		uniOrgModel = new ListModelList<UnidadeOrganica>(uniOrgList);
		lbxSet.setModel(uniOrgModel);
	}
	
	public void onSelect$lbxSet(){
		uniOrg = (UnidadeOrganica) lbxSet.getSelectedItem().getValue();
		selecionarUnidadeOrganica(uniOrg);		
	}
	
	private void selecionarUnidadeOrganica(UnidadeOrganica uniOrg){
		txt_codigo.setValue(uniOrg.getCodigo());
		txt_designacao.setValue(uniOrg.getDesignacao());
		txt_sigla.setValue(uniOrg.getSigla());
		cbx_inst.setValue(uniOrg.getInstituicao().getDesignacao());
		btn_actualizar.setVisible(true);
		btn_gravar.setVisible(false);		
		registar.setVisible(true);
		btn_visualiza.setVisible(false);
	}
	
	private void prencherInstituicao(){    	  
	  	  instList = instituicaoService.buscarInstituicao();
	  	  cbx_inst.setModel(new ListModelList<Instituicao>(instList));    	  
		 }
	
	public void onClick$btn_imprimir(Event e) throws JRException{
		if (uniOrgList.isEmpty()) {			
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
			mas.imprimir("/reports/reportUnidadeOrganica.jrxml", uniOrgList, map, win);
		}		
	}

	
	

}
