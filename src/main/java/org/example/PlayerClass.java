package org.example;

import java.util.ArrayList;

public abstract class PlayerClass {
    protected int level = 1;
    protected int health;
    protected int atk;
    protected int def;

    public PlayerClass(int health, int atk, int def) {
        this.health = health;
        this.atk = atk;
        this.def = def;
    }

    public abstract int id();
    public abstract void attack(ArrayList<Player> group, int selected_player);
}
