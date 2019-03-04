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

    void remove(T entity);

    T findById(Serializable id);

    List<T> findyByQueryNoParameters(String queryId, int maxResults);

    List<T> findByHlQuery(String queryId, List<Object> values, int maxResult);
}
