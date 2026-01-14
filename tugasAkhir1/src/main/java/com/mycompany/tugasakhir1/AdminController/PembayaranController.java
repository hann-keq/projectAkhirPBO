/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tugasakhir1.AdminController;

import com.jfoenix.controls.JFXButton;
import com.mycompany.tugasakhir1.Admin.DaoAdmin;
import com.mycompany.tugasakhir1.Admin.pesanan;
import com.mycompany.tugasakhir1.cartController.CartController;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author hanif
 */
public class PembayaranController  implements Initializable {
protected List<pesanan> dataAsli;
protected List<pesanan> dataTampil;
private int idPesanan;
@FXML private Label lblId,lblRincian,lblTotal;
@FXML private JFXButton btnKonfirmasi;

private final DaoAdmin dao = new DaoAdmin();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // loadData();
       // fetchData();
        // TODO
        btnKonfirmasi.setOnAction(eh ->{
            dao.UpdateStatus(idPesanan);
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pemberitahuan");
        alert.setHeaderText("Penjualan selesai");
        alert.setContentText("Pesanan konsumen pada no pesanan "+idPesanan+"telah diselesaikan");
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.isPresent() && result.get() == ButtonType.OK){
        return;
        }});
    }    
    public void setUpData(String hasil){
        idPesanan = Integer.parseInt(hasil);
        System.out.println("idPesanan yang dikirim "+idPesanan);
        
        dataAsli = dao.finishOrder(idPesanan);
        if (dataAsli == null || dataAsli.isEmpty()) {
            lblRincian.setText("Data Pesanan ID #" + idPesanan + " Tidak Ditemukan.");
            return;}
    dataTampil = new ArrayList<>(dataAsli);
    
       StringBuilder sb = new StringBuilder();
    for (pesanan item : dataTampil){
        sb.append(item.getNamaMakanan()).append(" x ").append(item.getQuantity())
                .append("         ").append(item.getHargaSatuan()).append("\n");
        lblRincian.setText(sb.toString());
        lblTotal.setText("Total harga barang = "+String.valueOf(item.getTotal()));
        lblId.setText("id terdeteksi = "+String.valueOf(idPesanan));
        }
    
    }
    private void loadData(){
    
    }
    private void fetchData(){
     
    }
}
