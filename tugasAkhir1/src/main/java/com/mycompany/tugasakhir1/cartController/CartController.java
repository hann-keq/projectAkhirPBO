/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tugasakhir1.cartController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleNode;
import com.mycompany.tugasakhir1.DaoKonsumen;
import com.mycompany.tugasakhir1.SceneChanger;
import com.mycompany.tugasakhir1.imageControll;
import com.mycompany.tugasakhir1.sesiAktif;
import java.io.FileInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author hanif
 */
class Basecomponent{
    public abstract static class component{
    @FXML protected FlowPane conKeranjang,conRingkasan;
@FXML protected Label lblTest,lblTest2,lblTotal;

@FXML protected ListView<Cart> listView;} }
public class CartController extends Basecomponent.component implements Initializable {

private final DaoKonsumen dao = new DaoKonsumen();
Map<Integer, List<Cart>> cartsPerToko = new HashMap<>();
private int idPelanggan;
  @FXML private Circle goToProfile;
   @FXML private JFXToggleNode btnJelajahi,btnPesanan;

protected int total = 0;
protected int idToko ;
private List<Cart> dataAsli;
private List<Cart> dataTampil;
private List<String>keranjang = new ArrayList<>();
private Cart cart;

@FXML private Button btnKembali,btnBayar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try{
    FileInputStream input = new FileInputStream(sesiAktif.getInstance().getPathFile());
    Image originial = new Image(input);
    
    imageControll square = new imageControll();
    Image squareImg = square.getSquareImage(originial);
    
    goToProfile.setFill(new ImagePattern(squareImg));
    }catch(Exception e){
    e.printStackTrace();
    }
        // TODO
        System.out.println("cartController berjalan");
        loadData();
        listener();
        cartModel.getInstance().getSelectedItem().addListener(
                (ListChangeListener<Cart>)c -> update());
       
    }    
    protected void listener(){
        btnKembali.setOnAction(eh -> SceneChanger.pindahScene("/com/mycompany/tugasakhir1/Home.fxml", eh));
        btnBayar.setOnAction(eh ->  Checkout(eh));
        btnPesanan.setOnAction(eh ->{
   SceneChanger.pindahScene("/com/mycompany/tugasakhir1/PesananKonsumen/Halaman Pesanan Saya.fxml", eh);});
btnJelajahi.setOnAction(eh ->{
        SceneChanger.pindahScene("/com/mycompany/tugasakhir1/Home.fxml", eh);});
    }
    protected void loadData(){
        dataAsli = dao.cart(sesiAktif.getInstance().getIdKonsumen());
       
       
        
        for(Cart c : dataAsli){
        cartModel.getInstance().duplicateEraser(c);
        }
        dataTampil = new ArrayList<>(cartModel.getInstance().getSelectedItem());
        tampilkanData();
        }
    
   protected void update(){
   total = 0;
   StringBuilder sb = new StringBuilder();
   for(Cart c : cartModel.getInstance().getSelectedItem()){
    sb.append(c.getNamaMakanan())
    .append(" x")
    .append(c.getJumlah())
    .append("      ")
    .append(c.getHarga())
    .append("\n");
   }
       System.out.println("selected item di cart "+cartModel.getInstance().getSelectedItem());

       System.out.println(sb.toString());
      lblTest.setText(sb.toString());
      for(Cart c : cartModel.getInstance().getSelectedItem()){
         
          total += c.getHarga()*c.getJumlah();
          
          System.out.println("harga "+c.getHarga());
          System.out.println("jumlah "+c.getJumlah());
          System.out.println("total "+total);
      lblTotal.setText(String.valueOf(total));
      }
     
    }
    
    protected void tampilkanData(){
        conKeranjang.getChildren().clear();
        
        for(Cart item : dataTampil){
            try{
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/tugasAkhir1/cardCart.fxml"));
                Node card = loader.load();
            cardCartController ctrl = loader.getController();
            ctrl.setData(item);
            conKeranjang.getChildren().add(card);
            
            }catch(Exception e){
                e.printStackTrace();
            }}
    }
   protected void Checkout(ActionEvent event){
    System.out.println("Co jalan");
    idPelanggan = sesiAktif.getInstance().getIdKonsumen();
        LocalTime now = LocalTime.now().withNano(0);
        
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
         String sekarang = now.format(time);
        String min = now.plusHours(2).format(time);
        String max = now.plusHours(3).format(time);
        
        LocalDate date = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String tanggal = date.format(format);
        System.out.println("id toko"+idToko);
        
        for (Cart c : cartModel.getInstance().getSelectedItem()){
            cartsPerToko.computeIfAbsent(c.getIdToko(), k-> new ArrayList<>()).add(c);
                    }
        for (Map.Entry<Integer,List<Cart>> entry: cartsPerToko.entrySet()){
            int idToko = entry.getKey();
            
            System.out.println("id toko" + idToko);
            List<Cart> carts = entry.getValue();
        
        int total = 0;
        int totalQuantity = 0;
        List<Integer> cartIds = new ArrayList<>();
        for(Cart c : carts){
            total +=c.getHarga()*c.getJumlah();
            totalQuantity += c.getJumlah();
            cartIds.add(c.getIdKeranjang());
        }
            System.out.println(idToko);
        int idPesanan = dao.AddtoOrder(idToko,idPelanggan,tanggal,min,total);
        
            System.out.println(idPesanan);
        }
        
        SceneChanger.pindahScene("/com/mycompany/tugasakhir1/PesananKonsumen/CheckoutPage.fxml",event);
    }
   @FXML
    protected void showProfile(MouseEvent event) {
        try{
        FXMLLoader loader = new FXMLLoader (
            getClass().getResource("/com/mycompany/tugasAkhir1/profile.fxml"));
            Parent root = loader.load();
            Scene scene = ((Node) event.getSource()).getScene();
            scene.setRoot(root);
        }catch(Exception e){
            e.printStackTrace();
        }   
    }
}
