package uem.mz.mint.dao.impl;

import uem.mz.mint.dao.LogDao;
import uem.mz.mint.entity.Log;

import org.springframework.stereotype.Repository;

@Repository("logDao")
public class LogDaoImpl extends GenericDaoImpl<Log> implements LogDao {

}
