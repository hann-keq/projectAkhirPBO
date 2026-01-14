/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1;

import com.jfoenix.controls.JFXButton;
import com.mycompany.tugasakhir1.Admin.pesanan;
import java.io.File;
import reports.report;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author hanif
 */
public class detailPesananController  implements Initializable
{
    
    @FXML private Button btnStruk;
    @FXML private Rectangle Gambar;
    int idPesanan;
         @FXML private Label lblNama,lblNoPesanan,lblHari,lblWaktu,lblHarga,lblLokasi,lblNamaPelangggan,lblStatus,
                 lblHargaMakanan,lblJumlahMakanan;

    @Override
     public void initialize(URL url, ResourceBundle rb) {
         btnStruk.setOnAction(eh ->{
             reports();
         });
         
      
        System.out.println("detail pesanan controller berjalan");
     }
     
     private void reports(){
         report laporan = new report();
        String filepath = laporan.createReports("/reportsJasper/Flower_Landscape.jrxml", "Report");
         System.out.println("file path = "+filepath);
     }
     
     
    
    
       public void setData(pesanan item){
            lblNama.setText(item.getNamaMakanan());
            lblNoPesanan.setText(String.valueOf(item.getIdPesanan()));
        lblHari.setText(item.getHariPengambilan());
        lblWaktu.setText(item.getWaktuPengambilan());
        lblHarga.setText(String.valueOf("Rp. "+item.getTotal()));
        lblLokasi.setText(item.getAlamatToko());
        lblStatus.setText(item.getStatus());
        lblHargaMakanan.setText(String.valueOf("Rp. "+item.getHarga()));
        lblJumlahMakanan.setText(String.valueOf(item.getNamaMakanan()+" "+"x "+item.getQuantity()));
        String path = item.getPathFile();
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
    }

   
   
   

