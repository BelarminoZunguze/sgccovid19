package uem.mz.sgccovid19.vm;

import java.io.IOException;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zhtml.Table;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Div;

public class AbstractVM {

	protected Component ui;
	
	@Wire("#dynamic-table")
	protected Table table;

	@Wire(".adv-table")
	protected Div advTable;
	
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

		Selectors.wireComponents(view, this, false);
	}

	@Init
	public void intialize() {

	}
	
	public void refreshDataTable(Component ui) {

		if (ui == null) {
			advTable.invalidate();
			Clients.evalJavaScript("initDataTable()");
		} else {
			ui.invalidate();
			Clients.evalJavaScript("dataInit()");
		}
	}
}
