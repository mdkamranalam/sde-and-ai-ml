package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JdbcTransactions {
    public static void main(String[] args) {
        String DB_URL = "jdbc:postgresql://localhost:5433/testdb";
        String DB_USERNAME = "postgres";
        String DB_PASSWORD = "admin123";

        /*
         * Terminal command - psql -h localhost -p 5433 -U postgres -d testdb
         *  */

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Student ID: ");
            long studentId = sc.nextLong();
            System.out.print("Course ID: ");
            long courseId = sc.nextLong();
            System.out.print("Payment ID: ");
            long paymentId = sc.nextLong();

            enrollAfterPayment(connection, studentId, courseId, paymentId);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static void enrollAfterPayment(Connection connection, long studentId, long courseId, long paymentId) throws SQLException {
        String insertPayment = "INSERT INTO payments (student_id, course_id, status) VALUES (?, ?, ?)";
        String insertEnrollment = "INSERT INTO enrollments (student_id, course_id) VALUES (?, ?)";

        try {
            connection.setAutoCommit(false);

            try (
                    PreparedStatement paymentStatus = connection.prepareStatement(insertPayment);
                    PreparedStatement enrollmentStatus = connection.prepareStatement(insertEnrollment)
            ) {
                // Operation 1
                paymentStatus.setLong(1, studentId);
                paymentStatus.setLong(2, courseId);
                paymentStatus.setString(3, "SUCCESS");

                paymentStatus.executeUpdate();

                // Operation 2
                enrollmentStatus.setLong(1, studentId);
                enrollmentStatus.setLong(2, courseId);

                enrollmentStatus.executeUpdate();
            }

            connection.commit();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
