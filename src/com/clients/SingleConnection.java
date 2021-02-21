package com.clients;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class SingleConnection {
    String db="magasin_db";
    String user="root";
    String pwd = "";
    String url="jdbc:mysql://localhost:3306/"+db;
    private static Connection connection = null;

    private SingleConnection(){
        try {
            connection = DriverManager.getConnection(url, user, pwd);
            System.out.println("creer inst");
        } catch (SQLException exp) {
            //TODO: handle exception
            exp.printStackTrace();
        }

    }

    public static Connection getConnection(){
        if(connection==null)
            new SingleConnection();
        
        return connection;
    }
}
