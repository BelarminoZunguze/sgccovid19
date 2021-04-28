package uem.mz.sgccovid19.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import uem.mz.sgccovid19.dao.GenericDao;
import uem.mz.sgccovid19.entity.IdEntity;
import uem.mz.sgccovid19.service.GenericService;


@Transactional
public abstract class GenericServiceImpl<T extends IdEntity> implements
		GenericService<T> {

	@Autowired
	protected GenericDao<T> specificDao;
	
	
	public T create(T t) {
		return specificDao.create(t);
	}

	
	public List<T> getAll() {
		return specificDao.getAll();
	}

	
	public T find(Long id) {
		return specificDao.find(id);
	}

	public T createAndReturn(T t){
		return specificDao.createAndReturn(t);
	}
	
	public T update(T t) {
		t.setUpdated(new Date());
		return specificDao.update(t);
	}

	
	public long count() {
		return specificDao.count();
	}

	
	public T first() {
		return specificDao.first();
	}
	
	
	public T last() {
		return specificDao.last();
	}
	
	
	public void delete(T id) {
		specificDao.delete(id);
	}
	
	public void saveOrUpdate(T t){
		
		specificDao.saveOrUpdate(t);
	}
}
