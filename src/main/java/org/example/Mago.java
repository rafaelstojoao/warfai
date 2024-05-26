package org.example;


import java.util.*;

/**
 * 
 */
public class Mago extends Personagem {

    public Mago(String name, int level, int health, int atk, int def, int cod_classe) {
        super(name, level, health, atk, def, cod_classe);
    }

    /**
     * @param p 
     * @param rest
     */
    @Override
    public void ataque(Personagem... rest) {
        for(var r : rest) {
            r.Vida -= Math.max(Ataque - r.Defesa, 0);
        }
    }

    @Override
    public void ataque(ArrayList<Personagem> rest) {
        rest.get(0).Vida -= Math.max(Ataque - rest.get(0).Defesa, 0);

        for(int i = 1; i < rest.size(); ++i) {
            rest.get(i).Vida -= Math.max((Ataque/2) - rest.get(i).Defesa, 0);
        }
    }
}