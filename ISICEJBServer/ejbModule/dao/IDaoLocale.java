package dao;

import java.util.List;

import jakarta.ejb.Local;

@Local
public interface IDaoLocale <T> {
	
	public T create(T o);
	public boolean delete(int id);
	public T update(T o);
	public T findById(int id);
	public List<T> findAll();

}
