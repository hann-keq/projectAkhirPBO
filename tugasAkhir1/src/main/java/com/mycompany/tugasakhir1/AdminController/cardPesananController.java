/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1.AdminController;

import com.jfoenix.controls.JFXButton;
import com.mycompany.tugasakhir1.Admin.DaoAdmin;
import com.mycompany.tugasakhir1.Admin.pesanan;
import com.mycompany.tugasakhir1.cartController.Cart;
import com.mycompany.tugasakhir1.cartController.cartModel;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author hanif
 */
public class cardPesananController implements Initializable {
    @FXML JFXButton btnTerima,btnTolak;
    @FXML private VBox vBoxMenu; 
    public int idPesanan ;
    String status;
    private DaoAdmin dao = new DaoAdmin();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnTerima.setOnAction(eh -> {
            AcceptOrder();
        });
       
    }
    
    
    @FXML private Label lblNama,lblNoPesanan,lblHari,lblWaktu,lblHarga,lblLokasi,lblNamaPelangggan,lblStatus;
    public void setData(pesanan orderan){
        lblNama.setText(orderan.getNamaMakanan());
        lblNoPesanan.setText(String.valueOf(orderan.getIdPesanan()));
        lblHari.setText(orderan.getHariPengambilan());
        lblWaktu.setText(orderan.getWaktuPengambilan());
        lblHarga.setText(String.valueOf(orderan.getTotal()));
        lblLokasi.setText(orderan.getAlamatToko());
        lblNamaPelangggan.setText(orderan.getNamaKonsumen());
        lblStatus.setText(orderan.getStatus());
        idPesanan = orderan.getIdPesanan();
        status = orderan.getStatus();
        if(status.equalsIgnoreCase("diterima")||status.equalsIgnoreCase("selesai")){
        vBoxMenu.setVisible(false);
        }
        
    }
    private void AcceptOrder(){
        int updateSuccess = dao.accpetOrder(idPesanan);
        System.out.println(updateSuccess);
        if(updateSuccess > 0){
        dao.setStock(idPesanan);
        }
        
        }
    
    
}
