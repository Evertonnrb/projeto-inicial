package br.com.bean;

import br.com.persistence.GenericDao;
import br.com.persistence.anotations.Transactional;
import br.com.persistence.dao.GenericDaoImpl;
import br.com.persistence.model.Projeto;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static br.com.util.FacesUtils.*;
import static br.com.util.StringUtils.like;

/**
 * @author - Everton Ribeiro
 * @email - everton.nrb@gmail.com
 **/
@Named
@ViewScoped
public class TestBean implements Serializable {

    @Inject
    private GenericDao<Projeto> dao;
    private Projeto projeto;
    private List<Projeto> projetos;

    @PostConstruct
    public void init(){
        projetos = dao.listAll();
    }

    @Transactional
    public void construct(){
        //Projeto p = new Projeto();
        //p.setNome("vai manolo");
        //Projeto p2 = new Projeto();
        //p2.setNome("vai manolo 2");
        //dao.save(p);
        //dao.save(p2);

        //System.out.println("IIIIIIIIIIIIAAAAAAH");
        //List<Projeto> pro = dao.findByHlQuery("buscarPorNome", Collections.singletonList("Vai manolo"),0);
        //List<Projeto> pro2 = dao.findByHlQuery("buscarPorNomeEId", Arrays.asList(like("\"manolo\""),42L),0 );
        addSucessMessage("blx");
        System.out.println(projeto);
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }
}
