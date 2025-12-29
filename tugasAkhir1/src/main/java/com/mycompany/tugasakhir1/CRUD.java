/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1;

import com.mycompany.tugasakhir1.Profile.profilKonsumen;
import java.sql.Connection;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
/**
 *
 * @author hanif
 */

public class CRUD  {
    int jumlah = 0;
    List banyak;
     List profile;
     private final List<profilKonsumen> listProfile = new ArrayList<>();
     private final List <Makanan> list = new ArrayList<>();
     
    private void result (ResultSet rs) throws SQLException{
    
    
    while(rs.next()){
                list.add(new Makanan(
                    
                    rs.getString("nama_toko"),
                    rs.getString("nama_kota"),
                    rs.getString("nama_makanan"),
                    rs.getInt("jumlah_tersedia"),
                    rs.getInt("harga"),
                    rs.getInt("jarak"),
                    rs.getString("jenis_makanan"),
                    rs.getString("makanan_minuman")
                  
                ));
                banyak = list;
    }
   
    }
    public List<Makanan>ambilMakanan(){
        list.clear();
        try{
        Connection conn = Database.getConnection();
        String query = """
                       SELECT 
                         
                         t.nama_toko,
                         k.nama_kota,
                         m.nama_makanan,
                         m.jumlah_tersedia,
                         m.harga,
                         m.jarak,
                         m.jenis_makanan,
                         m.makanan_minuman
                       FROM makanan AS m
                       JOIN toko AS t ON m.id_toko = t.id_toko
                       JOIN kota AS k ON m.id_kota = k.id_kota;
                       """;
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query);
            result(rs);
            
             System.out.println("makanan yang diambil "+ banyak.size());
            
        }catch(Exception e){
            e.printStackTrace();
        }return banyak;
        }
    
 public List <Makanan> filterMakanan(String filter){
     list.clear();
 try{
     Connection conn = Database.getConnection();
     String query =  """
                       SELECT 
                         m.id_makanan,
                         t.nama_toko,
                         k.nama_kota,
                         m.nama_makanan,
                         m.jumlah_tersedia,
                         m.harga,
                         m.jarak,
                         m.jenis_makanan,
                         m.makanan_minuman,
                         ka.kategori
                       FROM makanan AS m
                       JOIN toko AS t ON m.id_toko = t.id_toko
                       JOIN kota AS k ON m.id_kota = k.id_kota
                       JOIN kategori AS ka ON m.id_kategori = ka.id_kategori
                       WHERE ka.kategori = ?;
                     """;
     PreparedStatement ps = conn.prepareStatement(query);
     ps.setString(1, filter);
     ResultSet rs = ps.executeQuery();
     result(rs);
     
     System.out.println("filter "+filter);
     System.out.println("Makanan yang di lolos filter "+ banyak.size());
     
     }catch(Exception e){
             e.printStackTrace();
 }
        return banyak;
 }
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
   public profilKonsumen Login(String email,String username,String pass){ 
    try{
         Connection conn = Database.getConnection();
         String sql = """
                      SELECT 
                          id_konsumen,
                          username,
                          passwd,
                          email,
                          nama_konsumen,
                          alamat,
                          no_telp
                      FROM konsumen
                      WHERE (email = ? OR username = ?) 
                      AND passwd = ?;
                      """ ;
         PreparedStatement ps = conn.prepareStatement(sql);
         ps.setString(1, email);
         ps.setString(2, username);
         ps.setString(3, pass);
         ResultSet rs = ps.executeQuery();
         while (rs.next()){
           profilKonsumen user = new profilKonsumen();
            user.setIdUser(rs.getInt("id_konsumen"));
            user.setNamaKonsumen(rs.getString("nama_konsumen"));
            user.setAlamatKonsumen(rs.getString("alamat"));
            user.setEmailKonsumen(rs.getString("email"));
            user.setNoTelpKonsumen(rs.getLong("no_telp"));
            return user;
         }
         
   
   }catch(Exception e){
        e.printStackTrace();
   }return null;
}
  public profilKonsumen Profile(int idKonsumen){
    profilKonsumen user = new profilKonsumen();
    try {
        Connection conn = Database.getConnection();
        String sql = "SELECT * FROM konsumen WHERE id_konsumen = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idKonsumen);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            user.setIdUser(rs.getInt("id_konsumen"));
            user.setNamaKonsumen(rs.getString("nama_konsumen"));
            user.setAlamatKonsumen(rs.getString("alamat"));
            user.setEmailKonsumen(rs.getString("email"));
            user.setNoTelpKonsumen(rs.getLong("no_telp"));
            return user;
        }
    } catch(Exception e) {
        e.printStackTrace();
    }
    return null;
}

   public boolean CekUsername(String username){
       boolean cocok = false;
    try{
        Connection conn = Database.getConnection();
        String sql = "SELECT username FROM konsumen Where username = ?";
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
