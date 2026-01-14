/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1.Home;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleNode;
import com.mycompany.tugasakhir1.DaoKonsumen;
import com.mycompany.tugasakhir1.Animasi;
import com.mycompany.tugasakhir1.CardMakananController;
import com.mycompany.tugasakhir1.LoadingScreenController;
import com.mycompany.tugasakhir1.Makanan;
import com.mycompany.tugasakhir1.Profile.profileController;
import com.mycompany.tugasakhir1.SceneChanger;
import com.mycompany.tugasakhir1.blurrGauss;
import com.mycompany.tugasakhir1.imageControll;
import com.mycompany.tugasakhir1.sesiAktif;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import javafx.scene.shape.Ellipse;

/**
 *
 * @author hanif
 */
public class HomeController implements Initializable {
    private LoadingScreenController loading = new LoadingScreenController();
    private final DaoKonsumen crud = new DaoKonsumen();
    private List<Makanan> dataAsli;
    private List<Makanan> dataTampil;
    private int count =0;
    @FXML private ToggleGroup filter;
    @FXML private JFXButton btnKeranjang;
    @FXML private JFXToggleNode btnAll,btnVegetable,btnHeavy,btnDrink,btnBaked,btnPesanan;
    @FXML
    private Ellipse LingkaranBlurHome,LingkaranBlurHome1;
    @FXML private TextField txtCari;
    @FXML
    private FlowPane container;
    
    @FXML private Label lblRincian,lblCounter;
    
    @FXML private Group groupCounter;
    
    @FXML private Circle goToProfile;
    @Override
public void initialize(URL url,ResourceBundle rb){
    
    try{
    FileInputStream input = new FileInputStream(sesiAktif.getInstance().getPathFile());
    Image originial = new Image(input);
    
    imageControll square = new imageControll();
    Image squareImg = square.getSquareImage(originial);
    
    goToProfile.setFill(new ImagePattern(squareImg));
    }catch(Exception e){
    e.printStackTrace();
    }
   /* Image asli = new Image(getClass().getResourceAsStream("/com/mycompany/tugasakhir1/fonts/profile.png"));
    imageControll square = new imageControll();
    Image kotak = square.getSquareImage(asli);
    goToProfile.setFill(new ImagePattern(kotak));*/
 animateControll();
blurControll();
  setupAndListener();
  loadData();
}

private void loadData() {

    Task<List<Makanan>> task = new Task<>() {
        @Override
        protected List<Makanan> call() {
            return crud.ambilMakanan(); // DB + tunnel (AMAN di background)
        }
    };

    task.setOnRunning(e -> {
        // kalau mau: tampilkan loading
        lblRincian.setText("Loading data...");
        
    });

    task.setOnSucceeded(e -> {
        dataAsli = task.getValue();                // simpan data asli
        dataTampil = new ArrayList<>(dataAsli);    // data untuk ditampilkan
        
        tampilkanData();                           // UI update (AMAN)
    });

    task.setOnFailed(e -> {
        task.getException().printStackTrace();
    });

    new Thread(task, "load-makanan-thread").start();
}


public void addCart(Makanan m){
count++;
    System.out.println(count);
lblCounter.setText(String.valueOf(count));
if(count != 0){
groupCounter.setVisible(true);
}
}
private void tampilkanData(){
    
    container.getChildren().clear();
    System.out.println("After clear: " + container.getChildren().size());

    lblRincian.setText("Showing "+dataTampil.size()+ " available items");
    
    
    
     for (Makanan item : dataTampil) {
         
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/tugasAkhir1/cardMenu.fxml"));
                Node card = loader.load();

                // Set data ke card
                CardMakananController ctrl = loader.getController();
                ctrl.setData(item);                                         //ambil data dari CRUD.java
                ctrl.onClick(this :: addCart);
                
                System.out.println("count ++ = "+count );
                container.getChildren().add(card);
               

            } catch (IOException e) {
                e.printStackTrace();
            }
        }System.out.println("After add : " + container.getChildren().size());

    
    }
private void cariMakanan(String keyword){
if(keyword == null || keyword.isEmpty()){
    dataTampil = new ArrayList<>(dataAsli);
}else{
dataTampil = new ArrayList<>();
    for (Makanan item : dataAsli){
        if(item.getNamaMakanan().toLowerCase().contains(keyword)){
            dataTampil.add(item);
    }
}
}tampilkanData();
}
private void setupAndListener() {
    txtCari.setOnKeyTyped(eh -> {
    String keyword = txtCari.getText().toLowerCase();
        cariMakanan(keyword);
    });
   btnAll.setOnAction(eh -> loadData());
   btnVegetable.setOnAction(eh -> tampilkanDataByFilter("vegetables"));
   btnHeavy.setOnAction(eh -> tampilkanDataByFilter("heavy food"));
   btnDrink.setOnAction(eh -> tampilkanDataByFilter("drink"));
   btnBaked.setOnAction(eh -> tampilkanDataByFilter("baked"));
   btnPesanan.setOnAction(eh ->{
   SceneChanger.pindahScene("/com/mycompany/tugasakhir1/PesananKonsumen/Halaman Pesanan Saya.fxml", eh);});
   btnKeranjang.setOnAction(eh -> {
   SceneChanger.pindahScene("/com/mycompany/tugasAkhir1/cart.fxml", eh);});

   
    goToProfile.setOnMouseClicked(this::showProfile);
}


private void tampilkanDataByFilter(String filter){
     Task<List<Makanan>> task = new Task<>() {
        @Override
        protected List<Makanan> call() {
            return crud.filterMakanan(filter); 
        }
    };
    task.setOnRunning(eh -> {
        lblRincian.setText("Mengambil data");
            });
    task.setOnSucceeded(eh ->{
    dataAsli = task.getValue();
    dataTampil = new ArrayList<>(dataAsli);
    tampilkanData();
    });
    task.setOnFailed(eh ->{ 
    task.getException().printStackTrace();
            });
    
    new Thread(task,"load filter").start();
    }


  @FXML
    private void showProfile(MouseEvent event) {
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


public void animateControll(){
   Animasi.AnimasiTranslate(LingkaranBlurHome, 0, -200,3000, true);
    Animasi.AnimasiTranslate(LingkaranBlurHome1, 0, 200, 4000, true);

}
public void blurControll(){
 blurrGauss.blurr(200, 200, LingkaranBlurHome);
 blurrGauss.blurr(200, 200, LingkaranBlurHome1);
}
    



}