package com.tabeldata.bootcamp;

import org.joda.time.LocalDateTime;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 */
public class App {

    static String url = "jdbc:postgresql://127.0.0.1:5432/hr";
    static String username = "hr";
    static String password = "hr";

    public static void main(String[] args) {
        LocalDateTime time = LocalDateTime.now();
        System.out.println(String.format("Hello World at %s", time));

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            conn.setAutoCommit(false);

//            select data
            String query = "select * from regions where  lower(region_name) like ?";
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
            System.out.println("Statement select berhasil");

//            insert data baru
            query = "insert into regions (region_name) values (?)";
            statement = conn.prepareStatement(query);
            statement.setString(1, "batch 1");
            statement.addBatch();
            statement.setString(1, "batch 2");
            statement.addBatch();

            List<String> values = Arrays.asList("batch 3", "batch 4", "batch 5");
            for(String value: values ){
                statement.setString(1, value);
                statement.addBatch();
            }
            statement.executeBatch();


            System.out.println("statement insert berhasil");

            //            insert data baru
            query = "delete from regions where region_id in (?, ?)";
            statement = conn.prepareStatement(query);
            statement.setInt(1, 5);
            statement.setInt(2, 6);
            statement.executeUpdate();
            System.out.println("statement delete berhasil");

            conn.commit();
            statement.close();
            resultSet.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Tidak bisa konek ke database");
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

        }
    }
}
