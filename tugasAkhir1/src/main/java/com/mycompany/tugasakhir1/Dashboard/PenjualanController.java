/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tugasakhir1.Dashboard;

import com.mycompany.tugasakhir1.Admin.DaoAdmin;
import com.mycompany.tugasakhir1.Admin.DataAdmin;
import com.mycompany.tugasakhir1.Admin.pesanan;
import com.mycompany.tugasakhir1.sesiAktif;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author hanif
 */
public class PenjualanController extends DashboardAdminController implements Initializable {
private final DaoAdmin dao = new DaoAdmin();
private final int idAdminAktif =sesiAktif.getInstance().getIdAdmin();
@FXML private Label lblSaldo;
private DataAdmin data;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDataByFilter("Selesai");
        setupAndListener();
        loadDataSaldo();
    }    
    @Override
    protected void loadData(){}
    
    
    
    @Override 
    protected void loadDataByFilter(String status){
        dataAsli1 = dao.ambilPesanan(idAdminAktif, status);
    dataTampil = new ArrayList<>(dataAsli1);
     tampilkanData();
    }
    
    private void loadDataSaldo(){
    
    data = dao.getSaldo(idAdminAktif);
    lblSaldo.setText(String.valueOf(data.getSaldo()));
        System.out.println(data.getSaldo());
    
    }
}
