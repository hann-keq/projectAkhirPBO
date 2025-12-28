/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1.Location;


import com.mycompany.tugasakhir1.Animasi;
import com.mycompany.tugasakhir1.blurrGauss;
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
import javafx.scene.effect.BoxBlur;
import javafx.scene.shape.Ellipse;

/**
 *
 * @author hanif
 */
public class LocationController implements Initializable {
    
    @FXML private Ellipse cobaLingkaranBlur1,cobaLingkaranBlur;
    @FXML private Button btnNext;
     @Override
    public void initialize(URL url, ResourceBundle rb) {
       animateControll();
       blurControll();
        setupListener();
    }
    
    private void setupListener(){
        btnNext.setOnAction(eh -> showHome(eh));
    }
    public void animateControll(){
   Animasi.AnimasiTranslate(cobaLingkaranBlur, 0, -200,3000, true);
    Animasi.AnimasiTranslate(cobaLingkaranBlur1, 0, 200, 4000, true);

}
    private void blurControll(){
         blurrGauss.blurr(200, 200, cobaLingkaranBlur);
    }
        
   private void showHome(ActionEvent event) {
   FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/com/mycompany/tugasAkhir1/Home.fxml"));
   
        Parent root;
        try {
            root = loader.load();
            Scene scene = ((Node) event.getSource()).getScene();
            scene.setRoot(root);
  
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
   BoxBlur blur = new BoxBlur();
   blur.setHeight(200);
    blur.setWidth(200);
    
     }

   
}
