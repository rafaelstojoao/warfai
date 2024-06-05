package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class DatabaseBuilder {
    private Optional<String> driver;
    private Optional<String> host;
    private Optional<String> port;
    private Optional<String> username;
    private Optional<String> password;
    private String database;

    DatabaseBuilder set_driver(String driver) {
        this.driver = Optional.of(driver);
        return this;
    }

    DatabaseBuilder set_host(String host) {
        this.host = Optional.of(host);
        return this;
    }

    DatabaseBuilder set_port(String port) {
        this.port = Optional.of(port);
        return this;
    }

    DatabaseBuilder set_database(String database) {
        this.database = database;
        return this;
    }

    DatabaseBuilder set_username(String username) {
        this.username = Optional.of(username);
        return this;
    }

    DatabaseBuilder set_password(String password) {
        this.password = Optional.of(password);
        return this;
    }

    Database build() throws SQLException {
        Connection con = DriverManager.getConnection(String.format("jdbc:%s://%s:%s/%s",
                driver.orElse("mysql"), host.orElse("localhost"), port.orElse("3306"), database),
                username.orElse("root"), password.orElse(""));
        return new Database();
    }
}
