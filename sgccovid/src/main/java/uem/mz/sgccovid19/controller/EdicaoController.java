package uem.mz.sgccovid19.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import uem.mz.sgccovid19.entity.Distrito;
import uem.mz.sgccovid19.entity.Ficha;
import uem.mz.sgccovid19.entity.Provincia;
import uem.mz.sgccovid19.entity.TipoUtente;
import uem.mz.sgccovid19.entity.UnidadeOrganica;
import uem.mz.sgccovid19.entity.Utente;
import uem.mz.sgccovid19.entity.administracao.User;
import uem.mz.sgccovid19.service.DistritoService;
import uem.mz.sgccovid19.service.ProvinciaService;
import uem.mz.sgccovid19.service.TipoUtenteService;
import uem.mz.sgccovid19.service.UnidadeOrganicaService;
import uem.mz.sgccovid19.service.UtenteService;
import uem.mz.sgccovid19.util.Breadcrumb;
import uem.mz.sgccovid19.util.showClientNotification;

public class EdicaoController extends GenericForwardComposer{
	
	private Window win;
	private Div pesquisar;
	
	private showClientNotification cli = new showClientNotification();
	
	private List<String> links;
	@Wire 
	private Div breadcrumb;
	
	private Div target;
	
	private User user;
	
	private UtenteService utenteService;
	private List<Utente> utenteList;
	private ListModelList<Utente> utenteModel;
	private Utente utente;
	
	private TipoUtenteService tipoUtenteService;
	private List<TipoUtente> tiputList;
	private ListModelList<TipoUtente> tiputModel;
	
	private DistritoService distritoService;
	private List<Distrito> distritoList;
	private ListModelList<Distrito> distritoModel;
	
	
	private ProvinciaService provinciaService;
	private List<Provincia> provinciaList;
	private ListModelList<Provincia> provinciaModel;
	
	private UnidadeOrganicaService unidadeOrganicaService;
	private List<UnidadeOrganica> uniOrgList;
	private ListModelList<UnidadeOrganica> uniOrgModel;
	
	
	private Combobox cbxTipoUtente;
	private Combobox cbxNacionalidade;
	private Combobox cbxGenero;
	private Combobox cbxProvincia;
	private Combobox cbxDistrito;
	private Combobox cbxUnidade;
	private Combobox cbxClassificacao;
	private Combobox cbxLocalIsolamento;
	private Combobox cbxProvIsolamento;
	private Combobox cbxDistrIsolamento;
	private Combobox cbxDepartamentoDentro;
	private Combobox cbxSectorDentro;
	private Combobox cbxUnidadeFora;
	private Combobox cbxDepartamentoFora;
	private Combobox cbxSectorFora;
	
	private Textbox txtNome;
	private Textbox txtEmail;
	private Textbox txtContacto;
	private Textbox txtEndereco;
	private Textbox txt_proveniencia;
	private Textbox txt_pontoEntrada;
	private Textbox txt_MeioTransporte;
	private Textbox txt_outras;
	private Textbox txtOutrosDentro;
	private Textbox txtoutrosFora;
	
	private Datebox dtb_dataNascimento;
	private Datebox dtb_dataTeste;
	private Datebox dtb_dataNotificacao;
	private Datebox dtb_dataEntrada;
	private Datebox dtb_dataInformou;
	private Datebox dtb_dataUltima;
	
	
	private Radio rdb_sim;
	private Radio rdb_nao;
	private Radio rdb_sim_detectado;
	private Radio rdb_nao_detectado;
	private Radio rdb_sim_isolamento;
	private Radio rdb_nao_isolamento;
	private Radio rdb_sim_dentro;
	private Radio rdb_nao_dentro;
	private Radio rdb_sim_fora;
	private Radio rdb_nao_fora;
	
	private UtenteController ut;
	private Ficha ficha;
	
	private Provincia provincia;
	
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		user = (User) Executions.getCurrent().getDesktop().getSession().getAttribute("utilizadorAutenticado");
		
		target = (Div) Executions.getCurrent().getArg().get("target");
		
		utenteService = (UtenteService) SpringUtil.getBean("utenteService");
		
		tipoUtenteService = (TipoUtenteService) SpringUtil.getBean("tipoUtenteService");
		
		distritoService = (DistritoService) SpringUtil.getBean("distritoService");
		
		provinciaService = (ProvinciaService) SpringUtil.getBean("provinciaService");
		
		unidadeOrganicaService = (UnidadeOrganicaService) SpringUtil.getBean("unidadeOrganicaService");
		
		ficha = (Ficha) Executions.getCurrent().getArg().get("ficha");
		
		utente = (Utente) Executions.getCurrent().getArg().get("utente");
		
		
		}
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		buscarTipoUtente();
		buscarProvincia();
		buscarDistrito();
		buscarUnidadeOrganica();
		buscarDistritoIsolamento();
		preencherTela();
		
	}
	
	public void buscarTipoUtente() {
		tiputList = tipoUtenteService.buscarTipoUtente();
		tiputModel = new ListModelList<TipoUtente>(tiputList);
		cbxTipoUtente.setModel(tiputModel);
	}
	
	public void buscarDistrito() {
		distritoList = distritoService.buscarDistritos();
		distritoModel = new ListModelList<Distrito>(distritoList);
		cbxDistrito.setModel(distritoModel);
		
	}
	
	public void buscarDistritoIsolamento() {
		distritoList = distritoService.buscarDistritos();
		distritoModel = new ListModelList<Distrito>(distritoList);
		cbxDistrIsolamento.setModel(distritoModel);
		
	}
	
	public void onSelect$cbxProvincia() {
		provincia = cbxProvincia.getSelectedItem().getValue();
		distritoList = distritoService.buscarDistritosPorProvincia(provincia);
		distritoModel = new ListModelList<Distrito>(distritoList);
		cbxDistrito.setModel(distritoModel);
		
	}
	
	public void onSelect$cbxProvIsolamento() {
		provincia = cbxProvIsolamento.getSelectedItem().getValue();
		distritoList = distritoService.buscarDistritosPorProvincia(provincia);
		distritoModel = new ListModelList<Distrito>(distritoList);
		cbxDistrIsolamento.setModel(distritoModel);
		
	}
	
	public void buscarProvincia() {
		provinciaList = provinciaService.buscarProvincia();
		provinciaModel = new ListModelList<Provincia>(provinciaList);
		cbxProvincia.setModel(provinciaModel);
		
	}
	
	private void buscarUnidadeOrganica(){    	  
	  	  uniOrgList = unidadeOrganicaService.buscarUnidadeOrganica();
	  	  uniOrgModel = new ListModelList<UnidadeOrganica>(uniOrgList);
	  	  cbxUnidade.setModel(uniOrgModel);    	  
	}
	
	public void preencherTela() {
		//Dados do Utente
		
		txtNome.setValue(ficha.getUtente().getNome());
		//cbx_departamento.setValue(fun.getSector().getDepartamento()==null ? null : fun.getSector().getDepartamento().getDesignacao());	
		cbxNacionalidade.setValue(ficha.getUtente().getNacionalidade());
		txtEmail.setValue(ficha.getUtente().getEmail());
		txtContacto.setValue(ficha.getUtente().getContacto());
		cbxGenero.setValue(ficha.getUtente().getGenero());
		dtb_dataNascimento.setValue(ficha.getUtente().getDataNascimento());
		cbxTipoUtente.setValue(ficha.getUtente().getTipo_utente().getDesignacao());
		cbxUnidade.setValue(ficha.getUtente().getUnidade().getDesignacao());
		cbxProvincia.setValue(ficha.getUtente().getDistrito().getProvincia().getDesignacao());
		cbxDistrito.setValue(ficha.getUtente().getDistrito().getDesignacao());
		txtEndereco.setValue(ficha.getUtente().getEndereco());
		
		//Informações sobre o caso
		
		cbxClassificacao.setValue(ficha.getClassificacao().getNome());
		dtb_dataTeste.setValue(ficha.getDataTeste());
		dtb_dataNotificacao.setValue(ficha.getDataNotificacao());
		
		if(ficha.isViajou()==true) {
			rdb_sim.setChecked(true);
			txt_proveniencia.setValue(ficha.getProveniencia());
			txt_pontoEntrada.setValue(ficha.getPontoEntrada());
			
			if(ficha.isDetectadoNoPontoEntrada()==true) {
				rdb_sim_detectado.setChecked(true);
			} else {rdb_nao_detectado.setChecked(true);}
			
			dtb_dataEntrada.setValue(ficha.getDataEntradaNoPais());
			txt_MeioTransporte.setValue(ficha.getMeioTransporte());
			
		} else {rdb_nao.setChecked(true);}
		
		//Resiência do caso
		
		if(ficha.isEmIsolamento()==true) {
			rdb_sim_isolamento.setChecked(true);
			cbxLocalIsolamento.setValue(ficha.getLocal_isolamento());
			cbxProvIsolamento.setValue(ficha.getDistrito_isolamento().getProvincia().getDesignacao());
			cbxDistrIsolamento.setValue(ficha.getDistrito_isolamento().getDesignacao());
			dtb_dataInformou.setValue(ficha.getDataUltimaVezNaUnidade());
			dtb_dataUltima.setValue(ficha.getDataUltimaVezNaUnidade());
			txt_outras.setValue(ficha.getOutrasInformacoes());
		} else {rdb_nao_isolamento.setChecked(true);}
		
		//seguimento de contactos
		
		if(ficha.getFichaContacto()!=null) {
			
			if(ficha.getFichaContacto().isTeveContactoDentro()==true) {
				rdb_sim_dentro.setChecked(true);
				cbxDepartamentoDentro.setValue(ficha.getFichaContacto().getDepartamentoDentro().getDesignacao());
				cbxSectorDentro.setValue(ficha.getFichaContacto().getSectorDentro().getDesignacao());
				txtOutrosDentro.setValue(ficha.getFichaContacto().getOutrosEspacosDentro());
			} else {rdb_nao_dentro.setChecked(true);}
			
			
			if(ficha.getFichaContacto().isTeveContactoFora()==true) {
				rdb_sim_fora.setChecked(true);
				cbxUnidadeFora.setValue(ficha.getFichaContacto().getUnidadeFora().getDesignacao());
				cbxDepartamentoFora.setValue(ficha.getFichaContacto().getDepartamentoFora().getDesignacao());
				cbxSectorFora.setValue(ficha.getFichaContacto().getSectorFora().getDesignacao());
				txtoutrosFora.setValue(ficha.getFichaContacto().getOutrosEspacosFora());
			} else {rdb_nao_fora.setChecked(true);}
			
		}
		
		
		
	}
	
    
    
   public void onClick$btn_cancelar() {
    	
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		
		Executions.createComponents("views/ficha_investigacao/ficha_investigacao.zul", target, map);
		
		
	}
   
   public void onClick$btn_actualizar() {
	   
	    
	    utente.setUserUpdated(user.getId());
	    
	    utente.setNome(txtNome.getText());
	    utente.setNacionalidade((String)cbxNacionalidade.getSelectedItem().getValue());
	    utente.setGenero((String)(cbxGenero.getSelectedItem().getValue()));
	    utente.setDataNascimento(dtb_dataNascimento.getValue());
	    utente.setEmail(txtEmail.getValue());
	    utente.setContacto(txtContacto.getValue());
	    utente.setEndereco(txtEndereco.getValue());
	    utente.setTipo_utente((TipoUtente)(cbxTipoUtente.getSelectedItem().getValue()));
	    utente.setDistrito((Distrito)(cbxDistrito.getSelectedItem().getValue()));
	    utente.setUnidade((UnidadeOrganica)(cbxUnidade.getSelectedItem().getValue()));
	    
	    utenteService.update(utente);
	    
	    
	    //ut.gravarDadosUtente(utente);
	    
	    cli.Msg("Dados Actualizados com Sucesso!", win); 
		
		
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		target.getChildren().clear();
		Executions.createComponents("views/ficha_investigacao/ficha_investigacao.zul", target, map);
		
		
	}
   
    
     
    

}
