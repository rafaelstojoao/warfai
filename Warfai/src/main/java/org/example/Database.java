package org.example;


import java.sql.*;
import java.util.ArrayList;
import org.mariadb.jdbc.Driver;

public class Database {
    public Connection connection = null;
    public Statement statement = null;

    public void connect() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Warfai", "root", "");
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
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
