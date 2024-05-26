package org.example;


import java.util.*;

/**
 * 
 */
public class Guerreiro extends Personagem {
    public Guerreiro(String name, int level, int health, int atk, int def, int cod_classe) {
        super(name, level, health, atk, def, cod_classe);
    }

    /**
     * @param p 
     * @param rest
     */
    @Override
    public void ataque(Personagem... rest) {
        rest[0].Vida -= Math.max(Ataque - rest[0].Defesa, 1);
    }

    @Override
    public void ataque(ArrayList<Personagem> rest) {
        rest.get(0).Vida -=Math.max(Ataque - rest.get(0).Defesa, 1);
    }

}