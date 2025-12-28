/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1.Profile;

/**
 *
 * @author hanif
 */
public class profilKonsumen {
    private  String namaKonsumen,email,alamat;
    private long noTelp;
    private int id;
    
   public void setNamaKonsumen(String namaKonsumen){
    this.namaKonsumen = namaKonsumen;
   }
   public void setEmailKonsumen (String email){
    this.email = email;
   }
   public void setAlamatKonsumen(String alamat){
    this.alamat = alamat;
   }
   
   public void setIdUser(int id){
    this.id = id;
   }
   public void setNoTelpKonsumen(Long noTelp){
    this.noTelp = noTelp;
            System.out.println("no telp yang dikirim"+noTelp);
            System.out.println(this.noTelp);}
   
    
    public String getNamaKonsumen(){
        return namaKonsumen;
    }
    public String getEmail(){
        return email;
    }
    public String getAlamat(){
        return alamat;
    }
    public Long getNoTelp(){
        System.out.println(noTelp);
    return noTelp;
    }
    public int getIdUser(){
    return id;
    }
   
}
