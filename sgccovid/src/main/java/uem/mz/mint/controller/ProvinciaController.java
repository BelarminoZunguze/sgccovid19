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
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import uem.mz.mint.entity.Instituicao;
import uem.mz.mint.entity.Provincia;
import uem.mz.mint.entity.administracao.User;
import uem.mz.mint.service.InstituicaoService;
import uem.mz.mint.service.ProvinciaService;
import uem.mz.mint.util.MasterRep;

public class ProvinciaController  extends GenericForwardComposer{
	
	private Textbox txt_designacao;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_visualiza;
	private Button btn_cancelar;
	private Listbox lbxProv;
	private Div registar;
	private Window win;
	
	private ProvinciaService provinciaService;
	
	private List<Provincia> provList;
	private ListModelList<Provincia> provModel;
	
	private Provincia prov;
	private User user;
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);

		user = (User) Executions.getCurrent().getDesktop().getSession().getAttribute("utilizadorAutenticado");
		provinciaService = (ProvinciaService) SpringUtil.getBean("provinciaService");
		
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		buscarProvincia();	
	}    
	
	private void limpaCampos(){
		txt_designacao.setRawValue(null);
		btn_actualizar.setVisible(false);
		btn_gravar.setVisible(true);
		
	}
	
	public void onClick$btn_visualiza() {		
		registar.setVisible(true);
		btn_visualiza.setVisible(false);		
	}

	public void onClick$btn_gravar() {

		prov = new Provincia();
		prov.setDesignacao(txt_designacao.getValue());
		prov.setUserCreated(user.getId());
		prov.setUserUpdated(user.getId());
		
		
		provinciaService.saveOrUpdate(prov);
		Clients.showNotification(prov.getDesignacao() + " registado com sucesso!", "info", win, "before-center", -1);
		limpaCampos();
		buscarProvincia();
	}
	
	public void onClickEditar_Classe(ForwardEvent e){
		prov = (Provincia) e.getData();	
		selecionarProvincia(prov);	
	}
	
	
	public void onClick$btn_actualizar() {

		prov.setDesignacao(txt_designacao.getValue());
		prov.setUserCreated(user.getId());
		prov.setUserUpdated(user.getId());
		
		provinciaService.saveOrUpdate(prov);
		Clients.showNotification(prov.getDesignacao() + " actualizado com sucesso!", "info", win, "before-center", -1);
		limpaCampos();
		buscarProvincia();
	}
	
	public void onClick$btn_cancelar() {
			
			limpaCampos();
		}
	
	
	private void buscarProvincia(){			
		provList = provinciaService.buscarProvincia();
		provModel = new ListModelList<Provincia>(provList);
		lbxProv.setModel(provModel);
	}
	
	public void onSelect$lbxProv(){
		prov = (Provincia) lbxProv.getSelectedItem().getValue();
		selecionarProvincia(prov);		
	}
	
	private void selecionarProvincia(Provincia prov){
		txt_designacao.setValue(prov.getDesignacao());
		btn_actualizar.setVisible(true);
		btn_gravar.setVisible(false);		
		registar.setVisible(true);
		btn_visualiza.setVisible(false);
	}
	
	public void onClick$btn_imprimir(Event e) throws JRException{
		if (provList.isEmpty()) {			
			Clients.showNotification("Informa��o Vazia", "info", win, "middle_center", 3000);
		} else {

			MasterRep mas = new MasterRep();
			Map<String, Object> map = new HashMap<String, Object>();
			
			final Execution ex = Executions.getCurrent();
			InputStream inputV = ex.getDesktop().getWebApp().getResourceAsStream("/images/moz.png");
			map.put("imagemLogo", inputV);
			map.put("titulo", "LISTA DE PROVINCIAS");
			String realPath = ex.getDesktop().getWebApp().getRealPath("/reports/");
			map.put("SUBREPORT_DIR", realPath);
			mas.imprimir("/reports/reportBaseNivel0.jrxml", provList, map, win);
		}		
	}


}
