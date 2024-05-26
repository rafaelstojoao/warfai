package org.example;


import java.util.*;

/**
 * 
 */
public class Arqueiro extends Personagem {
    public Arqueiro(String name, int level, int health, int atk, int def, int cod_classe) {
        super(name, level, health, atk, def, cod_classe);
    }
    /**
     * @param p 
     * @param rest
     */
    @Override
    public void ataque(Personagem... rest) {
        rest[0].Vida -= Ataque;
    }

    @Override
    public void ataque(ArrayList<Personagem> rest) {
        rest.get(0).Vida -= Ataque;
    }

}