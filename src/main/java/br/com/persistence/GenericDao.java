package br.com.persistence;

import java.io.Serializable;
import java.util.List;

/**
 * @author - Everton Ribeiro
 * @email - everton.nrb@gmail.com
 **/
public interface GenericDao<T> extends Serializable {
    T save(T t);

    T update(T t);

    List<T> listAll();

    boolean remove(T entity);

    List<T> findByHlQuery(String queryId, List<Object> values, int maxResult);
}
