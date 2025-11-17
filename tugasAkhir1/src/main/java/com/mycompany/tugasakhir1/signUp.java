/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1;

import java.sql.Connection;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
/**
 *
 * @author hanif
 */

public class signUp  {
    
    
    
  public void processSignUp(String username,String email,String pass,String alamat,String nama)throws IOException{
      System.out.println("Data");
        System.out.println(username);
        System.out.println(pass);
        System.out.println(email);
       
       
  }
  public void InputToDatabase(String username,String email,String pass,String alamat,String nama) throws SQLException{
    try(Connection conn = Database.getConnection()){
    String sql = "INSERT INTO konsumen(username,passwd,nama_konsumen,alamat,email) VALUES (?,?,?,?,?)";
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1, username);
    ps.setString(2, pass);
    ps.setString(3, nama);
    ps.setString(4, alamat);
    ps.setString(5, email);
    ps.executeUpdate();
    
        System.out.println("Berhasil update database");
    }catch(Exception e){
    }
  }
   public boolean SelectFromDatabase(String usernameOrEmail,String pass) throws ClassNotFoundException, SQLException{
    try(Connection conn = Database.getConnection()){
     String sql = "SELECT * FROM KONSUMEN WHERE (username = ? OR email = ?) AND Passwd = ?" ;
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1, usernameOrEmail);
    ps.setString(2, usernameOrEmail);
    ps.setString(3, pass);
    ResultSet rs = ps.executeQuery();
   return rs.next();
   }
}
}
