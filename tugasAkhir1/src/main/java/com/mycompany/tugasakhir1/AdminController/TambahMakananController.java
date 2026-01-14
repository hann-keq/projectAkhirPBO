/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tugasakhir1.AdminController;

import com.jfoenix.controls.JFXComboBox;
import com.mycompany.tugasakhir1.Admin.DaoAdmin;
import com.mycompany.tugasakhir1.sesiAktif;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hanif
 */
public class TambahMakananController extends EditListMakananController implements Initializable {
String MakananMinuman;
@FXML private JFXComboBox cbMakananMinuman;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
      addItems();
       
        Listener();
        
    }  
    @Override 
    protected void addItems(){
    super.addItems();
    cbMakananMinuman.getItems().addAll("Makanan","Minuman");
    cbMakananMinuman.getSelectionModel().selectFirst();
    }
   
   
    @Override
    protected void Listener(){
        btnBatal.setOnAction(eh ->{
        Stage currentStage = (Stage)((Node)eh.getSource()).getScene().getWindow();
        currentStage.close();
        });
         editMakanan.setOnMouseClicked(eh ->{
         editFotoMakanan();
        });
         btnTambahkan.setOnAction(eh ->{
             updateToDatabase();
            addToDatabase();
        Stage currentStage = (Stage)((Node)eh.getSource()).getScene().getWindow();
        currentStage.close();
        });
    }
    
    private void addToDatabase(){
        DaoAdmin dao = new DaoAdmin();
        dao.addDataToDatabase(nama, stok, jenis, harga, waktu, berat, pathGambar,
                MakananMinuman, idKategori, sesiAktif.getInstance().getIdAdmin());
        System.out.println(nama+stok+jenis+harga+waktu);
    }
}
