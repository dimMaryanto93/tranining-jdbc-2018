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
    static String query = "insert into regions(region_id,region_name) values (?, ?)";

    public static void main(String[] args) {
        LocalDateTime time = LocalDateTime.now();
        System.out.println(String.format("Hello World at %s", time));

        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, 6);
            statement.setString(2, "Apa yaaa");

            statement.executeUpdate();

            statement.close();
            conn.close();
            System.out.println("Berhasil terkoneksi ke database!");

        } catch (SQLException e) {
            System.out.println("Tidak bisa konek ke database");
            e.printStackTrace();
        }
    }
}
