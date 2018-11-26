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
    static String query = "select * from regions where  lower(region_name) like ?";

    public static void main(String[] args) {
        LocalDateTime time = LocalDateTime.now();
        System.out.println(String.format("Hello World at %s", time));

        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, "%a%");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println(
                        String.format("{ region_id : %s, region_name: %s }",
                                resultSet.getInt(1),
                                resultSet.getString(2))
                );
            }

            statement.close();
            resultSet.close();
            conn.close();
            System.out.println("Berhasil terkoneksi ke database!");

        } catch (SQLException e) {
            System.out.println("Tidak bisa konek ke database");
            e.printStackTrace();
        }
    }
}
