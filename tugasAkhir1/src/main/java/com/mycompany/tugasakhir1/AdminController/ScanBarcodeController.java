/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tugasakhir1.AdminController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleNode;
import com.mycompany.tugasakhir1.Admin.DaoAdmin;
import com.mycompany.tugasakhir1.ScanBarcode;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hanif
 */
public class ScanBarcodeController implements Initializable {
ScanBarcode scan = new ScanBarcode();
@FXML private ImageView imageCam;
@FXML private JFXToggleNode btnScan,btnManual;
@FXML private Group grpManual;
@FXML private JFXButton btnKonfirmasi;
@FXML private TextField txtKode;
private final DaoAdmin dao = new DaoAdmin();
private boolean scanFlag = true;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        btnScan.setOnAction(eh ->{
        imageCam.setVisible(true);
        grpManual.setVisible(false);
        scan.initWebCam();
       
        
       scan.ScanBarcode128(
             bf ->{
            Image image = SwingFXUtils.toFXImage(bf, null);
            Platform.runLater(() -> imageCam.setImage(image));
        },hasil ->{
            Platform.runLater(() -> {
                System.out.println("hasil "+ hasil);
            if (hasil != null && scanFlag == true){
                scanFlag = false;
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"id pesanan Terderteksi "+hasil);
            alert.showAndWait();
            
            
            try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Pembayaran.fxml"));
            Parent root = loader.load();
            
            PembayaranController ctrl = loader.getController();
            ctrl.setUpData(hasil);
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Pembayaran");
            stage.show();
            }catch(Exception e){
            e.printStackTrace();
            }
            }
            });
        }
               );
            
        
    });
        btnManual.setOnAction(eh ->{
           
            scan.closeWebCam();
            imageCam.setVisible(false);
           grpManual.setVisible(true); });
        
    }    
   
}
