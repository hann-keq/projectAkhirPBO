/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tugasakhir1.Dashboard;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleNode;
import com.mycompany.tugasakhir1.Admin.DaoAdmin;
import com.mycompany.tugasakhir1.Admin.DataAdmin;
import com.mycompany.tugasakhir1.Admin.pesanan;
import com.mycompany.tugasakhir1.AdminController.PesananKosongController;
import com.mycompany.tugasakhir1.AdminController.cardPesananController;
import com.mycompany.tugasakhir1.CardMakananController;
import com.mycompany.tugasakhir1.Controller;
import com.mycompany.tugasakhir1.Makanan;
import com.mycompany.tugasakhir1.ScanBarcode;
import com.mycompany.tugasakhir1.SceneChanger;
import com.mycompany.tugasakhir1.sesiAktif;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hanif
 */
public class DashboardAdminController implements Initializable {
protected List<pesanan> dataAsli;
protected List<pesanan> dataTampil;
protected List<pesanan> dataAsli1;
private final int idAdminAktif =sesiAktif.getInstance().getIdAdmin();
@FXML private JFXButton scan;
protected pesanan data;
private final DaoAdmin dao = new DaoAdmin();
@FXML private FlowPane container;
@FXML private Label lblPsnMenunggu,lblPsnSelesai;
@FXML private JFXToggleNode btnMenunggu,btnDiterima,btnPengelola,btnPenjualan;
@FXML private JFXButton btnScan;
 @FXML private ToggleGroup pesanan;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadData();
        setupAndListener();
        btnMenunggu.setOnAction(eh -> loadDataByFilter("Menunggu"));
        btnDiterima.setOnAction(eh -> loadDataByFilter("Diterima"));
        scan.setOnAction(eh -> {
            ScanBarcode();}
                );
        btnScan.setOnAction(eh -> {
        SceneChanger.StageChanger("/fxml/scanBarcode.fxml", "scan Barcode");});
    }    
    protected void setupAndListener(){
        
        
        btnPengelola.setOnAction(eh -> SceneChanger.pindahScene("/fxml/kelolaMakanan.fxml",eh));
        btnPenjualan.setOnAction(eh -> SceneChanger.pindahScene("/fxml/penjualan.fxml", eh));
        
    }
    protected void loadDataByFilter(String status){
        dataAsli1 = dao.ambilPesanan(idAdminAktif, status);
    dataTampil = new ArrayList<>(dataAsli1);
     if(!dataAsli.isEmpty() ){
       pesanan first = dataAsli.get(0);
   
    lblPsnSelesai.setText(String.valueOf(first.getDiterima()));
    lblPsnMenunggu.setText(String.valueOf(first.getMenunggu()));
         System.out.println(first.getSelesai());
         System.out.println(first.getDiterima());
         
     }
    tampilkanData();
    }
   
    protected void loadData(){
        System.out.println("ini jalan load dayanya");
        dataAsli = dao.ambilPesanan(idAdminAktif,"Menunggu");
        dataTampil = new ArrayList<>(dataAsli);
        
        if(!dataAsli.isEmpty() ){
       pesanan first = dataAsli.get(0);
    lblPsnMenunggu.setText(String.valueOf(first.getMenunggu()));
    lblPsnSelesai.setText(String.valueOf(first.getDiterima()));}
        tampilkanData();
        
       
    }
    
    
    protected void tampilkanData(){
    container.getChildren().clear();
    System.out.println("After clear: " + container.getChildren().size());

if(!dataTampil.isEmpty()){
     
     for (pesanan item : dataTampil) {
      
         System.out.println("tes");
            try {
                System.out.println("tes 3");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/tugasakhir1/cardPesanan.fxml"));
                Node card = loader.load();

                // Set data ke card
                cardPesananController ctrl = loader.getController();
                ctrl.setData(item);                                         //ambil data dari CRUD.java
                container.getChildren().add(card);
               

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
     System.out.println("After add : " + container.getChildren().size());

    
    }else{
    
            System.out.println("fxml pesanan kosong jalan");
        try{
        FXMLLoader loader = new FXMLLoader (getClass().getResource("/fxml/pesananKosong.fxml"));
        Node card = loader.load();
        PesananKosongController ctrl = loader.getController();
        ctrl.setData();
        container.getChildren().add(card);
        System.out.println(card.getBoundsInParent());
        lblPsnMenunggu.setText("0");
        lblPsnSelesai.setText("0");
                }catch(Exception e ){
                e.printStackTrace();
                }
}
    }
    private void ScanBarcode(){
        File desktopDir = new File(System.getProperty("user.home"), "Desktop");
if(!desktopDir.exists() || !desktopDir.isDirectory()) {
    desktopDir = new File(System.getProperty("user.home")); // fallback ke home
}
    FileChooser fileChooser = new FileChooser();
fileChooser.setTitle("Upload new profile image");
fileChooser.setInitialDirectory(desktopDir);
fileChooser.getExtensionFilters().addAll(
    new FileChooser.ExtensionFilter("PNG Image", "*.png"),
    new FileChooser.ExtensionFilter("All image files", "*.jpg", "*.png")
);
Stage stage = new Stage();
File selectedFile = fileChooser.showOpenDialog(stage);
ScanBarcode scan = new ScanBarcode();
String hasilScan = scan.ScanBarcodeFromUpload(selectedFile);
        System.out.println(hasilScan);
        
        if(!hasilScan.isEmpty()){
        dao.UpdateStatus(Integer.parseInt(hasilScan));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pemberitahuan");
        alert.setHeaderText("Scan Barcode selesai");
        alert.setContentText("Pesanan konsumen pada no pesanan "+hasilScan +"telah diselesaikan");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
        return;
        }
        }
    }
    
}
