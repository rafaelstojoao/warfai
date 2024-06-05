package org.example;

import java.util.*;

public class Player {
    protected int id = 0;
    protected String name;
    protected PlayerClass player_class;
    protected int level = 1;
    protected double exp = 0;

    public Player(String name, int level, PlayerClass player_class) {
        this.name = name;
        this.level = level;
        this.player_class = player_class;
        this.player_class.levelup(1, level);
    }

    public void set_id(int i) { this.id = i; }

    public void attack(ArrayList<Player> group, int selected_player) {
        player_class.attack(group, selected_player);
    }

    public boolean is_dead() { return player_class.health <= 0; }

    public int class_id() { return player_class.id(); }

    public void levelup(int desired_level) {
        player_class.levelup(level, desired_level);
    }
}