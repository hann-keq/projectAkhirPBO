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
    
    
    
 
  public void InputToDatabase(String username,String password,String nama,String email,String alamat) {
    try{
        Connection conn = Database.getConnection();
        String sql = "INSERT INTO konsumen(username,passwd,nama_konsumen,email,alamat) VALUES (?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        ps.setString(3, nama);
        ps.setString(4, email);
        ps.setString(5, alamat);
        ps.execute();
    
        System.out.println("Berhasil update database");
    }catch(Exception e){
        e.printStackTrace();
    }
  }
   public boolean cariAkun(String email,String pass){
       boolean found = false;
    try{
         Connection conn = Database.getConnection();
         String sql = "SELECT * FROM KONSUMEN WHERE email = ? AND passwd = ?" ;
         PreparedStatement ps = conn.prepareStatement(sql);
         ps.setString(1, email);
         ps.setString(2, pass);
         ResultSet rs = ps.executeQuery();
         found = rs.next();
   
   }catch(Exception e){
        e.printStackTrace();
   }return found;
}
   
   public boolean CekUsername(String username){
       boolean cocok = false;
    try{
        Connection conn = Database.getConnection();
        String sql = "SELECT * FROM konsumen Where username = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        cocok = rs.next();
        System.out.println(cocok);
    }catch(Exception e){
        e.printStackTrace();
    }return cocok;
   }
}
