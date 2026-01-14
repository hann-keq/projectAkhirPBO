/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1.cartController;

import java.sql.Timestamp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author hanif
 */
public class Cart {

    private String namaMakanan,alamatToko,makananCheck,path;
    private Timestamp waktu,makananTersedia,makananExperied;
    
    private int harga,jumlah,idMakanan,jumlahCheck,idMakananCheck,hargaCheck,total,idToko,idKeranjang;
    public Cart(String namaMakanan,String alamatToko,Timestamp waktu,Timestamp makananTersedia,
            Timestamp makananExperied,int harga,int idMakanan,int idToko,String path){
        this.namaMakanan = namaMakanan;
        this.alamatToko = alamatToko;
        this.makananExperied = makananExperied;
        this.waktu = waktu;
        this.makananTersedia = makananTersedia;
        this.harga = harga;
        this.idMakanan = idMakanan;
        this.idToko  = idToko;
        this.idKeranjang = idKeranjang;
        this.path = path;
    
    }
    public Cart(){};
    public Cart(String nama,int harga,int jumlah,int id){
        this.idMakananCheck = id;
       this.makananCheck = nama;
        this.harga = harga;
        this.jumlah= jumlah;
       
    }
    public String getPath(){
    return path;
    }
    public int getIdKeranjang(){
        return idKeranjang;
    }
    public int getIdToko(){
    return idToko;
    }
    public int getTotal(){
     return total;}
    public int getIdMakananCheck(){
    return idMakananCheck;
    }
    public int getHargaCheck(){
    return hargaCheck;
    }
    public int getJumlahCheck(){
        return jumlahCheck ;
    }
    
    public void setHarga(int harga){
        this.harga = harga;
    }
    public void setJumlah(int jumlah){
    this.jumlah = jumlah;}
    
    public int getIdMakanan(){
        return idMakanan;}
    public int getJumlah(){
        return jumlah;
    }
    public String getMakananCheck(){
        System.out.println("maknaanCheck"+makananCheck);
        return makananCheck == null ? "":makananCheck;
    }
    public String getNamaMakanan(){
        return namaMakanan;
    }
    public String getAlamatToko(){
        return alamatToko;
    }
    public Timestamp getWaktu(){
        return waktu;
    }
    public Timestamp getWaktuMakananTersedia(){
        return makananTersedia;
    }
    public Timestamp getWaktuMakananExperied(){
        return makananExperied;
    }
    public int getHarga(){
        return harga;
    }
}
