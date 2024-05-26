package org.example;


import java.util.*;

/**
 * 
 */
public abstract class Personagem {
    public Personagem(String name, int level, int health, int atk, int def, int cod_classe) {
        Nome = name;
        Nivel = level;
        Vida = health;
        Ataque = atk;
        Defesa = def;
        Cod_Classe = cod_classe;
    }
    /**
     * 
     */
    protected String Nome;

    /**
     * 
     */
    protected int Nivel;

    /**
     * 
     */
    protected int Vida;

    /**
     * 
     */
    protected int Ataque;

    /**
     * 
     */
    protected int Defesa;
    
    /**
     * 
     */
    protected int Cod_Classe;

    /**
     * @param p 
     * @param rest
     */
    public abstract void ataque(Personagem... rest);
    public abstract void ataque(ArrayList<Personagem> rest);
}