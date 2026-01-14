/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleNode;
import com.mycompany.tugasakhir1.Admin.DaoAdmin;
import com.mycompany.tugasakhir1.Admin.pesanan;
import com.mycompany.tugasakhir1.Dashboard.DashboardAdminController;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 *
 * @author hanif
 */
public class RincianPesananController extends DashboardAdminController implements Initializable{
    @FXML private FlowPane flowRincian;
    @FXML private StackPane stackKosong;
    @FXML private Circle profil;
    @FXML private JFXToggleNode btnJelajahi,tgglAktif,tgglSelesai,tgglDiterima;
    @FXML private JFXButton btnKembali;
    private String status ;
    int idKonsumen = sesiAktif.getInstance().getIdKonsumen();
    private final DaoAdmin dao = new DaoAdmin();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
    FileInputStream input = new FileInputStream(sesiAktif.getInstance().getPathFile());
    Image originial = new Image(input);
    
    imageControll square = new imageControll();
    Image squareImg = square.getSquareImage(originial);
    
    profil.setFill(new ImagePattern(squareImg));
    }catch(Exception e){
    e.printStackTrace();
    }
    loadData();
    profil.setOnMouseClicked(this::showProfile);
    btnJelajahi.setOnAction(eh ->{
        SceneChanger.pindahScene("/com/mycompany/tugasakhir1/Home.fxml", eh);});
    btnKembali.setOnAction(eh -> {
        SceneChanger.pindahScene("/com/mycompany/tugasakhir1/Home.fxml", eh);});
    tgglSelesai.setOnAction(eh ->{
    filterStatus("selesai");
    });
    tgglAktif.setOnAction(eh ->{
    filterStatus("menunggu");
    });
    tgglDiterima.setOnAction(eh ->{
    filterStatus("diterima");
    });
    }
    @Override
    protected void loadData(){
       
        
        
        System.out.println("idKonsumen di cekRIwayat = "+idKonsumen);
        dataAsli = dao.ambilPesananKonsumen(idKonsumen,"menunggu");
        dataTampil = new ArrayList<>(dataAsli);
        if(!dataAsli.isEmpty()){
           
        pesanan first = dataAsli.get(0);}
        tampilkanData();
    }
    public void filterStatus(String filter){
    dataAsli  = dao.ambilPesananKonsumen(idKonsumen,filter);
    dataTampil = new ArrayList<>(dataAsli);
    tampilkanData();
    }
    @Override
    protected void tampilkanData(){
        stackKosong.setVisible(false);
        flowRincian.setVisible(true);
    flowRincian.getChildren().clear();
    
    if(!dataTampil.isEmpty()){
        for(pesanan item : dataTampil){
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/tugasakhir1/PesananKonsumen/cardRincianPesanan.fxml"));
                Node card = loader.load();
            
                cardRiwayatPesananController ctrl = loader.getController();
                ctrl.setData(item);
                flowRincian.getChildren().add(card);
                
            }catch(Exception e){
            e.printStackTrace();
            }
        }
    }else{
        stackKosong.setVisible(true);
        flowRincian.setVisible(false);
            }
}
     @FXML
    protected void showProfile(MouseEvent event) {
        try{
        FXMLLoader loader = new FXMLLoader (
            getClass().getResource("/com/mycompany/tugasAkhir1/profile.fxml"));
            Parent root = loader.load();
            Scene scene = ((Node) event.getSource()).getScene();
            scene.setRoot(root);
        }catch(Exception e){
            e.printStackTrace();
        }   
    }
}
