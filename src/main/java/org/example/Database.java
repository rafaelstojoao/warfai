package org.example;


import java.sql.*;

public class Database {
    public Connection connection = null;
    public Statement statement = null;

    public boolean connect() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/Warfai", "fernando", "adamantina");
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public void write(String sql) throws SQLException {
        try {
            statement = connection.createStatement();
            if(statement.execute(sql)) {
                System.out.println("Gravado");
            }
        }
        finally {
            statement.close();
        }
    }

    public void listdata() throws SQLException {
        try {
            statement = connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM ");
            while(res.next()) {
                String nome = res.getString("nome");
                System.out.println(nome);
            }
        }
        finally {
            statement.close();
        }
    }
    
    public ResultSet consulta(String query) throws SQLException {
        try {
            statement = connection.createStatement();
            ResultSet res = statement.executeQuery(query);
            return res;
        }
        finally {
            statement.close();
        }
    }
}
