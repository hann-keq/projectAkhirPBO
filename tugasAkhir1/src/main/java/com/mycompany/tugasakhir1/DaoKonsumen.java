/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1;

import com.mycompany.tugasakhir1.Location.location;
import com.mycompany.tugasakhir1.Profile.profilKonsumen;
import com.mycompany.tugasakhir1.cartController.Cart;
import com.mycompany.tugasakhir1.cartController.cartModel;
import com.mycompany.tugasakhir1.pembayaran.PembayaranController;
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

public class DaoKonsumen  {
    int jumlah = 0;
    List banyak;
     List profile;
     int pesanan;
    
     private final List<profilKonsumen> listProfile = new ArrayList<>();
     private final List <Makanan> list = new ArrayList<>();
     private final List <location> locationList = new ArrayList<>();
     private final List <Cart> cartList = new ArrayList<>();
    
    
     public String InsertCustomerLocation(String location,int id){
         String feedback = null;
        try{
            Connection conn = Database.getConnection();
            
            String query ="""
                          UPDATE konsumen SET
                          LokasiPemesan=? 
                          WHERE id_konsumen =?
                          """;
            PreparedStatement ps = conn.prepareStatement(query); 
        ps.setString(1, location);
        ps.setInt(2, id);
        int update = ps.executeUpdate();
        
        if(update > 0){
            feedback = "berhasil update lokasi pelanggan";
        }
        }catch(Exception e){
        e.printStackTrace();}
     
        return feedback;
     }
     public List<Makanan> getIdPesanan(int idKonsumen){
         try{
            Connection conn = Database.getConnection();
            
            String query ="""
                          SELECT 
                          id_pesanan
                          FROM pesanan
                          WHERE id_konsumen = ?
                          ORDER BY id_pesanan DESC
                          LIMIT 1;
                          """;
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idKonsumen);
            ResultSet  rs = ps.executeQuery();
            while(rs.next()){
                list.add(new Makanan(
                rs.getInt("id_pesanan")
                ));
            }
     }catch(Exception e){
     e.printStackTrace();
     }return list;
     }
     public int AddtoOrder(int idToko,int idKonsumen,String hari,String pengambilan,int total){
      
         System.out.println(idToko+idKonsumen+hari+pengambilan+total);
         try{
            Connection conn = Database.getConnection();
            conn.setAutoCommit(false);
            
            
            String query = """
                          INSERT INTO pesanan 
                           (id_toko, id_konsumen, hari_pengambilan, waktu_pengambilan, status, total_harga)
                          VALUES (?, ?, ?, ?, 'Menunggu', ?);
                          """;
             
            PreparedStatement ps = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idToko);
            ps.setInt(2, idKonsumen);
            ps.setString(3, hari);
            ps.setString(4, pengambilan);
            ps.setInt(5, total);
          
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
           
            if(rs.next()){
            pesanan = rs.getInt(1);
            }            
             System.out.println("pesanan id"+pesanan);
            String sql = """
                     INSERT INTO detail_pesanan
                     (id_makanan, id_pesanan, quantity, harga_satuan)
                     SELECT
                     k.id_makanan,
                     ?,
                    SUM(k.quantity),
                    m.harga
                    FROM keranjang AS k
                    JOIN makanan AS m ON k.id_makanan = m.id_makanan
                     
                    WHERE k.id_konsumen = ?
                         GROUP BY k.id_makanan, m.harga;
                     """;
        PreparedStatement ps1 = conn.prepareStatement(sql);
        ps1.setInt(1, pesanan );
        ps1.setInt(2, idKonsumen);
        ps1.executeUpdate();
        
        String sql1 = "DELETE FROM keranjang WHERE id_konsumen = ?;";
    PreparedStatement ps2 = conn.prepareStatement(sql1);
    ps2.setInt(1, idKonsumen);
    ps2.execute();
   
    conn.commit();
   
    conn.commit();
         }catch(Exception e){
            e.printStackTrace();
         }
         return pesanan;
     }
     
    
    
     public void Updateprofile(String nama,String alamat,String email,long no,String path){
     try{
     Connection conn = Database.getConnection();
     String query = """
                    UPDATE 
                    konsumen SET
                    nama_konsumen = ?,
                    alamat = ?,
                    email = ?,
                    no_telp = ?,
                    path_file = ?
                    WHERE id_konsumen = ?
                    """;
     PreparedStatement ps = conn.prepareStatement(query);
     ps.setString(1, nama);
     ps.setString(2, alamat);
     ps.setString(3, email);
     ps.setLong(4, no);
     ps.setString(5, path);
     ps.setInt(6, sesiAktif.getInstance().getIdKonsumen());
     ps.execute();
     }catch(Exception e){
     e.printStackTrace();
     }}
     public List<location>locationCompleter(){
     try{
     Connection conn = Database.getConnection();
     String query = "SELECT * FROM lokasi;";
     Statement st = conn.createStatement();
     ResultSet rs = st.executeQuery(query);
     while(rs.next()){
         locationList.add(new location(
         rs.getString("kecamatan"),
         rs.getString("kelurahan/desa")
         ));
         
     }
     }catch(Exception e){
     e.printStackTrace();
     } 
     return locationList;
     }
     
     
     public List<Cart> cart(int id){
         try{ Connection conn = Database.getConnection();
         String query="""
                      SELECT 
                      c.id_keranjang,
                      m.nama_makanan,
                      m.id_makanan,
                      c.waktu,
                      c.quantity,
                      t.jam_makanan_tersedia,
                      t.jam_makanan_experied,
                      t.alamat_toko,
                      m.harga,
                      m.path_file,
                      c.id_toko
                      FROM keranjang as c
                      JOIN makanan AS m ON c.id_makanan = m.id_makanan
                      JOIN toko AS t ON c.id_toko = t.id_toko
                      JOIN konsumen AS k ON c.id_konsumen = k.id_konsumen
                      WHERE c.id_konsumen = ?
                      """;
         PreparedStatement st = conn.prepareStatement(query);
         st.setInt(1, id);
         ResultSet rs = st.executeQuery();
         while(rs.next()){
             java.sql.Timestamp timeAdd = rs.getTimestamp("waktu");
            cartList.add(new Cart(
                rs.getString("nama_makanan"),
                rs.getString("alamat_toko"),
                timeAdd,
                rs.getTimestamp("jam_makanan_tersedia"),
                rs.getTimestamp("jam_makanan_experied"),
                rs.getInt("harga"),
                rs.getInt("id_makanan"),
                rs.getInt("id_toko"),
                rs.getString("path_file")
            ));}
         }catch(Exception e){
         e.printStackTrace();
         }
         return cartList;
     }
     
     public void addToCart(int idUser,int idMakanan,int idToko){
     try{
     Connection conn = Database.getConnection();
     String query ="INSERT INTO keranjang (id_konsumen,id_makanan,id_toko,quantity) VALUES (?,?,?,?);";
     PreparedStatement ps = conn.prepareStatement(query);
     ps.setString(1, String.valueOf(idUser));
     ps.setInt(2, idMakanan);
     ps.setInt(3,idToko);
     ps.setInt(4, 1);
     ps.execute();
     
     }catch(Exception e){
        e.printStackTrace();}
     }
    private void result (ResultSet rs) throws SQLException{
    while(rs.next()){
        
                list.add(new Makanan(
                    rs.getInt("id_makanan"),
                    rs.getString("nama_toko"),
                 
                    rs.getString("nama_makanan"),
                    rs.getInt("jumlah_tersedia"),
                    rs.getInt("harga"),
                   
                    rs.getString("jenis_makanan"),
                    rs.getString("makanan_minuman"),
                    rs.getInt("id_toko"),
                    rs.getString("path_file")
                ));
                banyak = list;
                
    }
   
    }
    public List<Makanan>ambilMakanan(){
        list.clear();
       
        
        String query = """
                       SELECT 
                         m.id_makanan,
                         t.nama_toko,
                        t.id_toko,
                         m.nama_makanan,
                         m.jumlah_tersedia,
                         m.harga,
                         m.path_file,
                         m.jenis_makanan,
                         m.makanan_minuman
                       FROM makanan AS m
                       JOIN toko AS t ON m.id_toko = t.id_toko
                      
                       """;
         try(
        Connection conn = Database.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query);)
         {
            result(rs);
            
             
            
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
                        t.id_toko,
                        m.path_file,
                         m.nama_makanan,
                         m.jumlah_tersedia,
                         m.harga,
                        m.jenis_makanan,
                         m.makanan_minuman,
                         ka.kategori
                       FROM makanan AS m
                       JOIN toko AS t ON m.id_toko = t.id_toko
                       
                       JOIN kategori AS ka ON m.id_kategori = ka.id_kategori
                       WHERE ka.kategori = ?;
                     """;
     PreparedStatement ps = conn.prepareStatement(query);
     ps.setString(1, filter);
     ResultSet rs = ps.executeQuery();
     result(rs);
     
     
     
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
    
     
    }catch(Exception e){
        e.printStackTrace();
    }
  }
   public profilKonsumen Login(String email,String pass){ 
    try{
        
         Connection conn = Database.getConnection();
         String sql = """
                      SELECT 
                          id_konsumen,
                          username,
                          passwd,
                          email,
                          path_file
                          FROM konsumen
                      WHERE (email = ? OR username = ?) 
                      AND passwd = ?;
                      """ ;
         PreparedStatement ps = conn.prepareStatement(sql);
         ps.setString(1, email);
         ps.setString(2, email);
         ps.setString(3, pass);
         ResultSet rs = ps.executeQuery();
         while (rs.next()){
           profilKonsumen user = new profilKonsumen();
            user.setIdUser(rs.getInt("id_konsumen"));
            user.setPathProfile(rs.getString("path_file"));
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
            user.setNama(rs.getString("nama_konsumen"));
            user.setAlamat(rs.getString("alamat"));
            user.setEmail(rs.getString("email"));
            user.setNoTelp(rs.getLong("no_telp"));
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
 public boolean cekKepunyaanToko(int idKonsumen){
        boolean ketemu = false;
        try{
        Connection conn = Database.getConnection();
        String sql = """
                     SELECT 
                     id_admin FROM admin 
                     WHERE id_konsumen = ?
                     """;
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idKonsumen);
        ResultSet rs = ps.executeQuery();
        ketemu = rs.next();
        }catch(Exception e ){
            e.printStackTrace();
        }return ketemu;
    }


    
   
  
}
