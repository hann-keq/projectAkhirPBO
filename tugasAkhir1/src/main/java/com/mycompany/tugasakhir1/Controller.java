/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author hanif
 */
public class Controller {
  
 @FXML
 private Rectangle coba2;
   
   @FXML
    private TextField txtUsername,txtEmail,txtPassword,txtNama,txtKonfirmasiPassword,
   txtAlamat,txtPasswordLogin,txtUsernameLogin;
    
     @FXML
    private void showWelcomeScreen() throws IOException{
       App.setRoot("welcomeScreen");
       Animasi.AnimasiTranslate(coba, 500, 10, 3000, true, 2);
       System.out.println(coba.getTranslateX() + " : " + coba.getTranslateY());
      Animasi.startAnimate();

    }
    public void animateControll(){
    Animasi.AnimasiTranslate(coba, 500, 10, 3000, true, 2);
    Animasi.AnimasiRotate(coba, 90, 1000,1);
    Animasi.AnimasiTranslate(coba2, 500, 10, 3000, true, 2);
    Animasi.AnimasiRotate(coba2, 90, 1000,1);
    }
    @FXML
    private void showSignUp()throws IOException{
      App.setRoot("signUp");
      
      
    }
    
    @FXML
    private void showLogin() throws IOException{
      App.setRoot("login");
   
      
      Animasi.startAnimate();
    }
   
    @FXML
    private Rectangle coba;
    
   
  
    @FXML
    private void Daftar()throws IOException, SQLException{
        String username = txtUsername.getText();
        String email = txtEmail.getText();
        String pass = txtPassword.getText();
        String alamat = txtAlamat.getText();
        String nama = txtNama.getText();
        String konfPass = txtKonfirmasiPassword.getText();
         if(txtUsername.getText().isEmpty() || txtEmail.getText().isEmpty() || txtPassword.getText().isEmpty() ||
                 (!txtPassword.getText().equals(konfPass))){
            Alert a = new Alert(AlertType.WARNING,"Form Tidk boleh kosong",ButtonType.OK);
            a.show();
        }else{
        
        signUp handler = new signUp();
        handler.processSignUp(username,email,pass,alamat,nama);
        handler.InputToDatabase(username,email,pass,alamat,nama);
        System.out.println("User memasukan : "+ username+" "+email+" "+pass);
        App.setRoot("welcomeScreen");
        
       
        }
    }
    @FXML
    private void Login()throws IOException, SQLException, ClassNotFoundException{
         
        String username = txtUsernameLogin.getText();
        String pass = txtPasswordLogin.getText();
        signUp cari = new signUp();
        if(txtUsernameLogin.getText().isEmpty() || txtPasswordLogin.getText().isEmpty()){
            AlertAlert.AlertTemplate("konfirmasi");
        }else{
            if(cari.SelectFromDatabase(username, pass)){
                System.out.println("login berhasil");
                Alert b = new Alert(AlertType.INFORMATION,"Login berhasil");
                    b.show();
                App.setRoot("WelcomeScreen");
            }else{
                System.out.println("login gagal");
        }
        }
    }
}
