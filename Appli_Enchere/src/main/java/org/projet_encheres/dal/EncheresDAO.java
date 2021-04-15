package org.projet_encheres.dal;

import java.util.List;

import org.projet_encheres.bo.Encheres;

public interface EncheresDAO extends DAO<Encheres>{
	
	public Encheres selectMaxByArticleId(int id) throws Exception;

}
