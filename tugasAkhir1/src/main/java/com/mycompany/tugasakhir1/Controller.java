/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1;
import com.mycompany.tugasakhir1.signUp;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author hanif
 */
public class Controller {
  
 @FXML
 private Rectangle coba2,coba;
 @FXML 
 private Ellipse cobaLingkaranBlur,cobaLingkaranBlur1,LingkaranBlurHome,LingkaranBlurHome1,
         LingkaranBlurHomeAtas;
 
 public Ellipse getEllipse(){
 return cobaLingkaranBlur;
 }
 
 public Ellipse getEllipse2(){
 return cobaLingkaranBlur1;
 }
@FXML
private Label test,cek;

   @FXML
    private TextField txtNama,txtEmail,txtPassword,txtUsername,txtAlamat,txtConfirmPassword;
    
   
   //pindah ke welcome screen
     @FXML
    private void showWelcomeScreen() throws IOException{
       App.setRoot("welcomeScreen");
       animateControll();
     

    }
     // mengatur animasi
    public void animateControll(){
    Animasi.AnimasiTranslate(coba, 0, 500,2000, true);
    Animasi.AnimasiRotate(coba, 90, 1000,1);
    Animasi.AnimasiTranslate(coba2, 500, 10, 3000, true, 2);
    Animasi.AnimasiRotate(coba2, 90, 100,1);
    Animasi.AnimasiTranslate(LingkaranBlurHome, 0, 100, 5000, true);
    Animasi.AnimasiTranslate(LingkaranBlurHome1, 0, 100, 3000, true);
    Animasi.AnimasiTranslate(LingkaranBlurHomeAtas, 0, -300, 3000, true);
    
    }
    
    //pindah ke signUp
    @FXML
    private void showSignUp()throws IOException{
      Controller c = (Controller) App.setRoot("signUp");
       BoxBlur blur = new BoxBlur();
    blur.setHeight(200);
    blur.setWidth(200);
    c.LingkaranBlurHomeAtas.setEffect(blur);
    c.LingkaranBlurHome.setEffect(blur);
      
      
    }
    
    //pindah ke profile
    @FXML
    private void showProfile() throws IOException{
    App.setRoot("profile");
    }
    @FXML
    private void showLogin() throws IOException{
     Controller c = (Controller) App.setRoot("login");

    BoxBlur blur = new BoxBlur();
    blur.setHeight(200);
    blur.setWidth(200);
    c.LingkaranBlurHomeAtas.setEffect(blur);
    c.LingkaranBlurHome.setEffect(blur);
    }
    
    //pindah ke homescreen
   @FXML
   private void showHome() throws IOException{
   
   Controller c = (Controller) App.setRoot("Home");
   BoxBlur blur = new BoxBlur();
   blur.setHeight(200);
    blur.setWidth(200);
    c.LingkaranBlurHome1.setEffect(blur);
    c.LingkaranBlurHome.setEffect(blur);
  
   }
   //pindah ke lokasi
   private void showLocation() throws IOException{
   blurrGauss.blurr("location", 200, 200);}
   
   //pengecekan username
    private boolean cekUsername(){
        
        String username = txtUsername.getText();
    signUp su = new signUp();
   boolean duplicate = su.CekUsername(username);
   cek.setVisible(duplicate);
   if(username.isEmpty()){
   cek.setVisible(false);}
   return duplicate;
    }
    //ikut diatas
    @FXML
    private void onKeyReleasedcekUsername(KeyEvent event){
    cekUsername();
    }
    
    //pengecekan password
    private boolean cekPassword(){
    String password = txtPassword.getText();
    String cekPassword = txtConfirmPassword.getText();
    boolean benar = cekPassword.equals(password);
    test.setVisible(!benar);
    return benar;
    }
   @FXML
   private void onKeyReleasedcekPassword(KeyEvent event){
    cekPassword();
   }
   
  @FXML
  private void Daftar() throws IOException, SQLException{
    String nama = txtNama.getText();
    String email = txtEmail.getText();
    String password = txtPassword.getText();
    String alamat = txtAlamat.getText();
    String username = txtUsername.getText();
    String cekPassword = txtConfirmPassword.getText();
    
    if(nama.isEmpty() || email.isEmpty() || password.isEmpty() || alamat.isEmpty()){
       AlertAlert.AlertTemplate(AlertType.WARNING, "kolom todak boleh kosong");
       }
   if(cekPassword()){
        signUp su = new signUp();
        su.InputToDatabase(username, password, nama, email, alamat);
        
        System.out.println("Berhasil");}
    else{
       
         AlertAlert.AlertTemplate(AlertType.WARNING,"Password tidak sama");
        
    }
  }
  
  @FXML
  private void Login(){
    String email = txtEmail.getText();
    String password = txtPassword.getText();
    signUp cari = new signUp();
    
  if(txtEmail.getText().isEmpty() || txtPassword.getText().isEmpty()){
        AlertAlert.AlertTemplate(AlertType.WARNING,"Kolom tidak boleh kosong", ButtonType.OK, 5000);
  }else{
    if( cari.cariAkun(email, password)){
        System.out.println("Login berhasil");
        AlertAlert.AlertTemplate(AlertType.INFORMATION,"Login berhasil");
        try {
            blurrGauss.blurr("location", 200, 200);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }else
        AlertAlert.AlertTemplate(AlertType.WARNING, "email atau password salah", ButtonType.OK, 1000);
        System.out.println("login gagal");
  }
  }
  
  private void location(){
    }
   
}
