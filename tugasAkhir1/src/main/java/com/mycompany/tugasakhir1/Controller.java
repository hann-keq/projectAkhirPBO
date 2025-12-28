/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1;
import com.mycompany.tugasakhir1.CRUD;
import com.mycompany.tugasakhir1.Profile.profilKonsumen;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author hanif
 */
public class Controller implements Initializable  {
    
    @Override
   public void initialize (URL url ,ResourceBundle rb){
   animateControll();
   blurrGauss.blurr(200, 200, cobaLingkaranBlur);
}
 
 @FXML 
 private Ellipse cobaLingkaranBlur,cobaLingkaranBlur1,LingkaranBlurHome,LingkaranBlurHome1,
         LingkaranBlurHomeAtas;

@FXML
private Label test,cek;

@FXML
private Button btnLocation;

   @FXML
    private TextField txtNama,txtEmail,txtPassword,txtUsername,txtAlamat,txtConfirmPassword;
    
       @FXML
    private FlowPane container;
     

   
     // mengatur animasi
    public void animateControll(){
 
    Animasi.AnimasiTranslate(LingkaranBlurHome, 0, 100, 5000, true);
    Animasi.AnimasiTranslate(LingkaranBlurHome1, 0, 100, 3000, true);
    Animasi.AnimasiTranslate(LingkaranBlurHomeAtas, 0, -300, 3000, true);
    Animasi.AnimasiTranslate(cobaLingkaranBlur1, 0,200, 3000, true);
    Animasi.AnimasiTranslate(cobaLingkaranBlur, 0, -200, 3000, true);
    
    
    
    
    }
    
    //pindah ke signUp
   @FXML 
    private void showSignUp(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/com/mycompany/tugasAkhir1/signUp.fxml"));
        
        Parent root = loader.load();
        Scene scene = ((Node) event.getSource()).getScene();
        
        scene.setRoot(root);
    }
    
    //pindah ke profile
    
    @FXML
    private void showLogin(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/com/mycompany/tugasAkhir1/login.fxml"));
        
        Parent root = loader.load();
        Scene scene = ((Node) event.getSource()).getScene();
        
        scene.setRoot(root);
        
}
    
   //pindah ke homescreen
   
    
      @FXML
   private void showHome(ActionEvent event) throws IOException{
   FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/com/mycompany/tugasAkhir1/Home.fxml"));
   
        Parent root = loader.load();
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
  
   BoxBlur blur = new BoxBlur();
   blur.setHeight(200);
    blur.setWidth(200);
     }
   @FXML
   private void showLocation(ActionEvent event) throws IOException{
       FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/com/mycompany/tugasAkhir1/location.fxml"));
       
       Parent root = loader.load();
       Scene scene = ((Node) event.getSource()).getScene();
       
       scene.setRoot(root);
    
   }
   
   //pengecekan username
    private boolean cekUsername(){
        
        String username = txtUsername.getText();
    CRUD su = new CRUD();
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
        CRUD su = new CRUD();
        su.InputToDatabase(username, password, nama, email, alamat);
        
        System.out.println("Berhasil");}
    else{
       
         AlertAlert.AlertTemplate(AlertType.WARNING,"Password tidak sama");
        
    }
  }
  
  @FXML
  private void Login(ActionEvent event) throws IOException{
    String email = txtEmail.getText();
    String password = txtPassword.getText();
    String username = txtEmail.getText();
    CRUD cari = new CRUD();
    
  if(txtEmail.getText().isEmpty() || txtPassword.getText().isEmpty()){
        AlertAlert.AlertTemplate(AlertType.WARNING,"Kolom tidak boleh kosong", ButtonType.OK, 5000);
        return;
  }
profilKonsumen user = cari.Login(email, username, password);
if(user != null ){
    int idKonsumenLogin = user.getIdUser();
    sesiAktif.setIdKonsumen(idKonsumenLogin);
    FXMLLoader loader = new FXMLLoader (
            getClass().getResource("/com/mycompany/tugasAkhir1/Home.fxml"));
            Parent root = loader.load();
            Scene scene = ((Node) event.getSource()).getScene();
            scene.setRoot(root);}
}


  
  }
  
  
   

