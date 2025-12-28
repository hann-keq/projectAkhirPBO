package com.mycompany.tugasakhir1.Profile;

import com.mycompany.tugasakhir1.CRUD;
import com.mycompany.tugasakhir1.Controller;
import com.mycompany.tugasakhir1.sesiAktif;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hanif
 */
public class profileController implements Initializable {
    private CRUD crud;
    private int idKonsumen;
   
    @FXML private Circle profile,myprofile;
    @FXML 
    private Label lblNama,lblEmail,lblEmail1,lblTelepon,lblAlamat;
   
 
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       

    
    loadData();
    
    Image originalImg = new Image(getClass().getResourceAsStream("/com/mycompany/tugasakhir1/fonts/profile.png"));

        // 2. Potong bagian tengah agar menjadi kotak (1:1)
        Image squareImg = getSquareImage(originalImg);

        // 3. Masukkan ke lingkaran
        myprofile.setFill(new ImagePattern(squareImg));
    }
    
    public Image getSquareImage(Image img) {
        double width = img.getWidth();
        double height = img.getHeight();
        
        // Tentukan dimensi kotak (ambil sisi terpendek)
        double side = Math.min(width, height);

        // Hitung posisi koordinat X dan Y agar potongannya tepat di tengah
        double x = (width - side) / 2;
        double y = (height - side) / 2;

        // Gunakan ImageView bantuan untuk memotong (cropping)
        ImageView imageView = new ImageView(img);
        Rectangle2D viewportRect = new Rectangle2D(x, y, side, side);
        imageView.setViewport(viewportRect);

        // Ambil snapshot dari hasil crop tersebut
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT); 
        
        return imageView.snapshot(params, null);
    }
    private void loadData(){
    profil();
    loadImage();
    }
    private void loadImage(){
        Image im = new Image(getClass().getResourceAsStream("/com/mycompany/tugasakhir1/fonts/profile.png"));
        ImageView iv = new ImageView(im);
        iv.setPreserveRatio(false);
        iv.setSmooth(true);
      
        
    SnapshotParameters sp = new SnapshotParameters();
    sp.setFill(Color.BLUEVIOLET);
    
    Image snapshot = iv.snapshot(sp, null);
    profile.setFill(new ImagePattern(snapshot));
  
    
    }
    private void profil(){
         crud = new CRUD();
        int id = sesiAktif.getIdKonsumen();
         profilKonsumen user = crud.Profile(id);
        if(user != null){
            lblNama.setText(user.getNamaKonsumen());
            lblEmail.setText(user.getEmail());
            lblTelepon.setText(String.valueOf(user.getNoTelp()));
    }
    }
        
     @FXML
    private void showLogin(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/com/mycompany/tugasAkhir1/login.fxml"));
        
        Parent root = loader.load();
        Scene scene = ((Node) event.getSource()).getScene();
        
        scene.setRoot(root);
        
}

    
}
