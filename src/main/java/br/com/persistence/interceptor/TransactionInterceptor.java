package br.com.persistence.interceptor;

import br.com.persistence.anotations.Transactional;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;

/**
 * @author - Everton Ribeiro
 * @email - everton.nrb@gmail.com
 **/
@Interceptor
@Transactional
public class TransactionInterceptor implements Serializable {

    @Inject
    private EntityManager manager;

    @AroundInvoke
    public Object invoke(InvocationContext context)throws Exception{
        EntityTransaction transaction = manager.getTransaction();
        Object result = null;
        try{
        if(!transaction.isActive()){
            transaction.begin();
            result = context.proceed();
            if(!transaction.getRollbackOnly()){
                transaction.commit();
            }
            else {
                transaction.rollback();
            }
        }
        }catch (Exception e){
            e.printStackTrace();
            if (transaction.isActive()){
                transaction.rollback();
                System.out.println("transação não realizada");
            }
        }
        return result;
    }
}
