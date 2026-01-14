/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1;

import com.mycompany.tugasakhir1.Profile.profilKonsumen;
import com.mycompany.tugasakhir1.cartController.Cart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author hanif
 */
public class sesiAktif {
    private int idKonsumen;
    private int idAdmin;
    private String path;
    private int idToko;
   
    
    private sesiAktif(){
    }
    
    private static class HelperClass{
        private static final sesiAktif INSTANCE = new sesiAktif();
    }
    
    public static sesiAktif getInstance(){
        return HelperClass.INSTANCE;
    }
   public void setPathFile(String path){
        this.path = path;
    }
   public String getPathFile(){
        return path;
   }
    public void setIdToko(int id){
        this.idToko = id;
    }
    public int getIdToko(){
        return idToko;
    }
  
    public void setIdAdmin(int id){
        this.idAdmin = id;
    }
    public int getIdAdmin(){
                            ;
        return idAdmin;
    }
    public void setIdKonsumen(int id ){
        this.idKonsumen = id;
        
    }
    public int getIdKonsumen(){
        return idKonsumen;
    }
}
