package DAO;

import java.sql.PreparedStatement;
import java.util.List;
import DTO.Entity;

/**Interface for access to information of database*/

public interface AbstractDAO <T extends Entity>{
	
	public abstract List<T> findAll();
	public abstract boolean delete(int id);
	public abstract boolean create(T entity);
	public abstract T update(T entity);
	public abstract void close(PreparedStatement ps);

}
