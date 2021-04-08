package uem.mz.mint.dao;

import java.util.List;

import uem.mz.mint.entity.IdEntity;

public interface GenericDao<T extends IdEntity> {
	
	public long count();

    public T create(T t);

    public void delete(T id);

    public T find(Object id);
    
    public List<T> getAll();

    public T update(T t); 
    
    public T createAndReturn(T t); 
    
    public T findById(final Object id);
    
    public void saveOrUpdate(T t); 

    public T first();
    
    public T last();
}
