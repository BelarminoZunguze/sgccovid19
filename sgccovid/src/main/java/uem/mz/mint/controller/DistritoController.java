package uem.mz.mint.controller;

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

import uem.mz.mint.entity.Departamento;
import uem.mz.mint.entity.Distrito;
import uem.mz.mint.entity.Instituicao;
import uem.mz.mint.entity.Provincia;
import uem.mz.mint.entity.Sector;
import uem.mz.mint.entity.UnidadeOrganica;
import uem.mz.mint.entity.administracao.User;
import uem.mz.mint.service.DistritoService;
import uem.mz.mint.service.InstituicaoService;
import uem.mz.mint.service.ProvinciaService;
import uem.mz.mint.service.SectorService;
import uem.mz.mint.service.UnidadeOrganicaService;
import uem.mz.mint.util.MasterRep;

public class DistritoController  extends GenericForwardComposer{
	
	private Textbox txt_designacao;
	private Listbox lbxDistrito;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_visualiza;
	private Button btn_cancelar;
	private Div registar;
	private Window win;
	
	private Combobox cbx_provincia;
	
    private DistritoService distritoService;
    private ProvinciaService provinciaService;
	
	private List<Distrito> disList;
	private ListModelList<Distrito> disModel;
	
	private List<Provincia> proList; 
	private ListModelList<Provincia> proModel;
	
	private Distrito dis;
	
	private User user;
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);

		user = (User) Executions.getCurrent().getDesktop().getSession().getAttribute("utilizadorAutenticado");
		distritoService = (DistritoService) SpringUtil.getBean("distritoService");
		provinciaService = (ProvinciaService) SpringUtil.getBean("provinciaService");
		
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		buscarDistritos();
		buscarProvincias();
	}    
	
	
	private void limpaCampos(){
		txt_designacao.setRawValue(null);
		cbx_provincia.setRawValue(null);
		btn_actualizar.setVisible(false);
		btn_gravar.setVisible(true);		
	}
	
	public void onClick$btn_visualiza() {		
		registar.setVisible(true);
		btn_visualiza.setVisible(false);		
	}
	
	public void onClick$btn_gravar() {

		dis = new Distrito();
		dis.setDesignacao(txt_designacao.getValue());
		dis.setProvincia((Provincia) cbx_provincia.getSelectedItem().getValue());
		dis.setUserCreated(user.getId());
		dis.setUserUpdated(user.getId());
		
		distritoService.saveOrUpdate(dis);
		Clients.showNotification(dis.getDesignacao() + " registado com sucesso!", "info", win, "before-center", -1);
		limpaCampos();
		buscarDistritos();
	}
	
	public void onClickEditar(ForwardEvent e){
		dis = (Distrito) e.getData();
		selecionarDistrito(dis);

	}
	
	
	public void onClick$btn_actualizar() {

		dis.setDesignacao(txt_designacao.getValue());
		dis.setProvincia((Provincia) cbx_provincia.getSelectedItem().getValue());
		dis.setUserUpdated(user.getId());
				
		distritoService.saveOrUpdate(dis);
		Clients.showNotification(dis.getDesignacao() + " actualizado com sucesso!", "info", win, "before-center", -1);
		limpaCampos();
		buscarDistritos();
	}
	
	public void onClick$btn_cancelar() {
			
			limpaCampos();
		}
	
	

	
	private void buscarDistritos(){			
		disList = distritoService.buscarDistritos();
		disModel = new ListModelList<Distrito>(disList);
		lbxDistrito.setModel(disModel);
	}
	
	public void onSelect$lbxDistrito(){
		dis = (Distrito) lbxDistrito.getSelectedItem().getValue();
		selecionarDistrito(dis);		
	}
	
	private void selecionarDistrito(Distrito dis){
		txt_designacao.setValue(dis.getDesignacao());
		cbx_provincia.setValue(dis.getProvincia().getDesignacao());
		btn_actualizar.setVisible(true);
		btn_gravar.setVisible(false);		
		registar.setVisible(true);
		btn_visualiza.setVisible(false);
	}
	
	private void buscarProvincias(){    	  
	  	  proList = provinciaService.buscarProvincia();
	  	  proModel = new ListModelList<Provincia>(proList);
	  	  cbx_provincia.setModel(proModel);    	  
		 }
	
	public void onClick$btn_imprimir(Event e) throws JRException{
		if (disList.isEmpty()) {			
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
			mas.imprimir("/reports/reportUnidadeOrganica.jrxml", disList, map, win);
		}		
	}

	
	

}
