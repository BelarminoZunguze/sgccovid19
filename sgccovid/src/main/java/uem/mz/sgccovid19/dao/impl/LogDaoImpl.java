package uem.mz.sgccovid19.dao.impl;

import uem.mz.sgccovid19.dao.LogDao;
import uem.mz.sgccovid19.entity.Log;

import org.springframework.stereotype.Repository;

@Repository("logDao")
public class LogDaoImpl extends GenericDaoImpl<Log> implements LogDao {

}
