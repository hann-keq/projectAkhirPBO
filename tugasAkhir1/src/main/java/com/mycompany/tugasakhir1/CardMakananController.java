/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
/**
 *
 * @author hanif
 */
public class CardMakananController implements Initializable {
    private DaoKonsumen crud;
    private Consumer<Makanan> onClick;
    private Makanan makanan;
    private String path;
     @FXML private Rectangle myImage;
     private int iddMakanan,idToko;
    @FXML private Label lblNama,lblToko,lblJarak,lblHarga,lblLokasi,lblBerat,lblTanggal,idMakanan;
    @FXML private Button BtnPilih;
    
@Override
public void initialize(URL url ,ResourceBundle rb){
    
     
    buttonClick();
    }


  
    
    public void onClick (Consumer <Makanan> onClick){
        this.onClick = onClick;
    }
    
    
    public void buttonClick(){
        BtnPilih.setOnAction(eh ->{
        if(onClick != null){
            System.out.println("pnClick" + onClick);
        
            addToCart();
            onClick.accept(makanan);
        }});
    }
private void addToCart(){
crud = new DaoKonsumen();


int idUser = sesiAktif.getInstance().getIdKonsumen();


    System.out.println("idMakanan aktif"+idMakanan);
crud.addToCart(idUser,iddMakanan,idToko);
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
       this.iddMakanan = makanan.getIdMakanan();
       this.idToko = makanan.getIdToko();
       path = makanan.getPath();
        try{
    FileInputStream input = new FileInputStream(path);
    Image originalImage = new Image(input);
    
    imageControll square = new imageControll();
        // 2. Potong bagian tengah agar menjadi kotak (1:1)
        Image squareImg = square.getSquareImage(originalImage);

        // 3. Masukkan ke lingkaran
        
        myImage.setFill(new ImagePattern(squareImg));
    }catch(Exception e){
        e.printStackTrace();
    }
        
    }
    
    
}


