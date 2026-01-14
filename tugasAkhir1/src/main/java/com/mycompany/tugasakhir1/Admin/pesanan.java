/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1.Admin;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.mycompany.tugasakhir1.sesiAktif;

/**
 *
 * @author hanif
 */
public class pesanan extends RecursiveTreeObject<pesanan>{
    private String pathFile,hariPengambilan,lokasi,waktuPengambilan,namaToko,namaMakanan,alamatToko,namaKonsumen,status,jenisMakanan;
    private int id_pesanan,id_toko,id_makanan,jumlahPembelian,harga,menunggu,selesai,quantity,total,diterima,hargaSatuan;
    
    public pesanan(int id_toko,int id_pesanan,
            String hariPengambilan,String waktuPengambilan,String namaToko,
            String namaKonsumen,String status,int menunggu,int selesai,int diterima,String pathFile,String makanan,int total,String alamatToko){
        this.hariPengambilan = hariPengambilan;
        this.namaMakanan = makanan;
        this.id_pesanan = id_pesanan;
        this.id_toko = id_toko;
        this.total = total;
        this.diterima = diterima;
        this.waktuPengambilan = waktuPengambilan;
        this.namaToko = namaToko;
       
       this.pathFile = pathFile;
        this.namaKonsumen = namaKonsumen;
        this.status = status;
        this.menunggu = menunggu;
        this.selesai = selesai;
        this.alamatToko = alamatToko;
    }
        public pesanan(int id_toko,int id_pesanan,
            String hariPengambilan,String waktuPengambilan,String namaToko,
            String namaKonsumen,String status,String pathFile){
        this.hariPengambilan = hariPengambilan;
      
        this.id_pesanan = id_pesanan;
        this.id_toko = id_toko;
       
     
        this.waktuPengambilan = waktuPengambilan;
        this.namaToko = namaToko;
        
        this.namaKonsumen = namaKonsumen;
        this.status = status;
        this.pathFile = pathFile;
        setPathFile(pathFile);
            System.out.println("idToko "+id_toko+"\n"+"nama toko: "+namaToko);
        }
        
        public pesanan(int idPesanan,String namaToko,int id_toko,String hariPengambilan,
                String waktuPengambilan,String alamatToko,String nama_makanan,int quantity,
                String namaKonsumen,String status,int hargaTotal,String path,int harga){
        this.id_pesanan = idPesanan;
        this.namaToko = namaToko;
        this.id_toko = id_toko;
        this.hariPengambilan = hariPengambilan;
        this.waktuPengambilan = waktuPengambilan;
        this.alamatToko = alamatToko;
        this.namaMakanan = nama_makanan;
        this.quantity = quantity;
        this.namaKonsumen = namaKonsumen;
        this.status = status;
        this.total = hargaTotal;
        this.pathFile = path;
        this.harga = harga;
        }
        
        public pesanan(int idPesanan,int total,String namaMakanan,int quantity,int hargaSatuan){
        this.id_pesanan = idPesanan;
        this.total = total;
        this.namaMakanan = namaMakanan;
        this.quantity = quantity;
        this.hargaSatuan = hargaSatuan;
        }
    public int getHargaSatuan(){
    return hargaSatuan;
    }
        public int getDiterima(){
        return diterima;
        }
        private void setPathFile(String path){
        sesiAktif.getInstance().setPathFile(path);
        }
    public String getPathFile(){
        return pathFile;
    }
    public String getAlamatToko(){
    return alamatToko;
    }
   public int getTotal(){
   return total;
   }
    public int getQuantity(){
    return quantity;}
    public void setJenisMakanan(String jenisMakanan){
    this.jenisMakanan = jenisMakanan;
    }
    
    public int getHarga(){
    return harga;
    }
    
    public int getIdToko(){
    return id_toko;
    }
    public int getIdPesanan(){
    return id_pesanan;
    }
    public int getIdMakanan(){
    return id_makanan;
    }
    public int getJumlahPembelian(){
    return jumlahPembelian;
    }
    
    public int getMenunggu(){
    return menunggu;
    }
    public int getSelesai(){
    return selesai;
    }
    public String getJenis(){
    return jenisMakanan;
    }
    public String getStatus(){
    return status;
    }
    public String getNamaKonsumen(){
    return namaKonsumen;
    }
    public String getNamaMakanan(){
        System.out.println("nama makanan"+namaMakanan);
        return namaMakanan;
   
    }
    public String getHariPengambilan(){
    return hariPengambilan;
    }
    public String getLokasi(){
    return lokasi;
    }
    public String getWaktuPengambilan(){
    return waktuPengambilan;
    }
    public String getNamaToko(){
    return namaToko;
    }
}
