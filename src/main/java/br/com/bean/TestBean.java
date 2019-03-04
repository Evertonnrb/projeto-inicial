package br.com.bean;

import br.com.persistence.GenericDao;
import br.com.persistence.anotations.Transactional;
import br.com.persistence.dao.GenericDaoImpl;
import br.com.persistence.model.Projeto;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author - Everton Ribeiro
 * @email - everton.nrb@gmail.com
 **/
@Named
@ViewScoped
public class TestBean implements Serializable {

    @Inject
    private GenericDao<Projeto> dao;

    @Transactional
    public void init(){
        Projeto p = new Projeto();
        p.setNome("vai manolo");
        Projeto p2 = new Projeto();
        p2.setNome("vai manolo 2");
        //dao.save(p);
        //dao.save(p2);

        //System.out.println("IIIIIIIIIIIIAAAAAAH");
        List<Projeto> pro = dao.findByHlQuery("buscarPorNome", Collections.singletonList("Vai manolo"),0);
        System.out.println(pro);
    }




}
