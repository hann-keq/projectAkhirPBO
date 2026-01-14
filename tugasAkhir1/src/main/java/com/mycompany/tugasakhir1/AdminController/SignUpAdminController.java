/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tugasakhir1.AdminController;

import com.mycompany.tugasakhir1.Admin.DaoAdmin;
import com.mycompany.tugasakhir1.DaoKonsumen;
import com.mycompany.tugasakhir1.Controller;
import com.mycompany.tugasakhir1.SignUpController;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author hanif
 */
public class SignUpAdminController  implements Initializable{
@FXML private TextField txtNama,txtAlamatToko,txtEmail,txtNamaToko,txtAlamat,txtUsername;
@FXML private PasswordField txtPassword,txtConfirmPassword;
@FXML private Label cekPassword,cek;
@FXML private Button btnDaftar;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnDaftar.setOnAction(eh -> this.Daftar(eh));
    }
    
    
protected void Daftar(ActionEvent event){
    String nama = txtNama.getText();
    String alamat= txtAlamat.getText();
    String alamatToko = txtAlamatToko.getText();
    String email = txtEmail.getText();
    String namaToko = txtNamaToko.getText();
    String Username = txtUsername.getText();
    String password = txtPassword.getText();
    String cekPassword = txtConfirmPassword.getText();
    
    if(cekPassword()){
        System.out.println("ini jalan");
    DaoAdmin daftar = new DaoAdmin();
    daftar.insertIntoAdmin(nama, Username, email, namaToko, alamat, password);
    pindahScene("/fxml/LoginAdmin.fxml",event);}
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
    protected boolean cekPassword(){
    String password = txtPassword.getText();
    String Passwordcek = txtConfirmPassword.getText();
    boolean benar = Passwordcek.equals(password);
    cekPassword.setVisible(!benar);
    return benar;
    }
     @FXML
    protected void onKeyReleasedcekUsername(KeyEvent event){
    cekUsername();
    }
   @FXML
   private void onKeyReleasedcekPassword(KeyEvent event){
    cekPassword();
   }
      protected boolean cekUsername(){
        
        String username = txtUsername.getText();
    DaoAdmin su = new DaoAdmin();
   boolean duplicate = su.cekUsername(username);
   cek.setVisible(duplicate);
   if(username.isEmpty()){
   cek.setVisible(false);}
   return duplicate;
    }
}
