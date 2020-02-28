package com.core.dao;

import java.util.List;

import com.core.exception.BusinessException;

public interface AbstractDAO<T> {
   
    void add(T t) throws BusinessException;
	
	T get(int id) throws BusinessException;
     
    List<T> getAll() throws BusinessException;     
     
    void update(T t) throws BusinessException;
     
    void delete(int id) throws BusinessException;
}