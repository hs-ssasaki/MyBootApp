package com.tuyano.springboot;

import java.util.List;

public interface MsgDataDao<T> {
	public List<T> getAll();
	public T findById(long id);
}
