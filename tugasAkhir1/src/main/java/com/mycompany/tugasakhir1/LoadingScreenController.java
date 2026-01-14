/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tugasakhir1;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author hanif
 */
public class LoadingScreenController  {

   public static <T> void loadData(Callable<T> fetchLogic, Consumer<T> onResult) {
        // 1. Setup UI untuk Stage Loading
        ProgressIndicator pi = new ProgressIndicator();
        Text text = new Text("Memuat Data...");
        VBox layout = new VBox(15, pi, text);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: white; -fx-padding: 30; -fx-border-color: #ccc; -fx-border-width: 1;");

        // 2. Buat Stage baru (Window kecil di tengah)
        Stage loadingStage = new Stage();
        loadingStage.initStyle(StageStyle.UNDECORATED); // Tanpa header/tombol close
        loadingStage.initModality(Modality.APPLICATION_MODAL); // User gak bisa klik window belakang
        loadingStage.setScene(new Scene(layout));

        // 3. Logika Task
        Task<T> task = new Task<>() {
            @Override
            protected T call() throws Exception {
                // Kamu bisa tambah simulasi progress di sini jika mau
                return fetchLogic.call();
            }
        };

        task.setOnRunning(e -> loadingStage.show());

        task.setOnSucceeded(e -> {
            text.setText("Berhasil!");
            pi.setProgress(1.0);
            
            // Kirim data ke UI Utama
            onResult.accept(task.getValue());

            // Tunggu sebentar lalu tutup otomatis
            PauseTransition delay = new PauseTransition(Duration.seconds(0.8));
            delay.setOnFinished(ev -> loadingStage.close());
            delay.play();
        });

        task.setOnFailed(e -> {
            loadingStage.close();
            task.getException().printStackTrace();
        });

        new Thread(task).start();
    }

   
}
    

