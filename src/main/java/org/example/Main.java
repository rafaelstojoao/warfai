package org.example;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static Database db = new Database();

    public static void main(String[] args) throws SQLException{
        db.connect();
        Scanner scan = new Scanner(System.in);
        System.out.println("Resetar o banco? [y/N]");
        if(scan.next().equalsIgnoreCase("y")) {
            drop_tables();
            create_tables();
            setup_tables();
        }
        IntUsuario interacao = new IntUsuario();
        Equipe fai = interacao.MenuEquipe();
        System.out.println("Equipe 1: \n");
        fai.dump_info();

        Equipe fatec = Equipe.equipe_random();
        System.out.println("Equipe 2: \n");
        fatec.dump_info();

        fai.battle(fatec);
    }

    public static void drop_tables() throws SQLException {
        db.write("DROP TABLE IF EXISTS Turno;");
        db.write("DROP TABLE IF EXISTS Player;");
        db.write("DROP TABLE IF EXISTS Batalhas;");
        db.write("DROP TABLE IF EXISTS Equipe;");
        db.write("DROP TABLE IF EXISTS Classe;");
        db.write("DROP TABLE IF EXISTS usuario_humano;");
    }

    public static void create_tables() throws SQLException {
        db.write("CREATE TABLE usuario_humano (" +
                "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                "nome VARCHAR(30) NOT NULL" +
                ");");
        db.write("CREATE TABLE IF NOT EXISTS Classe(" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "nome VARCHAR(50) NOT NULL" +
                ");");
        db.write("CREATE TABLE IF NOT EXISTS Equipe(" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "nome VARCHAR(50) NOT NULL," +
                "vitoria INT NOT NULL DEFAULT 0," +
                "derrota INT NOT NULL DEFAULT 0," +
                "descricao TEXT," +
                "id_usuario_humano INT," +
                "CONSTRAINT FOREIGN KEY(id_usuario_humano) REFERENCES usuario_humano(id)" +
                ");");
        db.write("CREATE TABLE IF NOT EXISTS Batalhas(" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "turnos INT NOT NULL DEFAULT 1," +
                "id_equipe1 INT," +
                "id_equipe2 INT," +
                "id_equipe_vitoriosa INT," +
                "CONSTRAINT FOREIGN KEY(id_equipe1) REFERENCES Equipe(id)," +
                "CONSTRAINT FOREIGN KEY(id_equipe2) REFERENCES Equipe(id)," +
                "CONSTRAINT FOREIGN KEY(id_equipe_vitoriosa) REFERENCES Equipe(id)" +
                ");");
        db.write("CREATE TABLE IF NOT EXISTS Player(" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "nickname VARCHAR(50) NOT NULL," +
                "nivel INT NOT NULL DEFAULT 1," +
                "exp INT NOT NULL DEFAULT 0," +
                "id_classe INT NOT NULL," +
                "id_equipe INT NOT NULL," +
                "CONSTRAINT FOREIGN KEY(id_classe) REFERENCES Classe(id)," +
                "CONSTRAINT FOREIGN KEY(id_equipe) REFERENCES Equipe(id)" +
                ");");
        db.write("CREATE TABLE IF NOT EXISTS Turno(" +
                "id INT PRIMARY KEY," +
                "id_batalha INT NOT NULL," +
                "id_atacante INT NOT NULL," +
                "CONSTRAINT FOREIGN KEY(id_atacante) REFERENCES Player(id)," +
                "CONSTRAINT FOREIGN KEY(id_batalha) REFERENCES Batalhas(id)" +
                ");");
    }

    public static void setup_tables() throws SQLException {
        db.write("INSERT INTO Classe(nome) VALUES('Warrior'), ('Archer'), ('Mage');");
    }
}