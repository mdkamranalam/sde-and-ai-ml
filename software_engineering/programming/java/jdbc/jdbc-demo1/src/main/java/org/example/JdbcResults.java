package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

record Course(long id, String title, double price) {
}

public class JdbcResults {
    public static void main(String[] args) {
        String DB_URL = "jdbc:postgresql://localhost:5433/testdb";
        String DB_USERNAME = "postgres";
        String DB_PASSWORD = "admin123";

        /*
         * Terminal command - psql -h localhost -p 5433 -U postgres -d testdb
         *  */

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            findAllCourses(connection);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static void findAllCourses(Connection connection) {
        List<Course> courseList = new ArrayList<>();
        String sql = "SELECT id, title, price FROM courses";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    long id = result.getLong("id");
                    String title = result.getString("title");
                    double price = result.getDouble("price");

                    Course resultCourse = new Course(id, title, price);
                    courseList.add(resultCourse);
                }

                System.out.println("Course list: " + courseList);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
