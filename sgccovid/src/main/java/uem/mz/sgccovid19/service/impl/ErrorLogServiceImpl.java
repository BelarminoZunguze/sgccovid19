package uem.mz.sgccovid19.service.impl;

import uem.mz.sgccovid19.entity.ErrorLog;
import uem.mz.sgccovid19.service.ErrorLogService;

import org.springframework.stereotype.Service;

@Service("errorLogService")
public class ErrorLogServiceImpl extends GenericServiceImpl<ErrorLog>
		implements ErrorLogService {

}
