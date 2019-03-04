package br.com.persistence.dao;

import br.com.persistence.GenericDao;
import br.com.config.XMLService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author - Everton Ribeiro
 * @email - everton.nrb@gmail.com
 **/
public class GenericDaoImpl<T> implements GenericDao<T> {

    @PersistenceContext
    private EntityManager manager;

    private final Class<T> classe;

    private static final XMLService hqlQuery;
    private static final XMLService sqlQuery;

    static {
        hqlQuery = new XMLService("hql.xml");
        sqlQuery = new XMLService("sql.xml");
    }


    public GenericDaoImpl(Class<T> classe,EntityManager em){
        this.classe = classe;
        this.manager = em;
    }

    @Override
    public T save(T t) {
        manager.persist(t);
        manager.flush();
        return t;
    }

    @Override
    public T update(T t) {
        manager.merge(t);
        return t;
    }

    @Override
    public List<T> listAll() {
        String sql = "select e from "+this.classe.getSimpleName()+" e ";
        TypedQuery<T> query = manager.createQuery(sql,this.classe);
        return query.getResultList();

    }

    @Override
    public void remove(T entity) {
         manager.remove(manager.contains(entity) ? entity : manager.merge(entity));
         manager.flush();
    }

    @Override
    public T findById(Serializable id) {
        return manager.find(this.classe,id);
    }

    @Override
    public List<T> findyByQueryNoParameters(String queryId, int maxResults){
        String hql = hqlQuery.fileValue(queryId);
        TypedQuery<T> query = manager.createQuery(hql,this.classe);
        return maxResults == 0 ? query.getResultList() : query.setMaxResults(maxResults).getResultList();
    }

    @Override
    public List<T> findByHlQuery(String queryId, List<Object> values, int maxResult){
        String hql = hqlQuery.fileValue(queryId);
        Pattern  pattern = Pattern.compile("(:\\w+)");
        Matcher matcher = pattern.matcher(hql);
        List<String> params = new ArrayList<>();
        while (matcher.find()){
            params.add(matcher.group().replace(":",""));
        }
        TypedQuery<T> query = manager.createQuery(hql,this.classe);
        for (int i = 0; i < params.size();i++){
            query.setParameter(params.get(i),values.get(i));
        }
        return maxResult == 0 ? query.getResultList() : query.setMaxResults(maxResult).getResultList();
    }
}
