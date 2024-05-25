package org.example;


import java.util.*;

/**
 * 
 */
public class PersonagemFactory {

    int level = 0;

    public PersonagemFactory(int level){
        this.level = level;
    }

    public Personagem makeGuerreiro(String name) {
        return new Guerreiro(name, level, 100, 3, 7, 0);
    }

    /**
     * 
     */
    public Personagem makeMago(String name) {
        return new Mago(name, level, 100, 9, 1, 2);
    }

    /**
     * 
     */
    public Personagem makeArqueiro(String name) {
        return new Arqueiro(name, level, 100, 7, 3, 1);
    }
}