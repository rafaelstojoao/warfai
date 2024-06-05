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
        try(Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
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
