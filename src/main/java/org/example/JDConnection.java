package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDConnection {
    private final static String url="jdbc:postgresql://localhost:5432/postgres";
    private final static String user="postgres";
    private final static String password="1234";

    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection= DriverManager.getConnection(url,user,password);
            System.out.println("connected");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
}}
