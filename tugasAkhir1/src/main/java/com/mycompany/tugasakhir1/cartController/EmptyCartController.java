/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1.cartController;

import com.jfoenix.controls.JFXButton;
import com.mycompany.tugasakhir1.SceneChanger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 *
 * @author hanif
 */
public class EmptyCartController implements Initializable{
@FXML private JFXButton btnBack;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnBack.setOnAction(eh -> SceneChanger.pindahScene("/com/mycompany/tugasakhir1/Home", eh));
    }
    
}
