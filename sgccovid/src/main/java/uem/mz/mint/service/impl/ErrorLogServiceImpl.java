package uem.mz.mint.service.impl;

import uem.mz.mint.entity.ErrorLog;
import uem.mz.mint.service.ErrorLogService;

import org.springframework.stereotype.Service;

@Service("errorLogService")
public class ErrorLogServiceImpl extends GenericServiceImpl<ErrorLog>
		implements ErrorLogService {

}
