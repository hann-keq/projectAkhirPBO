/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tugasakhir1.AdminController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.mycompany.tugasakhir1.Admin.DaoAdmin;
import com.mycompany.tugasakhir1.Makanan;
import com.mycompany.tugasakhir1.imageControll;
import com.mycompany.tugasakhir1.kelolaMakanan.KelolaMakananController;
import com.mycompany.tugasakhir1.sesiAktif;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hanif
 */
public class EditListMakananController implements Initializable {
protected String pathGambar;
@FXML private Rectangle Gambar;
@FXML private Label path,lblKeterangan,lblKeterangan2;
protected int idMakanan,idKategori;
protected String waktu,nama,jenis,berat;
int stok,harga;

@FXML protected JFXButton btnTambahkan,btnBatal;
@FXML protected Rectangle editMakanan;
@FXML private TextField txtNama,txtStok,txtHarga;
@FXML private JFXComboBox cbBerat,cbJenisMakanan,cbExperied;
 
/**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        addItems();
        
        Listener();
        
    }    
    protected void Listener(){
         editMakanan.setOnMouseClicked(eh ->{
         editFotoMakanan();
        });
         btnTambahkan.setOnAction(eh ->{
             updateToDatabase();
            DatabaseUpdate();
        Stage currentStage = (Stage)((Node)eh.getSource()).getScene().getWindow();
        
        currentStage.close();
        
        });
         btnBatal.setOnAction(eh ->{
        Stage currentStage = (Stage)((Node)eh.getSource()).getScene().getWindow();
        currentStage.close();
        });
    }
    protected void DatabaseUpdate(){
    DaoAdmin dao = new DaoAdmin();
        dao.UpdateList(nama, stok, jenis, harga, waktu, berat, pathGambar, idMakanan);}
    protected void updateToDatabase(){
        System.out.println("id makanan di edit list makanan "+idMakanan);
        nama = txtNama.getText();
        stok = Integer.parseInt(txtStok.getText());
        harga = Integer.parseInt(txtHarga.getText());
        
       
        
    
        System.out.println(nama + stok + harga + jenis + waktu+ berat);}
    protected void addItems(){
        cbJenisMakanan.getItems().addAll("vegetables","drinks","baked","heavy food");
        cbBerat.getItems().addAll("Liter","buah","ml","kg");
        cbExperied.getItems().addAll("2 jam dari sekarang","3 jam dari sekarang","4 jam dari sekarang","5 jam dari sekarang","6 jam dari sekarang");
       
        
        cbJenisMakanan.setOnAction(eh ->{
            jenis = cbJenisMakanan.getSelectionModel().getSelectedItem().toString();
             idKategori = cbJenisMakanan.getSelectionModel().getSelectedIndex()+1;
        });
        cbBerat.setOnAction(eh ->{
        berat = cbBerat.getSelectionModel().getSelectedItem().toString();
        });
        cbExperied.setOnAction(eh ->{
        int waktuTambah = cbExperied.getSelectionModel().getSelectedIndex()+2;
        waktu = LocalTime.now().withNano(0).plusHours(waktuTambah)
                .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        });
        System.out.println(nama + stok + harga + jenis + waktu+ berat);
        
    } 
    
    
protected void editFotoMakanan(){
File desktopDir = new File(System.getProperty("user.home"), "Desktop");
if(!desktopDir.exists() || !desktopDir.isDirectory()) {
    desktopDir = new File(System.getProperty("user.home")); 
}

FileChooser fileChooser = new FileChooser();
fileChooser.setTitle("Upload new profile image");
fileChooser.setInitialDirectory(desktopDir);
fileChooser.getExtensionFilters().addAll(
    new FileChooser.ExtensionFilter("PNG Image", "*.png"),
    new FileChooser.ExtensionFilter("All image files", "*.jpg", "*.png")
);

Stage stage = new Stage(); 
File selectedFile = fileChooser.showOpenDialog(stage);

if(selectedFile != null){
    pathGambar = selectedFile.getAbsolutePath();
    System.out.println("selected file "+ pathGambar);
    try{
    FileInputStream input = new FileInputStream(selectedFile.getAbsolutePath());
    Image originalImage = new Image(input);
    
    imageControll square = new imageControll();
        // 2. Potong bagian tengah agar menjadi kotak (1:1)
        Image squareImg = square.getSquareImage(originalImage);

        // 3. Masukkan ke lingkaran
        
        Gambar.setFill(new ImagePattern(squareImg));
        Gambar.setVisible(true);
        path.setVisible(true);
        path.setText(pathGambar);
        lblKeterangan.setVisible(false);
        lblKeterangan.setVisible(false);
    }catch(Exception e){
        e.printStackTrace();
    }
    
}
}
   public void setData(Makanan makanan){
       this.idMakanan = makanan.getIdMakanan();
       System.out.println("id makanan di edit list makanan "+idMakanan);
       
   } 
}
