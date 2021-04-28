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
import uem.mz.sgccovid19.util.MasterRep;

public class InstituicaoController  extends GenericForwardComposer{
	
	private Textbox txt_designacao;
	private Textbox txt_sigla;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_visualiza;
	private Listbox lbxInst;
	private Button btn_cancelar;
	private Div registar;
	private Window win;
	
	private InstituicaoService instituicaoService;
	
	private List<Instituicao> instList;
	private ListModelList<Instituicao> instModel;
	
	private Instituicao inst;
	private User user;
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);

		user = (User) Executions.getCurrent().getDesktop().getSession().getAttribute("utilizadorAutenticado");
		instituicaoService = (InstituicaoService) SpringUtil.getBean("instituicaoService");
		
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		buscarInstituicao();	
	}    
	
	private void limpaCampos(){
		txt_sigla.setRawValue(null);
		txt_designacao.setRawValue(null);
		btn_actualizar.setVisible(false);
		btn_gravar.setVisible(true);
		
	}
	
	public void onClick$btn_visualiza() {		
		registar.setVisible(true);
		btn_visualiza.setVisible(false);		
	}

	public void onClick$btn_gravar() {

		inst = new Instituicao();
		inst.setDesignacao(txt_designacao.getValue());
		inst.setSigla(txt_sigla.getValue());
		inst.setUserCreated(user.getId());
		inst.setUserUpdated(user.getId());
		
		
		instituicaoService.saveOrUpdate(inst);
		Clients.showNotification(inst.getDesignacao() + " registado com sucesso!", "info", win, "before-center", -1);
		limpaCampos();
		buscarInstituicao();
	}
	
	public void onClickEditar_Classe(ForwardEvent e){
		inst = (Instituicao) e.getData();
		selecionarInstituicao(inst);	
	}
	
	
	public void onClick$btn_actualizar() {

		inst.setDesignacao(txt_designacao.getValue());
		inst.setSigla(txt_sigla.getValue());
		inst.setUserUpdated(user.getId());
		
		instituicaoService.saveOrUpdate(inst);
		Clients.showNotification(inst.getDesignacao() + " actualizado com sucesso!", "info", win, "before-center", -1);
		limpaCampos();
		buscarInstituicao();
	}
	
	public void onClick$btn_cancelar() {
			
			limpaCampos();
		}
	
		
	private void buscarInstituicao(){			
		instList = instituicaoService.buscarInstituicao();
		instModel = new ListModelList<Instituicao>(instList);
		lbxInst.setModel(instModel);
	}
	
	public void onSelect$lbxInst(){
		inst = (Instituicao) lbxInst.getSelectedItem().getValue();
		selecionarInstituicao(inst);		
	}
	
	private void selecionarInstituicao(Instituicao inst){
		txt_sigla.setValue(inst.getSigla());
		txt_designacao.setValue(inst.getDesignacao());
		btn_actualizar.setVisible(true);
		btn_gravar.setVisible(false);		
		registar.setVisible(true);
		btn_visualiza.setVisible(false);
	}
	
	
	public void onClick$btn_imprimir(Event e) throws JRException{
		if (instList.isEmpty()) {			
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
			mas.imprimir("/reports/reportInstituicao.jrxml", instList, map, win);
		}		
	}

}
