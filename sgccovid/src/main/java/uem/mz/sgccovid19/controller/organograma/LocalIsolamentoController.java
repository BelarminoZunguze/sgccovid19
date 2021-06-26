package uem.mz.sgccovid19.controller.organograma;

import java.util.List;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import uem.mz.sgccovid19.entity.LocalIsolamento;
import uem.mz.sgccovid19.entity.administracao.User;
import uem.mz.sgccovid19.service.LocalIsolamentoService;

public class LocalIsolamentoController extends GenericForwardComposer{
	
	private Textbox txt_designacao;
	private Textbox txt_codigo;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_visualiza;
	private Listbox lbxLocal;
	private Div registar;
	private Window win;
	
	private LocalIsolamentoService localService;
	private List<LocalIsolamento> localList;
	private ListModelList<LocalIsolamento> localModel;
	
	
	private LocalIsolamento loc;
	private User user;
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);

		user = (User) Executions.getCurrent().getDesktop().getSession().getAttribute("utilizadorAutenticado");
		localService = (LocalIsolamentoService) SpringUtil.getBean("localService");
		
		
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		buscarLocal();	
	} 
	
	private void buscarLocal(){
		localList = localService.buscarLocalIsolamento();
		localModel = new ListModelList<LocalIsolamento>(localList);
		lbxLocal.setModel(localModel);
	}
	
	private void limpaCampos(){
		txt_codigo.setRawValue(null);
		txt_designacao.setRawValue(null);
		btn_actualizar.setVisible(false);
		btn_gravar.setVisible(true);
		
	}
	
	public void onClick$btn_visualiza() {		
		registar.setVisible(true);
		btn_visualiza.setVisible(false);		
	}

	public void onClick$btn_gravar() {
		loc = new LocalIsolamento(); 
		
		loc.setCodigo(txt_codigo.getValue());
		loc.setDesignacao(txt_designacao.getValue());
		loc.setUserCreated(user.getId());
		loc.setUserUpdated(user.getId());
		
		localService.saveOrUpdate(loc);
		
		Clients.showNotification(loc.getDesignacao() + " registado com sucesso!", "info", win, "before-center", 3000);
		limpaCampos();
		buscarLocal();
	}
	
	public void onClickEditar_Categ(ForwardEvent e){
		loc = (LocalIsolamento) e.getData();
		
		txt_codigo.setValue(loc.getCodigo());
		txt_designacao.setValue(loc.getDesignacao());
		btn_actualizar.setVisible(true);
		btn_gravar.setVisible(false);		
		registar.setVisible(true);
		btn_visualiza.setVisible(false);
		
	}
	
	
	public void onClick$btn_actualizar() {
		
		loc.setCodigo(txt_codigo.getValue());
		loc.setDesignacao(txt_designacao.getValue());
		loc.setUserUpdated(user.getId());
		
		localService.saveOrUpdate(loc);
		
		Clients.showNotification(loc.getDesignacao() + " actualizado com sucesso!", "info", win, "before-center", 3000);
		limpaCampos();
		buscarLocal();
	}
	
	public void onClick$btn_cancelar() {
			
			limpaCampos();
	}
	
	

}
