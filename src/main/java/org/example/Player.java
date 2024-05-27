package org.example;

import java.util.*;

public abstract class Player {
    protected String name;
    protected int level;

    protected int health;
    protected int atk;
    protected int def;
    protected int Cod_Classe;
    public Player(String name, int level, int health, int atk, int def, int cod_classe) {
        this.name = name;
        this.level = level;
        this.health = health;
        this.atk = atk;
        this.def = def;
        Cod_Classe = cod_classe;
    }

    public abstract void attack(ArrayList<Player> group, int selected_player);
}