package com.mycompany.tugasakhir1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static FXMLLoader loader;
    

    @Override
    public void start(Stage stage) {
        try{
      loader = new FXMLLoader (App.class.getResource("/com/mycompany/tugasakhir1/login.fxml"));
      Parent root = loader.load();
      
      scene = new Scene(root);
      stage.setMaximized(true);
      stage.setScene(scene);
      String css = this.getClass().getResource("/com/mycompany/tugasakhir1/fonts/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.show();
      
        }catch(Exception e){
            e.printStackTrace();}
    }

  
        public static void main(String[] args) {
        launch();
    }
    
}