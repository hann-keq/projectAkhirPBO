/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
/**
 *
 * @author hanif
 */
public class CardMakananController implements Initializable {
     @FXML private ImageView myImage;

    @FXML private Label lblNama,lblToko,lblJarak,lblHarga,lblLokasi,lblBerat,lblTanggal;
    @FXML private Button BtnPilih;
    
@Override
public void initialize(URL url ,ResourceBundle rb){
    
    Rectangle clip = new Rectangle();
    
    // settings di imageview di implementasikan ke rectangle
    clip.widthProperty().bind(myImage.fitWidthProperty());
    clip.heightProperty().bind(myImage.fitHeightProperty());
    
    //besar rounded corner rectangle clip
    clip.setArcHeight(30);
    clip.setArcWidth(30);
    
    myImage.setClip(clip);
    }


    

    // Set data makanan ke card
    public void setData(Makanan makanan) {
        lblNama.setText(makanan.getNamaMakanan());
        lblHarga.setText("Rp " + makanan.getHarga());
        lblToko.setText(makanan.getNamaToko());
       lblLokasi.setText(makanan.getNamaKota());
       lblJarak.setText(String.valueOf(makanan.getJarak()));
       lblBerat.setText(String.valueOf(makanan.getJumlahTersedia()));
       BtnPilih.setText("pilih "+makanan.getMakananMinuman());
        
    }
    
    
}


