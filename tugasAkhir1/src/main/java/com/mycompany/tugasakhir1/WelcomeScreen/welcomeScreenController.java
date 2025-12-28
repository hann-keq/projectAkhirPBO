/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1.WelcomeScreen;

import com.mycompany.tugasakhir1.Animasi;
import com.mycompany.tugasakhir1.App;
import com.mycompany.tugasakhir1.Controller;
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
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;

/**
 *
 * @author hanif
 */
public class welcomeScreenController implements Initializable{
    
    @FXML 
private Circle circlejpg;
    
 @FXML 
 private Ellipse cobaLingkaranBlur,cobaLingkaranBlur1,LingkaranBlurHome,LingkaranBlurHome1,
         LingkaranBlurHomeAtas;
    
 
 


 @Override
public void initialize(URL url,ResourceBundle rb){
 Image im = new Image(getClass().getResourceAsStream("/com/mycompany/tugasakhir1/fonts/profile.png"));

 circlejpg.setFill(new ImagePattern(im));
 circlejpg.setEffect(new DropShadow(+25d,0d,+2d,Color.DARKVIOLET));
 animateControll();
 
       }


public void animateControll(){
    Animasi.AnimasiTranslate(circlejpg, 0, 50, 1000, true);
}
 @FXML
    private void showLogin(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/com/mycompany/tugasAkhir1/login.fxml"));
        
        Parent root = loader.load();
        Scene scene = ((Node) event.getSource()).getScene();
        
        scene.setRoot(root);
        
}
    
    @FXML 
    private void showSignUp(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/com/mycompany/tugasAkhir1/signUp.fxml"));
        
        Parent root = loader.load();
        Scene scene = ((Node) event.getSource()).getScene();
        
        scene.setRoot(root);
    }
}
