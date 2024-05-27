package org.example;


import java.util.*;

/**
 * 
 */
public class Mago extends Player {

    public Mago(String name, int level, int health, int atk, int def, int cod_classe) {
        super(name, level, health, atk, def, cod_classe);
    }

    @Override
    public void attack(ArrayList<Player> rest, int selected_player) {
        rest.get(selected_player).health -= Math.max(atk - rest.get(selected_player).def, 0);

        for(int i = 0; i < rest.size(); ++i) {
            if(i == selected_player)
                continue;
            rest.get(i).health -= Math.max((atk/2) - rest.get(i).def, 0);
        }
    }
}