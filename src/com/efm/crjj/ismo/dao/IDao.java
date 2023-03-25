package com.efm.crjj.ismo.dao;

import java.util.List;

public interface IDao<T> {
	
	List<T> getAll();
	T getOne(int id);
	boolean save(T obj);
	boolean delete(T obj);

}
