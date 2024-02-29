package com.kongo.banking.service;

import java.util.List;

public interface AbstractService<T, I extends Number> {

    Integer save(T dto);

    List<T> findAll();

    T findById(Integer id);

    void delete(Integer id);
}
