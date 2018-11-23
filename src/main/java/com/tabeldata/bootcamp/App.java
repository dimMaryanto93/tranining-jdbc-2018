package com.tabeldata.bootcamp;

import org.joda.time.LocalDateTime;

import java.sql.*;

/**
 * Hello world!
 */
public class App {

    static String url = "jdbc:postgresql://127.0.0.1:5432/hr";
    static String username = "hr";
    static String password = "hr";
    static String query = "select department_id, department_name  from departments where department_id = 10";

    public static void main(String[] args) {
        LocalDateTime time = LocalDateTime.now();
        System.out.println(String.format("Hello World at %s", time));

        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                String data = String.format("%s, %s",
                        resultSet.getString(1),
                        resultSet.getString("department_name"));
                System.out.println(data);
            }

            resultSet.close();
            statement.close();
            conn.close();
            System.out.println("Berhasil terkoneksi ke database!");

        } catch (SQLException e) {
            System.out.println("Tidak bisa konek ke database");
            e.printStackTrace();
        }
    }
}
