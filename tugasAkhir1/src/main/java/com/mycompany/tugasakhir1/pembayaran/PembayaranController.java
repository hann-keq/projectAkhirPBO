/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1.pembayaran;

import com.github.sarxos.webcam.Webcam;
import com.google.zxing.WriterException;
import com.jfoenix.controls.JFXButton;
import com.mycompany.tugasakhir1.DaoKonsumen;
import com.mycompany.tugasakhir1.Makanan;
import com.mycompany.tugasakhir1.ScanBarcode;
import com.mycompany.tugasakhir1.SceneChanger;
import com.mycompany.tugasakhir1.cartController.Cart;
import com.mycompany.tugasakhir1.cartController.CartController;
import com.mycompany.tugasakhir1.cartController.cartModel;
import com.mycompany.tugasakhir1.qr;
import com.mycompany.tugasakhir1.sesiAktif;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.shape.Circle;

/**
 *
 * @author hanif
 */
public class PembayaranController extends CartController implements Initializable {
    DaoKonsumen dao = new DaoKonsumen();
   
    int idPesanan;
    ScanBarcode scan = new ScanBarcode();
    @FXML private JFXButton btnBatal;
@FXML private ImageView ImageCode;
@FXML private JFXButton btnSelesai;
@FXML private Label lblPath;

    @Override
    public void initialize(URL url,ResourceBundle rb){
       listener();


    try {
        generateQr();
    } catch (IOException ex) {
        System.getLogger(PembayaranController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
    } catch (WriterException ex) {
        System.getLogger(PembayaranController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
    }
   update();
   
    }
    
   
    @Override
    protected void tampilkanData(){
        }
    @Override
    protected void listener(){
    btnSelesai.setOnAction(eh -> {SceneChanger.pindahScene("/com/mycompany/tugasakhir1/PesananKonsumen/Halaman Pesanan Saya.fxml", eh);
        
    });}


@Override 
protected void update(){
super.update();
 }
private void generateQr() throws IOException, WriterException{
    for(Makanan item :dao.getIdPesanan(sesiAktif.getInstance().getIdKonsumen())){
    idPesanan = item.getIdPesanan();
    }
qr qr = new qr();
try{
WritableImage image = qr.generateBarcode(String.valueOf(idPesanan), 298, 1000);
ImageCode.setImage(image);

String simpan = qr.saveQR(image);
lblPath.setText("Barcode tersimpan di "+simpan);

}catch(Exception e){
e.printStackTrace();
}


}

}