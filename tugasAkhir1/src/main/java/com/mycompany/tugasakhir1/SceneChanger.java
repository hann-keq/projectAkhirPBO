package com.mycompany.tugasakhir1;


import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hanif
 */
public class SceneChanger {
    public static void pindahScene(String fxml,ActionEvent event){
        FXMLLoader loader = new FXMLLoader (
            App.class.getResource(fxml));
            Parent root;
            
         try {
             root = loader.load();
             Scene scene = ((Node) event.getSource()).getScene();
            scene.setRoot(root);
         } catch (IOException ex) {
             System.getLogger(Controller.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
         }
    }
    public static void StageChanger(String fxml,String title){
        FXMLLoader loader = new FXMLLoader (
            App.class.getResource(fxml));
        try {
            Parent parent = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(title);
            stage.show();
        } catch (IOException ex) {
            System.getLogger(SceneChanger.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    
}
