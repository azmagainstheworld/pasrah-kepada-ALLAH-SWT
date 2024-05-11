package com.gotpb.tubespbokelompok7;

import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection(){
        String databaseName = "gotpb";
        String databaseUser = "root";
        String databasePassword = "";
        String databaseUrl = "jdbc:mysql://localhost/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return databaseLink;
    }

}
