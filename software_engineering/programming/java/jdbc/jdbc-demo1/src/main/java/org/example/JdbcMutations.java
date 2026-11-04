package org.example;

import java.sql.*;

public class JdbcMutations {
    public static void main(String[] args) {
        String DB_URL = "jdbc:postgresql://localhost:5433/testdb";
        String DB_USERNAME = "postgres";
        String DB_PASSWORD = "admin123";

        /*
         * Terminal command - psql -h localhost -p 5433 -U postgres -d testdb
         *  */

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
//            Scanner sc = new Scanner(System.in);
//            System.out.print("Title: ");
//            String title = sc.nextLine();
//            System.out.print("Price: ");
//            double price = sc.nextDouble();

//            insertCourse(connection, title, price);
//            System.out.println(updateCourse(connection,11, 199.00));
            System.out.println(deleteCourse(connection, 11));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static long insertCourse(Connection connection, String title, double price) throws SQLException {
        String sql = "INSERT INTO courses (title, price) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, title);
            statement.setDouble(2, price);

            statement.executeUpdate();

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getLong(1);
                }

                throw new SQLException("Course inserted by no ID returned");
            }
        }
    }

    static int updateCourse(Connection connection, long courseId, double newPrice) throws SQLException {
        String sql = "UPDATE courses SET price = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, newPrice);
            statement.setLong(2, courseId);

            return statement.executeUpdate();
        }
    }

    static int deleteCourse(Connection connection, long courseId) throws SQLException {
        String sql = "DELETE FROM courses where id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, courseId);

            return statement.executeUpdate();
        }
    }
}
