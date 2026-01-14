/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1.Location;


import com.gluonhq.charm.glisten.control.AutoCompleteTextField;
import com.mycompany.tugasakhir1.Animasi;
import com.mycompany.tugasakhir1.blurrGauss;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.mycompany.tugasakhir1.DaoKonsumen;
import com.mycompany.tugasakhir1.SceneChanger;
import com.mycompany.tugasakhir1.cardRiwayatPesananController;
import com.mycompany.tugasakhir1.Location.CardLocationController;
import com.mycompany.tugasakhir1.Makanan;
import com.mycompany.tugasakhir1.sesiAktif;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Ellipse;
import javafx.scene.web.WebView;

/**
 *
 * @author hanif
 */
public class LocationController implements Initializable {
    
    @FXML private Ellipse cobaLingkaranBlur1,cobaLingkaranBlur;
    @FXML private Button btnNext;
    @FXML private AnchorPane map;
     private final DaoKonsumen crud = new DaoKonsumen();
    @FXML private AutoCompleteTextField<String> comboLoc;
    @FXML private Label lblLokasi;
    @FXML private FlowPane container; 
    @FXML private TextField txtCari;
    String lokasi;
    int id;
    
    private List<location> data;
    private List<location> dataTampil;
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        setData();
       animateControll();
       blurControll();
        setupListener();
        searchLocation();
       addList();
       
      txtCari.setOnKeyTyped(eh ->{
      String keyword = txtCari.getText().toLowerCase();
          System.out.println(keyword);
      cariLokasi(keyword);
      });
    }
    private void setupListener(){
        btnNext.setOnAction(eh -> {
            System.out.println(id+lokasi);
            String feedback = crud.InsertCustomerLocation(lokasi,id);
            System.out.println(feedback);
            showHome(eh);
        });
    }
    
    public void addLocation(String loc){
        lokasi = loc;
         id = sesiAktif.getInstance().getIdKonsumen();
        System.out.println("lokasi di controller "+lokasi + id);
       
        
    
    }
    private void setData(){
    data = crud.locationCompleter();
    dataTampil = new ArrayList(data);
    }
    private void cariLokasi(String keyword){
if(keyword == null || keyword.isEmpty()){
    dataTampil = new ArrayList<>(data);
}else{
dataTampil = new ArrayList<>();
    for (location item : data){
        if(item.getSubDistrict().toLowerCase().contains(keyword)){
            System.out.println(item.getSubDistrict().toLowerCase().contains(keyword));
            dataTampil.add(item);
            System.out.println(dataTampil.size());
    }
}
}addList();
}
    private void addList(){
      container.getChildren().clear();
     for(location item : dataTampil){
   
         try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/cardLocation.fxml"));
                Node card = loader.load();
            
                CardLocationController ctrl = loader.getController();
                ctrl.setData(item);
                ctrl.onCLick(text ->{
                    System.out.println("isinya"+text);
                addLocation(text);});
                container.getChildren().add(card);
                
            }catch(Exception e){
            e.printStackTrace();
            }
     }
     
    }
    
    public void animateControll(){
   Animasi.AnimasiTranslate(cobaLingkaranBlur, 0, -200,3000, true);
    Animasi.AnimasiTranslate(cobaLingkaranBlur1, 0, 200, 4000, true);

}
    private void blurControll(){
         blurrGauss.blurr(200, 200, cobaLingkaranBlur);
    }
        
   private void showHome(ActionEvent event) {
   FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/com/mycompany/tugasAkhir1/Home.fxml"));
   
        Parent root;
        try {
            root = loader.load();
            Scene scene = ((Node) event.getSource()).getScene();
            scene.setRoot(root);
  
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
   BoxBlur blur = new BoxBlur();
   blur.setHeight(200);
    blur.setWidth(200);
    
     }
  private void map(double lat , double longi){
  WebView web = new WebView();
  
  double latid = lat;
  double longitude = longi;
  
  String googleMapsUrl = "https://www.google.com/maps/@" + lat + "," + longitude + ",15z";
  web.getEngine().load(googleMapsUrl);
  map.getChildren().add(web);
 
  }
   private void  searchLocation(){
   try{
       URI uri = URI.create("https://checkip.amazonaws.com");
    URL url = uri.toURL();
    BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
    String publicIP = br.readLine().trim();
    System.out.println("Working dir: " + new File(".").getAbsolutePath());

  // InputStream db= LocationController.class.getClassLoader().getResourceAsStream("dbip-city-lite-2026-01.mmdb");
   File db = new File("G:/tugaskuliah/tugas_kuliah_semester_3/projectAkhirPBO/tugasAkhir1/src/main/java/com/mycompany/tugasakhir1/Location/dbip-city-lite-2026-01.mmdb");
   if(!db.exists()){
       System.out.println("path file tidak ditemukan");
   return;
   }
    DatabaseReader dr = new DatabaseReader.Builder(db).build();
    
    InetAddress ipAdress = InetAddress.getByName(publicIP);
    CityResponse response = dr.city(ipAdress);
    
       System.out.println("\n==== Hasil Lokasi ====");
       System.out.println("Kota : "+response.city().name());
       System.out.println("provinsi : "+response.mostSpecificSubdivision().name());
       System.out.println("Latitude  : " + response.location().latitude());
        System.out.println("Longitude : " + response.location().longitude());
       lblLokasi.setText("Lokasi yang terdeteksi dari ip "+ response.city().name());
       
   }catch(Exception e){
   e.printStackTrace();
   }
   }

   
}
