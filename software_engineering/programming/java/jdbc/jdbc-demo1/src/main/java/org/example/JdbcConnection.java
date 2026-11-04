package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
    public static void main(String[] args) {
        String DB_URL = "jdbc:postgresql://localhost:5433/testdb";
        String DB_USERNAME = "postgres";
        String DB_PASSWORD = "admin123";

        /*
         * Terminal command - psql -h localhost -p 5433 -U postgres -d testdb
         *  */

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            System.out.println("Connection: " + !connection.isClosed());
            System.out.println("Database: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
