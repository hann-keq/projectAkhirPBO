/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tugasakhir1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author hanif
 */
public class LoadingScreenController implements Initializable {

    @FXML
    private ProgressIndicator progressIndicator;
    @FXML
    private Text loadingText;

    LoadingScreen loadingScreen;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadingScreen = new LoadingScreen(progressIndicator, loadingText);
    }

    @FXML
    void startProgress(ActionEvent event) {
        Thread thread = new Thread(loadingScreen);
        thread.setDaemon(true);
        thread.start();
    }

    @FXML
    void restart(ActionEvent event) {
        progressIndicator.setProgress(0);
        loadingText.setText("Loading...");
    }


    //Loading screen runnable class
    public class LoadingScreen implements Runnable {

        ProgressIndicator progressIndicator;
        Text loadingText;

        public LoadingScreen(ProgressIndicator progressIndicator, Text loadingText) {
            this.progressIndicator = progressIndicator;
            this.loadingText = loadingText;
        }

        @Override
        public void run() {
            while(progressIndicator.getProgress() <= 1) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        progressIndicator.setProgress(progressIndicator.getProgress() + 0.1);
                    }
                });
                synchronized (this){
                    try{
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }
                }
            }
            loadingText.setText("Done Loading");
        }
    }
    
}
