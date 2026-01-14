package com.mycompany.tugasakhir1.Admin;

import com.mycompany.tugasakhir1.DaoKonsumen;
import com.mycompany.tugasakhir1.Database;
import com.mycompany.tugasakhir1.Makanan;
import static java.nio.file.Files.list;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hanif
 */
public class DaoAdmin  {
    private final List <Makanan> list = new ArrayList<>();
    private final List<pesanan> listPesanan = new ArrayList<>();
        List banyak;
        private final DataAdmin data = new DataAdmin();
        
        public List<pesanan>finishOrder(int pesanan){
        try{
    Connection conn = Database.getConnection();
        String sql ="""
                    SELECT 
                        p.id_pesanan,
                        p.total_harga,
                        m.nama_makanan,
                        dp.quantity,
                        dp.harga_satuan
                    
                    FROM pesanan AS p
                    JOIN detail_pesanan AS dp ON p.id_pesanan = dp.id_pesanan
                    JOIN makanan AS m ON dp.id_makanan = m.id_makanan
                    WHERE p.id_pesanan = ?;
                    """;
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, 158);
       ResultSet rs =  ps.executeQuery();
       while (rs.next()){
       listPesanan.add(new pesanan(
        rs.getInt("id_pesanan"),
        rs.getInt("total_harga"),
        rs.getString("nama_makanan"),
        rs.getInt("quantity"),
        rs.getInt("harga_satuan")
       ));
       banyak = listPesanan;
       }
    }catch(Exception e){
    e.printStackTrace();
    }
       return banyak; 
        }
 public void UpdateStatus(int pesanan){
     try{
    Connection conn = Database.getConnection();
        String sql ="""
                    UPDATE pesanan 
                    SET status ='Selesai'
                    WHERE id_pesanan = ?
                    """;
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, pesanan);
        ps.execute();
    }catch(Exception e){
    e.printStackTrace();
    }
 }
 
        public void addDataToDatabase(String namaMakanan,int jumlah,String jenisMakanan,int harga,String experied,String SatuanBerat,
            String path,String makananMinuman,int idKategori,int idAdmin){
    try{
    Connection conn = Database.getConnection();
        String sql = """
                     INSERT INTO makanan(id_toko, nama_makanan, jumlah_tersedia, harga, jenis_makanan, makanan_minuman,
                     id_kategori, satuan_berat_makanan, path_file, experied) SELECT id_toko,?,?,?,?,?,?,?,?,? 
                     FROM toko WHERE id_admin = ?
                    
                     """;
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, namaMakanan);
        ps.setInt(2, jumlah);
        ps.setInt(3, harga);
        ps.setString(4, jenisMakanan);
        ps.setString(5, makananMinuman);
        ps.setInt(6, idKategori);
        ps.setString(7, SatuanBerat);
        ps.setString(8, path);
        ps.setString(9,experied);
      
        ps.setInt(10, idAdmin);
        
       
        ps.execute();
        }catch(Exception e){e.printStackTrace();}
}
 public List<pesanan> ambilPesananKonsumen(int id,String status){
      
        System.out.println("od yang dituju"+id);
        System.out.println("query ke ambil pesanan konsumen");
        listPesanan.clear();
    try{
    Connection conn = Database.getConnection();
    String query ="""
                SELECT 
                      p.id_pesanan,
                      t.nama_toko,
                      p.id_toko,
                      p.hari_pengambilan,
                      p.waktu_pengambilan,
                      t.alamat_toko,
                     
                      GROUP_CONCAT(m.nama_makanan SEPARATOR ', ') AS nama_makanan,
                     dp.quantity,
                    m.harga,
                      pl.nama_konsumen,
                      p.status,
                      p.total_harga,
                      MIN(m.path_file) AS path_file
                  FROM pesanan AS p
                  JOIN toko AS t ON p.id_toko = t.id_toko
                  JOIN konsumen AS pl ON p.id_konsumen = pl.id_konsumen
                  JOIN detail_pesanan AS dp ON p.id_pesanan = dp.id_pesanan 
                  JOIN makanan AS m ON dp.id_makanan = m.id_makanan 
                  WHERE pl.id_konsumen = ? AND status = ?
                  GROUP BY 
                      p.id_pesanan, 
                      t.nama_toko, 
                      t.alamat_toko, 
                      p.id_toko, 
                      p.hari_pengambilan, 
                      p.waktu_pengambilan, 
                      pl.nama_konsumen, 
                      p.status, 
                      p.total_harga,
                      dp.quantity,
                      m.harga
                  ORDER BY p.id_pesanan DESC;
                  """;
    PreparedStatement ps = conn.prepareStatement(query);
    ps.setInt(1,id);
    ps.setString(2, status);
    ResultSet rs = ps.executeQuery();
        while(rs.next()){
            listPesanan.add(new pesanan(
                rs.getInt("id_pesanan"),
                rs.getString("nama_toko"),
                rs.getInt("id_toko"),
                rs.getString("hari_pengambilan"),
                rs.getString("waktu_pengambilan"),
                rs.getString("alamat_toko"),
                rs.getString("nama_makanan"),
                rs.getInt("quantity"),
                rs.getString("nama_konsumen"),
                rs.getString("status"),
                rs.getInt("total_harga"),
                rs.getString("path_file"),
                rs.getInt("harga")
            ));
        }banyak = listPesanan;
        System.out.println("banyaknya yang diambil dari pesanan konsumen "+banyak.size());
    }
    catch(Exception e){
    e.printStackTrace();
    }return banyak;
        
        }
        
        
   public List<Makanan>ambilMakananAdmin(int idAdmin) {
       System.out.println("query ambik makanan admin");
       list.clear();
       try{
           Connection conn = Database.getConnection();
        
        String query = """
                       SELECT m.id_makanan,m.path_file,m.nama_makanan,m.jenis_makanan,m.harga,m.jumlah_tersedia FROM makanan AS m
                       JOIN toko AS t ON m.id_toko = t.id_toko
                       JOIN admin AS a ON a.id_admin = t.id_admin
                       WHERE a.id_admin = ?;
                      
                       """;
       
      
             PreparedStatement ps = conn.prepareStatement(query);
             ps.setInt(1, idAdmin);
             
             ResultSet rs = ps.executeQuery();
           while(rs.next()){
        
                list.add(new Makanan(
                   rs.getString("nama_makanan"),
                   rs.getString("jenis_makanan"),
                   rs.getInt("harga"),
                   rs.getInt("jumlah_tersedia"),
                   rs.getString("path_file"),
                   rs.getInt("id_makanan")
                ));
                banyak = list;
                System.out.println("banyaknya yang diambil"+banyak.size());
         }
       }catch(Exception e){
       e.printStackTrace();
       }    
    
    
   return banyak;
   }
   public int finishedOrder(int idPesanan){
       int updateSaldo =0;
       try{
            Connection conn = Database.getConnection();
            String sql = """
                      UPDATE toko as t 
                        JOIN pesanan AS p ON p.id_toko = t.id_toko
                        SET t.saldo = t.saldo + p.total_harga 
                        WHERE p.status IN ('Selesai') AND p.id_pesanan = ?
                         """;
        PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idPesanan);
            
            updateSaldo = ps.executeUpdate();
            
            }catch(Exception e ){
            e.printStackTrace();
            }
   return updateSaldo;
   }
   public void setStock(int idPesanan){
       try{
            Connection conn = Database.getConnection();
            String sql = """
                        UPDATE makanan AS m
                         JOIN detail_pesanan AS dp ON m.id_makanan = dp.id_makanan
                         SET m.jumlah_tersedia = m.jumlah_tersedia - dp.quantity
                         WHERE dp.id_pesanan = ?;
                         """;
        PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idPesanan);
            
            ps.execute();
            
            }catch(Exception e ){
            e.printStackTrace();
            }
        }
   
        public int accpetOrder(int idPesanan){
            int update = 0;
            try{
            Connection conn = Database.getConnection();
            String sql = """
                         UPDATE pesanan 
                         SET status ='Diterima'
                         WHERE id_pesanan = ?
                         """;
        PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idPesanan);
            update = ps.executeUpdate();
            }catch(Exception e ){
            e.printStackTrace();
            }
            return update;
        }
        public void DeleteFromList(int idMakanan){
        try{
        Connection conn = Database.getConnection();
        String sql = "DELETE FROM makanan WHERE id_makanan = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idMakanan);
        ps.execute();
        
        }catch(Exception e){
        e.printStackTrace();
        }
        }
    public void UpdateList(String namaMakanan,int jumlah,String jenisMakanan,int harga,String experied,String SatuanBerat,
            String path,int idMakanan){
        try{
        Connection conn = Database.getConnection();
        String sql = """
                     UPDATE makanan
                     SET nama_makanan = ?,
                     jumlah_tersedia =?,
                     jenis_makanan =?,
                     harga =?,
                     experied = ?,
                     satuan_berat_makanan =?,
                     path_file =?
                     WHERE id_makanan = ?
                     """;
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, namaMakanan);
        ps.setInt(2, jumlah);
        ps.setString(3, jenisMakanan);
        ps.setInt(4, harga);
        ps.setString(5, experied);
        ps.setString(6, SatuanBerat);
        ps.setString(7, path);
        ps.setInt(8,idMakanan);
        ps.execute();
        }catch(Exception e){e.printStackTrace();}}
    private void result (ResultSet rs){
        try {
            while(rs.next()){
                System.out.println("rs nya jalan");
                listPesanan.add(new pesanan(
                        rs.getInt("id_toko"),
                        rs.getInt("id_pesanan"),
                        rs.getString("hari_pengambilan"),
                        rs.getString("waktu_pengambilan"),
                        rs.getString("nama_toko"),
                        rs.getString("nama_konsumen"),
                        rs.getString("status"),
                        rs.getInt("jumlah_menunggu"),
                        rs.getInt("jumlah_selesai"),
                        rs.getInt("jumlah_diterima"),
                        rs.getString("path_file"),
                        rs.getString("nama_makanan"),
                        rs.getInt("total_harga"),
                        rs.getString("alamat_toko")
                )
                );
            }banyak = listPesanan;
           
            System.out.println("banyaknya = "+banyak);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
}
    /*public List<pesanan> ambilMenu(int idToko){
    listPesanan.clear();
    try{
    Connection Conn = Database.getConnection();
    String query = """
                   
                   """;}}*/
    
    public DataAdmin getSaldo(int id){
        try{
            Connection conn = Database.getConnection();
            String sql = """
                         SELECT saldo FROM toko WHERE id_admin = ?
                         """;
        PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
            data.setSaldoToko(rs.getInt("saldo"));
                    }
            }catch(Exception e ){
            e.printStackTrace();
            }
        return data;
    }
    public List<pesanan> ambilPesanan(int id,String filter){
      
        System.out.println("od yang dituju"+id);
        System.out.println("query ke ambil pesanan");
        listPesanan.clear();
    try{
    Connection conn = Database.getConnection();
    String query ="""
               SELECT 
                    p.id_pesanan,
                    t.nama_toko,
                    t.alamat_toko,
                    GROUP_CONCAT(m.nama_makanan SEPARATOR ', ') AS nama_makanan, 
                    p.id_toko,
                    p.hari_pengambilan,
                    p.waktu_pengambilan,
                    pl.nama_konsumen,
                    p.status,
                    p.total_harga,
                    MIN(m.path_file) AS path_file,
                    SUM(CASE WHEN p.status='Menunggu' THEN 1 ELSE 0 END) OVER() AS jumlah_menunggu,
                    SUM(CASE WHEN p.status='Diterima' THEN 1 ELSE 0 END) OVER() AS jumlah_diterima,
                    SUM(CASE WHEN p.status='Selesai' THEN 1 ELSE 0 END) OVER() AS jumlah_selesai
                FROM pesanan AS p
                JOIN toko AS t ON p.id_toko = t.id_toko
                JOIN konsumen AS pl ON p.id_konsumen = pl.id_konsumen
                JOIN detail_pesanan AS dp ON p.id_pesanan = dp.id_pesanan
              
                JOIN makanan AS m ON dp.id_makanan = m.id_makanan  AND m.id_toko = t.id_toko
                WHERE t.id_admin = ? AND p.status = ?
                GROUP BY p.id_pesanan;
                  """;
    PreparedStatement ps = conn.prepareStatement(query);
    ps.setInt(1,id);
    ps.setString(2, filter);
    ResultSet rs = ps.executeQuery();
        System.out.println("rs "+rs);
    result(rs);
    }
    catch(Exception e){
    e.printStackTrace();
    }
    System.out.println(banyak.size());
        return banyak;
        
    
    }
    public void insertIntoAdmin(String Nama,String username,String Email,String namaToko,String Alamat,String Password){
        try{
            System.out.println("ini jalan");
        Connection conn = Database.getConnection();
        String query = """
                       INSERT INTO admin
                       (username,nama_lengkap,email,alamat,nama_toko,password)
                       VALUES(?,?,?,?,?,?);
                       """;
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, username);
        ps.setString(2, Nama);
        ps.setString(3, Email);
        ps.setString(4, Alamat);
        ps.setString(5, namaToko);
        ps.setString(6, Password);
        ps.execute();
            System.out.println("query berhasi;");
        
        }catch(Exception e ){
        e.printStackTrace();
        }
        
    }
    public boolean cekUsername(String username){
        boolean cocok = false;
    try{
    Connection conn = Database.getConnection();
    String query = "Select username from admin WHERE username = ?";
    PreparedStatement ps = conn.prepareStatement(query);
    ps.setString(1, username);
    ResultSet rs = ps.executeQuery();
    
    cocok = rs.next();
    }catch (Exception e){
        e.printStackTrace();
    }
    return cocok;
    }
    public DataAdmin LoginAdmin(String Email , String password){
        System.out.println(Email+password);
    try{
        System.out.println("query ke admin");
        Connection conn = Database.getConnection();
        String query = """
                       SELECT 
                       t.id_toko,
                       a.id_admin,
                       a.username,
                       a.email,
                       a.password
                       FROM admin as a
                       JOIN toko as t ON a.id_admin = t.id_admin
                       WHERE 
                       (email = ? OR username = ?)AND password = ?
                       """;
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, Email);
        ps.setString(2, Email);
        ps.setString(3, password);
        ResultSet rs = ps.executeQuery();
        if(rs != null){
            System.out.println("data ad,om ketemu");}else{
            System.out.println("data mgga ada ");}
        while(rs.next()){
            System.out.println("admin ketemu");
            DataAdmin admin = new DataAdmin();
            admin.setAdminAktif(rs.getInt("id_admin"));
            
            return admin;
        }
        
    }catch(Exception e){
    e.printStackTrace();
    }return null;
    }
    
}
