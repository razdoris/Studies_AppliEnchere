package org.projet_encheres.dal;

import java.util.List;

public interface DAO<T> {

	public void insert(T objet) throws Exception;	
	public List<T> selectAll() throws Exception;
	public T selectById(int id) throws Exception;
	public void delete(T objet) throws Exception;
}
