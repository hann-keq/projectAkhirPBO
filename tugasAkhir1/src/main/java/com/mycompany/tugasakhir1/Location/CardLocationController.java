/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tugasakhir1.Location;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleNode;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;

/**
 * FXML Controller class
 *
 * @author hanif
 */
public class CardLocationController implements Initializable {
@FXML private JFXToggleNode btnLoc;
@FXML private FlowPane containerChild;
ToggleGroup groupLokasi = new ToggleGroup();
private Consumer <String> onClick;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
      
    }    
    
    public void onCLick(Consumer <String> onClick){
    this.onClick = onClick;
        
    }
   
   
        
    public void setData(location set){
  JFXToggleNode btn = new JFXToggleNode();
          btn.toggleGroupProperty().set(groupLokasi);
        btn.setPrefHeight(61);
        btn.setPrefWidth(454);
        btn.setAlignment(Pos.BASELINE_LEFT);
        btn.setText(set.getDistrict()+" "+set.getSubDistrict());
        containerChild.getChildren().add(btn);
        
         btn.setOnAction(eh -> {
        String lokasi = btn.getText();
        onClick.accept(lokasi);
        set.setFinalLocation(lokasi);
            System.out.println("lokasi di pilih "+ lokasi);});
   // btnLoc.setText(set.getDistrict()+" "+set.getSubDistrict());
    }
    
}
