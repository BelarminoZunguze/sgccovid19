package uem.mz.sgccovid19.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import uem.mz.sgccovid19.entity.Ficha;
import uem.mz.sgccovid19.entity.administracao.User;
import uem.mz.sgccovid19.service.ClassificacaoService;
import uem.mz.sgccovid19.service.FichaService;
import uem.mz.sgccovid19.service.TipoUtenteService;
import uem.mz.sgccovid19.service.UnidadeOrganicaService;

public class SituacaoController extends GenericForwardComposer{
	
	private User user;
	
	private Div target;
	
	private Window win;
	
	private FichaService fichaService;
	private List<Ficha> fichaList;
	private ListModelList<Ficha> fichaModel;
	
	private Listbox lbxSituacao;
	
	private Combobox cbx_situacao;
	
	private Ficha ficha;
	
	@Wire
    Window modalDialog;
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		user = (User) Executions.getCurrent().getDesktop().getSession().getAttribute("utilizadorAutenticado");
		
		target = (Div) Executions.getCurrent().getArg().get("target");
		
		fichaService = (FichaService) SpringUtil.getBean("fichaService");
		
		ficha = (Ficha) Executions.getCurrent().getArg().get("ficha");
		
		
		}
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		
		fichaList = new ArrayList<Ficha>();
		fichaList.add(ficha);
		fichaModel = new ListModelList<Ficha>(fichaList);
		lbxSituacao.setModel(fichaModel);
		
		cbx_situacao.setValue(ficha.getEstado());
		
	}
	
	public void onClick$btn_actualizar() {
		
			ficha.setEstado((String)cbx_situacao.getSelectedItem().getValue());
			ficha.setUserUpdated(user.getId());
			fichaService.update(ficha);
			Clients.showNotification("Actualizado com Sucesso!","info", win, "middle_center", 3000);
			modalDialog.detach();
			
			final HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("target", target);
			target.getChildren().clear();
			Executions.createComponents("views/ficha_investigacao/ficha_investigacao.zul", target, map);
			
		
		
	}
	
	

}
