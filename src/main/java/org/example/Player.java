package org.example;

import java.util.*;

public class Player {
    protected int id = 0;
    protected String name;
    protected PlayerClass player_class;
    protected int level = 1;
    protected double exp = 0;

    public Player(String name, PlayerClass player_class) {
        this.name = name;
        this.player_class = player_class;
    }

    public void set_id(int i) { this.id = i; }

    public void attack(ArrayList<Player> group, int selected_player) {
        player_class.attack(group, selected_player);
    }

    public boolean is_dead() { return player_class.health <= 0; }

    public int class_id() { return player_class.id(); }
}