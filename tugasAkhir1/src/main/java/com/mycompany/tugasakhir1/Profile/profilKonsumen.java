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
    private  String namaKonsumen,email,alamat,path;
    private long noTelp;
    private int id,output;
    
    public void setOutput(int output){
    this.output = output;
    }
    public int getOutput(){
        return output;
    }
   public void setNama(String namaKonsumen){
    this.namaKonsumen = namaKonsumen;
   }
   public void setEmail (String email){
    this.email = email;
   }
   public void setAlamat(String alamat){
    this.alamat = alamat;
   }
   public void setPathProfile(String path){
   this.path = path;
   }
   public String getPathProfile(){
   return path;
   }
   public void setIdUser(int id){
    this.id = id;
   }
   public void setNoTelp(Long noTelp){
    this.noTelp = noTelp;
            System.out.println("no telp yang dikirim"+noTelp);
            System.out.println(this.noTelp);}
   
    
    public String getNama(){
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
