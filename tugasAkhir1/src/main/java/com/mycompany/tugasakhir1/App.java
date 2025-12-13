package com.mycompany.tugasakhir1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.io.IOException;
import javafx.fxml.FXML;
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
      loader = new FXMLLoader (App.class.getResource("welcomeScreen.fxml"));
      Parent root = loader.load();
      
      scene = new Scene(root);
      stage.setMaximized(true);
      stage.setScene(scene);
      String css = this.getClass().getResource("/com/mycompany/tugasakhir1/fonts/style.css").toExternalForm();
        scene.getStylesheets().add(css);
      stage.show();
      Animasi.startAnimate();
        }catch(Exception e){
            e.printStackTrace();}
    }

    static Object setRoot(String fxml) throws IOException {
        loader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent root = loader.load();
        Animasi.startAnimate();
        String css = App.class.getResource("/com/mycompany/tugasakhir1/fonts/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        scene.setRoot(root);
         return loader.getController();
    }
      public static FXMLLoader getLoader() {
        return loader;
    }


    

    public static void main(String[] args) {
       
        launch();
    }
    
}