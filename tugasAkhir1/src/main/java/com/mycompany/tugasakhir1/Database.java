/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hanif
 */
public class Database {
    private static Connection conn;
    
    public static Connection getConnection() throws ClassNotFoundException{
        try{
            if(conn == null || conn.isClosed()){
                 Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/database_foodcycle?zeroDateTimeBehavior=CONVERT_TO_NULL",
                        "root",
                        ""
                );
            }
        }catch(SQLException e){
            System.out.println("Koneksi gagal: "+e.getMessage());
        }
        return  conn;
    }
}
