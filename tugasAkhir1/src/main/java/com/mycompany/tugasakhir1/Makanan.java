/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.mycompany.tugasakhir1.Admin.pesanan;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author hanif
 */
public class Makanan extends RecursiveTreeObject<Makanan>{
   
   private StringProperty namaMakanan = new SimpleStringProperty();
   private StringProperty namaKota = new SimpleStringProperty();
   private StringProperty namaToko = new SimpleStringProperty();
   private StringProperty jenisMakanan = new SimpleStringProperty();
   private StringProperty makananMinuman = new SimpleStringProperty();
   private StringProperty pathFile = new SimpleStringProperty();
   
    
    private IntegerProperty idMakanan = new SimpleIntegerProperty();
    private IntegerProperty idToko = new SimpleIntegerProperty();
    private IntegerProperty harga = new SimpleIntegerProperty();
    private IntegerProperty jarak = new SimpleIntegerProperty();
    private IntegerProperty jumlahTersedia = new SimpleIntegerProperty();
    private IntegerProperty stok = new SimpleIntegerProperty();
    private IntegerProperty idPesanan = new SimpleIntegerProperty();
   
    
    public Makanan( int idMakanan, String namaToko,  String namaMakanan, int jumlahTersedia, int harga,String jenisMakanan, String makananMinuman,int idToko,String path){
    this.idMakanan.set(idMakanan);
    this.namaToko.set(namaToko);

    this.namaMakanan.set(namaMakanan);
    this.jumlahTersedia.set(jumlahTersedia);
    this.harga.set(harga);
    this.pathFile.set(path);
    this.jenisMakanan.set(jenisMakanan);
    this.makananMinuman.set(makananMinuman);
    this.idToko.set(idToko);
        System.out.println("id makanan"+idMakanan);
        System.out.println("id_makanan this "+this.idMakanan);
    }
    
   public Makanan(String namaMakanan,String jenisMakanan,int harga,int stok,String pathFile,int idMakanan){
       this.idMakanan.set(idMakanan);
       this.pathFile.set(pathFile);
       this.namaMakanan.set(namaMakanan);
       this.jenisMakanan.set(jenisMakanan);
       this.harga.set(harga);
       this.stok.set(stok);
   
   }
   public Makanan(int idPesanan){
        this.idPesanan.set(idPesanan);
   }
    
     public Makanan(){
    }
     public int getIdPesanan(){
     return idPesanan.get();
     }
     public String getPath(){
     return pathFile.get();
     }
     public int getStok(){
     return stok.get();
     }
     public void setSesiAktifIdMakanan(int id){
        this.idMakanan.set(id);
     }
     public int getIdMakanan(){
     return idMakanan.get();
     }
     public int getIdToko(){
        return idToko.get();
     }
    public int getJumlahTersedia(){
        return jumlahTersedia.get();
    }
    public String getNamaKota(){
        return namaKota.get();
    }
    public String getNamaToko(){
        return namaToko.get();
    }
    
    
    public int getJarak(){
        return jarak.get();
    }
    public String getNamaMakanan(){
        return namaMakanan.get();
    }
    public int getHarga(){
        return harga.get();
    }
    public String getMakananMinuman(){
        return makananMinuman.get();
    }
   
    public String getJenisMakanan(){
    return jenisMakanan.get();
    }
   
}
