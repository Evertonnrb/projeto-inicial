package br.com.persistence.dao;

import br.com.persistence.GenericDao;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;

/**
 * @author - Everton Ribeiro
 * @email - everton.nrb@gmail.com
 **/
public class GenericDaoFactory {

    @Inject
    private EntityManager entityManager;

    @SuppressWarnings({"rawtypes","unchecked"})
    @Produces
    @Dependent
    public <T> GenericDao<T> createDao(InjectionPoint point){
        ParameterizedType type = (ParameterizedType) point.getType();
        Class classe = (Class) type.getActualTypeArguments()[0];
        return new GenericDaoImpl<T>(classe, entityManager);
    }
}
