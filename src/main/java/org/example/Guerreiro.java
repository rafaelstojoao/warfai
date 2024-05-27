package org.example;


import java.util.*;

/**
 * 
 */
public class Guerreiro extends Player {
    public Guerreiro(String name, int level, int health, int atk, int def, int cod_classe) {
        super(name, level, health, atk, def, cod_classe);
    }

    @Override
    public void attack(ArrayList<Player> rest, int selected_player) {
        rest.get(selected_player).health -=Math.max(atk - rest.get(selected_player).def, 1);
    }

}