/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1;
import com.jfoenix.controls.JFXButton;
import com.mycompany.tugasakhir1.DaoKonsumen;
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

class BaseLogin{
public abstract static class Login{
    protected String nama;
    protected String email;
    protected String password;
    protected String username;
}}
public class Controller extends BaseLogin.Login implements Initializable  {
    
    @Override
   public void initialize (URL url ,ResourceBundle rb){
   animateControll();
   blurrGauss.blurr(200, 200, cobaLingkaranBlur);
   setupListener();
   loadData();
}
   
   private void loadData(){
   }
   private void setupListener(){
       btnLogin.setOnAction(event -> this.Login(event));
       
   }
 
 @FXML 
 private Ellipse cobaLingkaranBlur,cobaLingkaranBlur1,LingkaranBlurHome,LingkaranBlurHome1,
         LingkaranBlurHomeAtas;



@FXML
private Button btnLocation,btnLogin;

    @FXML
    public TextField txtNama;

   @FXML
    private TextField txtEmail,txtPassword,txtUsername,txtAlamat,txtConfirmPassword;
    
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
    protected void showSignUp(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/com/mycompany/tugasAkhir1/signUp.fxml"));
        
        Parent root;
        try {
            root = loader.load();
            Scene scene = ((Node) event.getSource()).getScene();
        
        scene.setRoot(root);
        } catch (IOException ex) {
            System.getLogger(Controller.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }
    
    //pindah ke profile
    
    @FXML
    protected void showLogin(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/com/mycompany/tugasAkhir1/login.fxml"));
        
        Parent root = loader.load();
        Scene scene = ((Node) event.getSource()).getScene();
        
        scene.setRoot(root);
        
}
    

   @FXML
   protected void showLocation(ActionEvent event) throws IOException{
       FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/com/mycompany/tugasAkhir1/location.fxml"));
       
       Parent root = loader.load();
       Scene scene = ((Node) event.getSource()).getScene();
       
       scene.setRoot(root);
    
   }
   
   //pengecekan username
  
   
  
  
  
  
  protected void Login(ActionEvent event) {
     email = txtEmail.getText();
     password = txtPassword.getText();
    username = txtEmail.getText();
 
    
  if(txtEmail.getText().isEmpty() || txtPassword.getText().isEmpty()){
        AlertAlert.AlertTemplate(AlertType.WARNING,"Kolom tidak boleh kosong", ButtonType.OK, 5000);
        return;
  }
  
profilKonsumen user = search(email,password);
if(user != null ){
    sesiAktif(user);
    sesiAktif.getInstance().setPathFile(user.getPathProfile());
    System.out.println(user.getPathProfile());
    AlertAlert.AlertTemplate(AlertType.INFORMATION,"login berhasil",ButtonType.OK,1000);
    SceneChanger.pindahScene("/com/mycompany/tugasAkhir1/Location.fxml",event);
}else{
    AlertAlert.AlertTemplate(AlertType.INFORMATION,"login gagal",ButtonType.OK,1000);
}
  }
  
  
  protected void pindahScene(String fxml,ActionEvent event){
    FXMLLoader loader = new FXMLLoader (
            getClass().getResource(fxml));
            Parent root;
            
         try {
             root = loader.load();
             Scene scene = ((Node) event.getSource()).getScene();
            scene.setRoot(root);
         } catch (IOException ex) {
             System.getLogger(Controller.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
         }
            
}
  protected  profilKonsumen search(String email,String password){
    DaoKonsumen cari = new DaoKonsumen();
    return cari.Login(email, password);
  }
  
  protected void sesiAktif(profilKonsumen user){
  sesiAktif.getInstance().setIdKonsumen(user.getIdUser());
  }


  
  }

  
  
   

