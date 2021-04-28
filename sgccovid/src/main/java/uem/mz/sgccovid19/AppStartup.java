package uem.mz.sgccovid19;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

@Repository("starter")
public class AppStartup implements InitializingBean{
	
	@Override
	public void afterPropertiesSet() throws Exception {
		start();
	}

	@PostConstruct
	public void start() {

	}

	@Transactional
	protected void initPermissions() {

	}

}
