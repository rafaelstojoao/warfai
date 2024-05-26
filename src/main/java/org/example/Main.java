package org.example;

import java.sql.SQLException;

public class Main {
    static Database db = new Database();
    public static void main(String[] args) throws SQLException{
        db.connect();
        create_tables();
        IntUsuario interacao = new IntUsuario();
        Equipe fai = interacao.MenuEquipe();
        System.out.println("Equipe 1: \n");
        fai.dump_info();

        Equipe fatec = Equipe.equipe_random();
        System.out.println("Equipe 2: \n");
        fatec.dump_info();

        fai.battle(fatec);
    }


    public static void create_tables() throws SQLException {
        db.write("CREATE TABLE IF NOT EXISTS Classe(" +
                "cod_classe INT PRIMARY KEY AUTO_INCREMENT," +
                "nome VARCHAR(50) NOT NULL," +
                "ataque INT NOT NULL," +
                "defesa INT NOT NULL," +
                "vida INT NOT NULL" +
                ")");
        db.write("CREATE TABLE IF NOT EXISTS Equipe(" +
                "cod_equipe INT PRIMARY KEY AUTO_INCREMENT," +
                "nome VARCHAR(50) NOT NULL," +
                "vitoria INT NOT NULL DEFAULT 0," +
                "derrota INT NOT NULL DEFAULT 0," +
                "descricao TEXT" +
                ")");
        db.write("CREATE TABLE IF NOT EXISTS Batalhas(" +
                "cod_batalha INT PRIMARY KEY AUTO_INCREMENT," +
                "turnos INT NOT NULL DEFAULT 1," +
                "cod_equipe1 INT NOT NULL," +
                "cod_equipe2 INT NOT NULL," +
                "cod_equipe_vitoriosa INT NOT NULL," +
                "CONSTRAINT fk_equipe1 FOREIGN KEY(cod_equipe1) REFERENCES Equipe(cod_equipe)," +
                "CONSTRAINT fk_equipe2 FOREIGN KEY(cod_equipe2) REFERENCES Equipe(cod_equipe)," +
                "CONSTRAINT fk_equipe_vitoriosa FOREIGN KEY(cod_equipe_vitoriosa) REFERENCES Equipe(cod_equipe)" +
                ")");
        db.write("CREATE TABLE IF NOT EXISTS Turno(" +
                "cod_turno INT PRIMARY KEY," +
                "cod_batalha INT NOT NULL," +
                "CONSTRAINT fk_batalha FOREIGN KEY(cod_batalha) REFERENCES Batalhas(cod_batalha)" +
                ")");
        db.write("CREATE TABLE IF NOT EXISTS Player(" +
                "cod_player INT PRIMARY KEY AUTO_INCREMENT," +
                "nickname VARCHAR(50) NOT NULL," +
                "nivel INT NOT NULL DEFAULT 1," +
                "exp INT NOT NULL DEFAULT 0," +
                "cod_classe INT NOT NULL," +
                "cod_equipe INT NOT NULL," +
                "CONSTRAINT fk_classe FOREIGN KEY(cod_classe) REFERENCES Classe(cod_classe)," +
                "CONSTRAINT fk_equipe FOREIGN KEY(cod_equipe) REFERENCES Equipe(cod_equipe)" +
                ")");
    }
}