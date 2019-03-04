package br.com.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author - Everton Ribeiro
 * @email - everton.nrb@gmail.com
 **/
public class JPAUtil {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    @Produces
    @RequestScoped
    public EntityManager criarEntityManager(){
        return emf.createEntityManager();
    }

    public void fecharEmf(@Disposes EntityManager em){
        if(em!=null && em.isOpen()){
            em.close();
        }
    }
}
