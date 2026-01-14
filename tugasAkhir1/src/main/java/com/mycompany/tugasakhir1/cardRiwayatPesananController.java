/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1;

import com.jfoenix.controls.JFXButton;
import com.mycompany.tugasakhir1.Admin.pesanan;
import com.mycompany.tugasakhir1.AdminController.cardPesananController;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author hanif
 */
public class cardRiwayatPesananController {
    int idPesanan;
    @FXML private Rectangle Gambar;
    @FXML protected JFXButton btnDetail;
   
     @FXML private Label lblNama,lblNoPesanan,lblHari,lblWaktu,lblHarga,lblLokasi,lblNamaPelangggan,lblStatus,lblJumlah;
     
    protected void setData(pesanan orderan){
        btnDetail.setOnAction(eh -> DetailsWindow(orderan));
        lblNama.setText(orderan.getNamaMakanan());
        lblNoPesanan.setText(String.valueOf(orderan.getIdPesanan()));
        lblHari.setText(orderan.getHariPengambilan());
        lblWaktu.setText(orderan.getWaktuPengambilan());
        lblHarga.setText(String.valueOf(orderan.getTotal()));
        lblLokasi.setText(orderan.getAlamatToko());
        lblStatus.setText(orderan.getStatus());
        lblJumlah.setText(String.valueOf(orderan.getQuantity()));
        idPesanan = orderan.getIdPesanan();
        
        
        String path = orderan.getPathFile();
            System.out.println("path makanan "+path);
        
        if(path != null && !path.isEmpty()){
          try{ 
          FileInputStream input = new FileInputStream(path);
          Image original = new Image(input);
          
          imageControll square = new imageControll();
          Image squareImage = square.getSquareImage(original);
          
          Gambar.setFill(new ImagePattern(squareImage));
          }catch(Exception e){
          e.printStackTrace();
          }}
    }
    
    private void DetailsWindow (pesanan orderan){
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/tugasakhir1/PesananKonsumen/Lihat Detail Pesanan.fxml"));
    try{
    Parent parent = loader.load();
    detailPesananController dpc = loader.getController();
    dpc.setData(orderan);
     Stage stage = new Stage();
    stage.setScene(new Scene(parent));
    
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.setTitle( "Rincian Pesanan");
    stage.show();
}catch(Exception e){
e.printStackTrace();}
}}
