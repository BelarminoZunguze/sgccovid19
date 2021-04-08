package uem.mz.mint.util;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Window;
import org.zkoss.zk.ui.select.annotation.Listen;


@SuppressWarnings("rawtypes")
public class showClientNotification extends GenericForwardComposer{

	private static final long serialVersionUID = 1L;

	public void Msg(String msg, Window form){		
	Clients.showNotification(""+msg.toUpperCase()+"", "info", form, "middle_center", 4400);
	}
	
	public void MsgUpdate(String msg, Window form){
	Clients.showNotification(""+msg.toUpperCase()+" - "+" actualizado(a) com Sucesso", "info", form, "middle_center", 4400);
	}
	
	public void MsgNumeroSuperior(String msg, String msg1, Window form){
		Clients.showNotification("O numero de "+msg.toUpperCase()+" - nao pode ser superior ao numero de "+msg1.toUpperCase()+" ", "info", form, "middle_center", 4400);
	}
	
	public void MsgNumeroArtigosRestantes(String msg, String msg1, String msg2, Window form){
		Clients.showNotification("O ultimo "+msg.toUpperCase()+" registado foi "+msg1.toUpperCase()+" - e ainda pode registar "+msg2.toUpperCase(), "info", form, "middle_center", 4400);
	}
	
	public void MsgNotificacoes( Window form){
		Clients.showNotification("Notificacoes enviadas com sucesso ", "info", form, "middle_center", 4400);
	}
	
	public void MsgAll( String msg, Window form){
		Clients.showNotification(msg, "info", form, "middle_center", 4400);
	}
}
