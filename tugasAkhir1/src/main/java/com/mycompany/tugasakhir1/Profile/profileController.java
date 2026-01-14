package com.mycompany.tugasakhir1.Profile;

import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.controls.JFXToggleNode;
import com.mycompany.tugasakhir1.DaoKonsumen;
import com.mycompany.tugasakhir1.Controller;
import com.mycompany.tugasakhir1.SceneChanger;
import com.mycompany.tugasakhir1.imageControll;
import com.mycompany.tugasakhir1.sesiAktif;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hanif
 */
public class profileController implements Initializable {
    private DaoKonsumen crud;
    private int idKonsumen;
    @FXML private Button btnAdmin,btnKeluar,btnEdit;
    @FXML private Circle profile,profile1;
    @FXML private JFXToggleNode btnjelajahi,btnPesanan;
   @FXML private Label lblNama,lblEmail,lblEmail1,lblTelepon,lblAlamat;
   
 
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    btnAdmin.setOnAction(this::showLoginAdmin);
    btnKeluar.setOnAction(this::showLogin);
    btnEdit.setOnAction(eh ->{
        SceneChanger.StageChanger("/Edit Profil.fxml","Edit profil");});
btnPesanan.setOnAction(eh ->{
   SceneChanger.pindahScene("/com/mycompany/tugasakhir1/PesananKonsumen/Halaman Pesanan Saya.fxml", eh);});
btnjelajahi.setOnAction(eh ->{
        SceneChanger.pindahScene("/com/mycompany/tugasakhir1/Home.fxml", eh);});
    
    loadData();
    
      try{
    FileInputStream input = new FileInputStream(sesiAktif.getInstance().getPathFile());
    Image originial = new Image(input);
    
    imageControll square = new imageControll();
    Image squareImg = square.getSquareImage(originial);
    
    profile.setFill(new ImagePattern(squareImg));
    profile1.setFill(new ImagePattern(squareImg));
    }catch(Exception e){
    e.printStackTrace();
    }
        
    }
    
    
    private void loadData(){
    profil();
    
    }
    
    
    ///////////////////////

    @FXML
    private void showLoginAdmin(ActionEvent event) {
        try{
        FXMLLoader loader = new FXMLLoader (
            getClass().getResource("/fxml/LoginAdmin.fxml"));
            Parent root = loader.load();
            Scene scene = ((Node) event.getSource()).getScene();
            scene.setRoot(root);
        }catch(Exception e){
            e.printStackTrace();
        }   
    }
    private void profil(){
         crud = new DaoKonsumen();
        int id = sesiAktif.getInstance().getIdKonsumen();
         profilKonsumen user = crud.Profile(id);
        if(user != null){
            lblNama.setText(user.getNama());
            lblEmail.setText(user.getEmail());
            lblTelepon.setText(String.valueOf(user.getNoTelp()));
            lblAlamat.setText(user.getAlamat());
              lblEmail1.setText(user.getEmail());
            
    }
    }
        
     @FXML
    private void showLogin(ActionEvent event) {
       try{
           FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/com/mycompany/tugasAkhir1/login.fxml"));
        
        Parent root = loader.load();
        Scene scene = ((Node) event.getSource()).getScene();
        
        scene.setRoot(root);
       }catch(Exception e){
       e.printStackTrace();
       }
}

    
}
