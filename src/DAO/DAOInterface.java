package DAO;

import java.util.ArrayList;

public interface DAOInterface<T> {
	public int insert(T t);
	public int update(T t);
	public int delete(int id);
	public T selectByID(int id);
	public ArrayList<T> getAll();
}
