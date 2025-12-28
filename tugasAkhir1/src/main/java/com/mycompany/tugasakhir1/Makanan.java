/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1;

/**
 *
 * @author hanif
 */
public class Makanan {
      private String namaMakanan,namaKota,namaToko,jenisMakanan,makananMinuman;
    private int idMakanan,idToko,idKota,harga,jarak,jumlahTersedia;
    
   
    
    public Makanan( String namaToko, String namaKota, String namaMakanan, int jumlahTersedia, int harga, int jarak, String jenisMakanan, String makananMinuman){
    
    this.namaToko = namaToko;
    this.namaKota = namaKota;
    this.namaMakanan = namaMakanan;
    this.jumlahTersedia = jumlahTersedia;
    this.harga = harga;
    this.jarak = jarak;
    this.jenisMakanan  = jenisMakanan;
    this.makananMinuman = makananMinuman;
    }
    
   
    
     public Makanan(){
    }
     
     
    public int getJumlahTersedia(){
        return jumlahTersedia;
    }
    public String getNamaKota(){
        return namaKota;
    }
    public String getNamaToko(){
        return namaToko;
    }
    public int getIdMakanan(){
        return idMakanan;
    }
    
    public int getJarak(){
        return jarak;
    }
    public String getNamaMakanan(){
        return namaMakanan;
    }
    public int getHarga(){
        return harga;
    }
    public String getMakananMinuman(){
        return makananMinuman;
    }
   
    public String getJenisMakanan(){
    return jenisMakanan;
    }
   
}
