/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1;

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
import javafx.scene.input.KeyEvent;

/**
 *
 * @author hanif
 */
public class SignUpController implements Initializable{
    @FXML private Button btnLogin,btnSignUp,btnDaftar;
    @FXML
    private TextField txtEmail,txtPassword,txtUsername,txtAlamat,txtConfirmPassword,txtNama;
    @FXML
private Label test,cek;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupListener();
    }
    private void setupListener(){
    btnLogin.setOnAction( eh -> this.showLogin(eh));
    btnSignUp.setOnAction(eh -> this.showSignUp(eh));
    btnDaftar.setOnAction(eh -> this.Daftar(eh));
    }
    protected void Daftar(ActionEvent event) {
    String nama = txtNama.getText();
    String email = txtEmail.getText();
    String password = txtPassword.getText();
    String alamat = txtAlamat.getText();
    String username = txtUsername.getText();
    String cekPassword = txtConfirmPassword.getText();
    
    if(nama.isEmpty() || email.isEmpty() || password.isEmpty() || alamat.isEmpty()){
       AlertAlert.AlertTemplate(Alert.AlertType.WARNING, "kolom todak boleh kosong");
       return;
    }
   if(cekPassword()){
        DaoKonsumen su = new DaoKonsumen();
        su.InputToDatabase(username, password, nama, email, alamat);
        AlertAlert.AlertTemplate(Alert.AlertType.INFORMATION,"daftar berhasil",ButtonType.OK,1000);
        pindahScene("/com/mycompany/tugasAkhir1/Login.fxml", event);
        System.out.println("Berhasil");}
        
    else{
       
         AlertAlert.AlertTemplate(Alert.AlertType.WARNING,"Password tidak sama");
        
    }
  }
      protected boolean cekUsername(){
        
        String username = txtUsername.getText();
    DaoKonsumen su = new DaoKonsumen();
   boolean duplicate = su.CekUsername(username);
   cek.setVisible(duplicate);
   if(username.isEmpty()){
   cek.setVisible(false);}
   return duplicate;
    }
    //ikut diatas
    @FXML
    protected void onKeyReleasedcekUsername(KeyEvent event){
    cekUsername();
    }
    
    //pengecekan password
    protected boolean cekPassword(){
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
   private void showSignUp(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/com/mycompany/tugasAkhir1/signUp.fxml"));
        
        Parent root;
        try {
            root = loader.load();
            Scene scene = ((Node) event.getSource()).getScene();
        
        scene.setRoot(root);
        } catch (IOException ex) {
            System.getLogger(SignUpController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }
   private void showLogin(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/com/mycompany/tugasAkhir1/login.fxml"));
        
        Parent root;
        try {
            root = loader.load();
             Scene scene = ((Node) event.getSource()).getScene();
        
        scene.setRoot(root);
        } catch (IOException ex) {
            System.getLogger(SignUpController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
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
    
}
