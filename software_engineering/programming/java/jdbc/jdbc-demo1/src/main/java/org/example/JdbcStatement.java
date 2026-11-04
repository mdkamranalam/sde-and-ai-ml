package org.example;

import java.sql.*;
import java.util.Scanner;

public class JdbcStatement {
    public static void main(String[] args) {
        String DB_URL = "jdbc:postgresql://localhost:5433/testdb";
        String DB_USERNAME = "postgres";
        String DB_PASSWORD = "admin123";

        /*
         * Terminal command - psql -h localhost -p 5433 -U postgres -d testdb
         *  */

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter a course title: ");
            String title = sc.nextLine();

            findCourseByTitle(connection, title);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static void findCourseByTitle(Connection connection, String title) {
        String sql = "SELECT id, title FROM courses WHERE title = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    long id = result.getLong("id");
                    String courseTitle = result.getString("title");
                    System.out.println(id + " - " + courseTitle);
                }
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
