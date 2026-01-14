/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1.Admin;

/**
 *
 * @author hanif
 */
public class DataAdmin {
    private String namaLengkap,email,alamat,namaToko,AlamatToko;
    private int adminAktif,saldo;
    
    public void setSaldoToko(int saldo){
        this.saldo = saldo;
        System.out.println(this.saldo);
    }
    public int getSaldo(){
    return saldo;
    }
    public void setAdminAktif(int admin){
        this.adminAktif = admin;
    }
    
    public void setNamaLengkap(String nama){
        this.namaLengkap = nama;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setAlamat(String alamat){
        this.alamat = alamat;
    }
    public void setNamaToko(String namaToko){
        this.namaToko = namaToko;
    }
    public void setAlamatToko(String alamat){
        this.AlamatToko = alamat;
    }
    
    public int getAdminAktif(){
        return adminAktif;
    }
    public String getNamaLengkap(){
        return namaLengkap;
    }
    public String getEmail(){
        return email;
    }
    public String getAlamat(){
        return alamat;
    }
    public String getNamaToko(){
        return namaToko;
    }
    public String getAlamatToko(){
        return AlamatToko;
    }
}
