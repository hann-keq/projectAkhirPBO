/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tugasakhir1.AdminController;

import com.mycompany.tugasakhir1.Admin.DaoAdmin;
import com.mycompany.tugasakhir1.Admin.DataAdmin;
import com.mycompany.tugasakhir1.AlertAlert;
import com.mycompany.tugasakhir1.DaoKonsumen;
import com.mycompany.tugasakhir1.Controller;
import com.mycompany.tugasakhir1.Profile.profilKonsumen;
import com.mycompany.tugasakhir1.SceneChanger;
import com.mycompany.tugasakhir1.sesiAktif;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author hanif
 */
public class LoginAdminController implements Initializable{
 @FXML TextField txtEmail,txtPassword;
 @FXML Button btnLogin,btnSignUp,btnLogin1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupListener();
        System.out.println("berhasil dimuat");
    }
    private void setupListener(){
        btnLogin.setOnAction(eh -> this.Login(eh));
        btnSignUp.setOnAction(eh ->this.showSignUp(eh));
        btnLogin1.setOnAction(eh ->this.showLogin(eh));
                
    }

  protected void Login(ActionEvent event){
      String email = txtEmail.getText();
      String password = txtPassword.getText();
      
      DataAdmin admin = searchAdmin(email,password);
      if(admin != null){
          
      sesiAktifAdmin(admin);
       AlertAlert.AlertTemplate(Alert.AlertType.INFORMATION,"login berhasil",ButtonType.OK,1000);
    pindahScene("/fxml/DashboardAdmin.fxml",event);
      
  }else{
    AlertAlert.AlertTemplate(Alert.AlertType.INFORMATION,"login gagal",ButtonType.OK,1000);
          System.out.println("data masih null");
  }
  }
  

  
  protected DataAdmin searchAdmin(String email , String password){
      DaoAdmin dao = new DaoAdmin();
      return dao.LoginAdmin(email, password);
  }
  protected void sesiAktifAdmin(DataAdmin admin){
    sesiAktif.getInstance().setIdAdmin(admin.getAdminAktif());
      System.out.println("id admin akitf = "+ admin.getAdminAktif());
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
  protected void showLogin(ActionEvent event){
    FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/fxml/LoginAdmin.fxml"));
        
        Parent root;
        try {
            root = loader.load();
            Scene scene = ((Node) event.getSource()).getScene();
        
        scene.setRoot(root);
        } catch (IOException ex) {
            System.getLogger(Controller.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
  }
  
  protected void showSignUp(ActionEvent event){
   
   
  FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/fxml/SignUpAdmin.fxml"));
        
        Parent root;
        try {
            root = loader.load();
            Scene scene = ((Node) event.getSource()).getScene();
        
        scene.setRoot(root);
        } catch (IOException ex) {
            System.getLogger(Controller.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }}
        }
