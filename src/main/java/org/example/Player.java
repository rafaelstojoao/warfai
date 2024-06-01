package org.example;

import java.util.*;

public class Player {
    protected String name;
    protected PlayerClass player_class;
    public Player(String name, PlayerClass player_class) {
        this.name = name;
        this.player_class = player_class;
    }

    public void attack(ArrayList<Player> group, int selected_player) {
        player_class.attack(group, selected_player);
    }

    public boolean is_dead() { return player_class.health <= 0; }

    public int class_id() { return player_class.id(); }
}