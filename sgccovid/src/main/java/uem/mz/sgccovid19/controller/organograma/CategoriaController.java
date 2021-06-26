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

import uem.mz.sgccovid19.entity.Instituicao;
import uem.mz.sgccovid19.entity.TipoUtente;
import uem.mz.sgccovid19.entity.administracao.User;
import uem.mz.sgccovid19.service.InstituicaoService;
import uem.mz.sgccovid19.service.TipoUtenteService;

public class CategoriaController extends GenericForwardComposer{
	
	private Textbox txt_designacao;
	private Textbox txt_codigo;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_visualiza;
	private Listbox lbxCategoria;
	private Div registar;
	private Window win;
	
	private TipoUtenteService tipoUtenteService;
	private List<TipoUtente> tiputList;
	private ListModelList<TipoUtente> tiputModel;
	
	private TipoUtente tipo;
	private User user;
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);

		user = (User) Executions.getCurrent().getDesktop().getSession().getAttribute("utilizadorAutenticado");
		tipoUtenteService = (TipoUtenteService) SpringUtil.getBean("tipoUtenteService");
		
		
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		buscarCategoria();	
	} 
	
	private void buscarCategoria(){
		tiputList = tipoUtenteService.buscarTipoUtente();
		tiputModel = new ListModelList<TipoUtente>(tiputList);
		lbxCategoria.setModel(tiputModel);
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
		
		tipo = new TipoUtente();
		tipo.setCodigo(txt_codigo.getValue());
		tipo.setDesignacao(txt_designacao.getValue());
		tipo.setUserCreated(user.getId());
		tipo.setUserUpdated(user.getId());
		
		
		tipoUtenteService.saveOrUpdate(tipo);
		
		Clients.showNotification(tipo.getDesignacao() + " registado com sucesso!", "info", win, "before-center", 3000);
		limpaCampos();
		buscarCategoria();
	}
	
	public void onClickEditar_Categ(ForwardEvent e){
		tipo = (TipoUtente) e.getData();
		
		txt_codigo.setValue(tipo.getCodigo());
		txt_designacao.setValue(tipo.getDesignacao());
		btn_actualizar.setVisible(true);
		btn_gravar.setVisible(false);		
		registar.setVisible(true);
		btn_visualiza.setVisible(false);
		
	}
	
	
	public void onClick$btn_actualizar() {
		
		tipo.setCodigo(txt_codigo.getValue());
		tipo.setDesignacao(txt_designacao.getValue());
		tipo.setUserUpdated(user.getId());
		
		tipoUtenteService.saveOrUpdate(tipo);
		Clients.showNotification(tipo.getDesignacao() + " actualizado com sucesso!", "info", win, "before-center", 3000);
		limpaCampos();
		buscarCategoria();
	}
	
	public void onClick$btn_cancelar() {
			
			limpaCampos();
	}
	
	
	
	
	

}
