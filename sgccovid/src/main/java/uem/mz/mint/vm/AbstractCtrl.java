package uem.mz.mint.vm;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import uem.mz.mint.entity.Log;
import uem.mz.mint.entity.administracao.User;
import uem.mz.mint.service.LogService;
import uem.mz.mint.service.UserService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;

@SuppressWarnings("rawtypes")
public class AbstractCtrl extends GenericForwardComposer {

	private static final long serialVersionUID = 1L;

	@WireVariable
	protected UserService userService;

	@WireVariable
	protected LogService logService;

	protected List<Log> logs;

	protected Authentication authentication = SecurityContextHolder
			.getContext().getAuthentication();
	

	protected User loggeduser;

	private Locale pt = new Locale("pt", "pt");

	SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd", pt);

	@SuppressWarnings("unchecked")
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		Selectors.wireComponents(comp, this, false);

	}

	@SuppressWarnings("unchecked")
	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);
		userService = (UserService) SpringUtil.getBean("userService");
	

	}

	public User getLoggeduser() {
		return loggeduser;
	}

	public void setLoggeduser(User loggeduser) {
		this.loggeduser = loggeduser;
	}

}
