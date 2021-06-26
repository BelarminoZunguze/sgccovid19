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

import uem.mz.sgccovid19.entity.Classificacao;
import uem.mz.sgccovid19.entity.TipoUtente;
import uem.mz.sgccovid19.entity.administracao.User;
import uem.mz.sgccovid19.service.ClassificacaoService;
import uem.mz.sgccovid19.service.TipoUtenteService;

public class ClassificacaoController extends GenericForwardComposer{
	
	private Textbox txt_designacao;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_visualiza;
	private Listbox lbxClassificacao;
	private Div registar;
	private Window win;
	
	private ClassificacaoService classificacaoService;
	private List<Classificacao> classList;
	private ListModelList<Classificacao> classModel;
	
	private Classificacao cla;
	
	private User user;
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);

		user = (User) Executions.getCurrent().getDesktop().getSession().getAttribute("utilizadorAutenticado");
		classificacaoService = (ClassificacaoService) SpringUtil.getBean("classificacaoService");
		
		
		
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		buscarClassificacao();	
		
	} 
	
	private void buscarClassificacao(){
		classList = classificacaoService.buscarClassificacao();
		classModel = new ListModelList<Classificacao>(classList);
		lbxClassificacao.setModel(classModel);
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
		
		cla = new Classificacao(); 
	
		cla.setNome(txt_designacao.getValue());
		cla.setUserCreated(user.getId());
		cla.setUserUpdated(user.getId());
		
		classificacaoService.saveOrUpdate(cla);
		
		Clients.showNotification(cla.getNome() + " registado com sucesso!", "info", win, "before-center", 3000);
		limpaCampos();
		buscarClassificacao();
	}
	
	public void onClickEditar_Categ(ForwardEvent e){
		cla = (Classificacao) e.getData();
		
		txt_designacao.setValue(cla.getNome());
		btn_actualizar.setVisible(true);
		btn_gravar.setVisible(false);		
		registar.setVisible(true);
		btn_visualiza.setVisible(false);
		
	}
	
	
	public void onClick$btn_actualizar() {
		
		cla.setNome(txt_designacao.getValue());
		cla.setUserUpdated(user.getId());
		
		classificacaoService.saveOrUpdate(cla);
		
		Clients.showNotification(cla.getNome() + " actualizado com sucesso!", "info", win, "before-center", 3000);
		limpaCampos();
		buscarClassificacao();
	}
	
	public void onClick$btn_cancelar() {
			
			limpaCampos();
	}
	
	

}
