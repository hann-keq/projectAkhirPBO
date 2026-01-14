/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1.cartController;

import com.jfoenix.controls.JFXCheckBox;
import com.mycompany.tugasakhir1.imageControll;
import com.mycompany.tugasakhir1.sesiAktif;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author hanif
 */
public class cardCartController implements Initializable{
private Cart data;
@FXML private Rectangle myItem;
@FXML private Label lblHarga,lblNama,lblWaktu,lblAlamat,lblJumlah;
String nama;
int harga,jumlah;
private String path;



@FXML private JFXCheckBox cbCheck;


private Consumer<String> onCheck;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imageControll square = new imageControll();
        Image asli = new Image(getClass().getResourceAsStream("/photos/brokoli.jpeg"));
        Image kotak = square.getSquareImage(asli);
        
        myItem.setFill(new ImagePattern(kotak));
        
      
    }
    public void setData(Cart cart){
        
        lblHarga.setText("Rp. "+String.valueOf(cart.getHarga()));
        lblWaktu.setText(String.valueOf(cart.getWaktu()));
        lblNama.setText(cart.getNamaMakanan());
        lblAlamat.setText(cart.getAlamatToko());
        lblJumlah.setText(String.valueOf(cart.getJumlah()));
       path = cart.getPath();
        System.out.println(path + " adalah path di keranjang");
        this.data = cart;
           cartModel.getInstance().getSelectedItem().clear();
 cbCheck.selectedProperty().addListener((obs, oldVal, isSelected) -> {
        if (isSelected) {
            if (!cartModel.getInstance().getSelectedItem().contains(data)) {
                cartModel.getInstance().getSelectedItem().add(data);
            }
        } else {
            cartModel.getInstance().getSelectedItem().remove(data);
        }
    });
       
             try{
    FileInputStream input = new FileInputStream(path);
    Image originalImage = new Image(input);
    
    imageControll square = new imageControll();
        // 2. Potong bagian tengah agar menjadi kotak (1:1)
        Image squareImg = square.getSquareImage(originalImage);

        // 3. Masukkan ke lingkaran
        
        myItem.setFill(new ImagePattern(squareImg));
    }catch(Exception e){
        e.printStackTrace();
    }
    }
    public void onCheck(Consumer <String> onCheck){
        this.onCheck = onCheck;
    
    }
   
  
    
}
