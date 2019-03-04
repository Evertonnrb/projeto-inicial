package br.com.persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author - Everton Ribeiro
 * @email - everton.nrb@gmail.com
 **/
@Entity
public class Projeto extends AbstractEntity {

    private String nome;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
