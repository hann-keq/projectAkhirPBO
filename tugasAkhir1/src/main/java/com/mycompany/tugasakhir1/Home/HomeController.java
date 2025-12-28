/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1.Home;
import com.jfoenix.controls.JFXToggleNode;
import com.mycompany.tugasakhir1.CRUD;
import com.mycompany.tugasakhir1.Animasi;
import com.mycompany.tugasakhir1.CardMakananController;
import com.mycompany.tugasakhir1.Makanan;
import com.mycompany.tugasakhir1.Profile.profileController;
import com.mycompany.tugasakhir1.blurrGauss;
import java.io.File;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
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
    private final CRUD crud = new CRUD();
    private List<Makanan> dataAsli;
    private List<Makanan> dataTampil;
    @FXML private ToggleGroup filter;
    
    @FXML private JFXToggleNode btnAll,btnVegetable,btnHeavy,btnDrink,btnBaked;
    @FXML
    private Ellipse LingkaranBlurHome,LingkaranBlurHome1;
    
    @FXML
    private FlowPane container;
    
    @FXML private Label lblRincian;
    
    @FXML private Circle goToProfile;
    @Override
public void initialize(URL url,ResourceBundle rb){
    
    Image asli = new Image(getClass().getResourceAsStream("/com/mycompany/tugasakhir1/fonts/profile.png"));
    profileController square = new profileController();
    Image kotak = square.getSquareImage(asli);
    goToProfile.setFill(new ImagePattern(kotak));
 animateControll();
blurControll();
  setupListener();
  loadDataAwal();
}

private void loadDataAwal(){
    dataAsli = crud.ambilMakanan();
    dataTampil = new ArrayList<>(dataAsli);
    tampilkanData();
    }

private void tampilkanData(){
    container.getChildren().clear();
    lblRincian.setText("Showing "+dataTampil.size()+ " available items");
     for (Makanan item : dataTampil) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/tugasAkhir1/cardMenu.fxml"));
                Node card = loader.load();

                // Set data ke card
                CardMakananController ctrl = loader.getController();
                ctrl.setData(item);                                         //ambil data dari CRUD.java

                container.getChildren().add(card);
               

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
    }

private void setupListener(){
    btnAll.setOnAction(eh -> reset());
    btnVegetable.setOnAction(eh -> tampilkanDataByFilter("vegetable"));
    btnHeavy.setOnAction(eh -> tampilkanDataByFilter("heavy food") );
    btnDrink.setOnAction(eh -> tampilkanDataByFilter("drink"));
    btnBaked.setOnAction(eh -> tampilkanDataByFilter("baked"));
    goToProfile.setOnMouseClicked(eh -> showProfile(eh));
    
}


private void tampilkanDataByFilter(String filter){
    dataTampil = crud.filterMakanan(filter);
    tampilkanData();
    }

private void reset(){
    dataTampil = crud.ambilMakanan();
    tampilkanData();
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