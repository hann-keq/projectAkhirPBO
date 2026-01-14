/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tugasakhir1.kelolaMakanan;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleNode;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.mycompany.tugasakhir1.Admin.DaoAdmin;
import com.mycompany.tugasakhir1.Admin.pesanan;
import com.mycompany.tugasakhir1.AdminController.EditListMakananController;
import com.mycompany.tugasakhir1.AlertAlert;
import com.mycompany.tugasakhir1.Controller;
import com.mycompany.tugasakhir1.Makanan;
import com.mycompany.tugasakhir1.SceneChanger;
import com.mycompany.tugasakhir1.imageControll;
import com.mycompany.tugasakhir1.sesiAktif;
import java.io.FileInputStream;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hanif
 */
public class KelolaMakananController  implements Initializable {
@FXML private JFXTreeTableView<Makanan> table;
@FXML private TreeTableColumn<Makanan , String> colNama,colEdit;
@FXML private TreeTableColumn<Makanan,String>colKategori;
@FXML private TreeTableColumn<Makanan,Integer>colHarga,colStok;
@FXML private JFXToggleNode btnDashboard;
@FXML private Button btnTambah;

private final int idAdminAktif =sesiAktif.getInstance().getIdAdmin();

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnTambah.setOnAction(eh ->{
             try{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TambahMakanan.fxml"));
                    Parent root = loader.load();
                     EditListMakananController ctrl = loader.getController();
                
                     
                     
                     Stage stage = new Stage();
                     stage.setScene(new Scene(root));
                     stage.setOnHidden(event ->{
                     loadData();
                     });
                     stage.setTitle("Tambah menu baru");
                     stage.show();
                     
                    
                }catch(Exception e){
                e.printStackTrace();
                }
            } );
        btnDashboard.setOnAction(eh -> SceneChanger.pindahScene("/fxml/DashboardAdmin.fxml", eh));
        loadData();
    } protected void loadData(){
        DaoAdmin dao = new DaoAdmin();
    List<Makanan> hasilData = dao.ambilMakananAdmin(idAdminAktif);
        colNama.setCellValueFactory(clbck -> {
            String nama = clbck.getValue().getValue().getNamaMakanan();
            System.out.println("nama "+nama);
            System.out.println("id admin aktif "+idAdminAktif);
                    return new ReadOnlyStringWrapper(nama);
});     
        
        colNama.setCellFactory(columnn -> new TreeTableCell<Makanan,String>(){
        @Override
        protected void updateItem(String item,boolean empty){
            super.updateItem(item, empty);
            
            if(empty || item == null){
                setGraphic(null);
                setText(null);
            
            }else{
                Makanan data = getTreeTableView().getTreeItem(getIndex()).getValue();
                String path = data.getPath();
                
                if(path != null && !path.isEmpty()){
                try{
                FileInputStream input = new FileInputStream(path);
                Image original = new Image(input);
                
                imageControll square = new imageControll();
                Image squareImage = square.getSquareImage(original);
                Rectangle r = new Rectangle();
               r.setWidth(50);
               r.setHeight(50);
               r.setArcHeight(10);
               r.setArcWidth(10);
               
               r.setFill(new ImagePattern(squareImage));
                 Label lblNama = new Label(item);
                
            HBox container = new HBox(50,r,lblNama);
            container.setPadding(new Insets(10,0,0,50));
            container.setAlignment(Pos.CENTER_LEFT);
            
            setGraphic(container);
                        }catch(Exception e){
                        e.printStackTrace();
                        }
               
               
                }
                
                
                
            }
        }});
        colKategori.setCellValueFactory(clbck -> {
            String kategori = clbck.getValue().getValue().getJenisMakanan();
                return new ReadOnlyStringWrapper(kategori);
                        });
        colHarga.setCellValueFactory(clbck -> {
            int harga = clbck.getValue().getValue().getHarga();
                return new ReadOnlyIntegerWrapper(harga).asObject();
        });
        colStok.setCellValueFactory(clbck -> {
            int stok = clbck.getValue().getValue().getStok();
                return new ReadOnlyIntegerWrapper(stok).asObject();
        });
        colEdit.setCellFactory(clbck -> new TreeTableCell<Makanan,String>(){
            private final JFXButton btnEdit = new JFXButton("Edit");
            private final JFXButton btnHapus = new JFXButton ("Hapus");
            private final HBox container = new HBox(10,btnEdit,btnHapus);
            @Override
            protected void updateItem(String item,boolean empty){
                super.updateItem(item, empty);
                    if(empty){
                        setGraphic(null);
                    }else{
                    btnEdit.setStyle("-fx-background-color: #FFC107; -fx-text-fill:white;");
                    btnHapus.setStyle("-fx-backgrond-color: #F44366; -fx-text-fill:white;");
                    
                    btnHapus.setOnAction(eh ->{
                        Makanan data = getTreeTableView().getTreeItem(getIndex()).getValue();
                        
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Hapus makanan?");
                        alert.setHeaderText("Konfirmasi penghapusan makanan dari daftar");
                        alert.setContentText("Apakah anda yakin untuk mengapus "+data.getNamaMakanan()+" dari list?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if(result.isPresent() && result.get() == ButtonType.OK){
                            dao.DeleteFromList(data.getIdMakanan());
                        }else{
                        return;
                        }
                        
                        
                        
                        System.out.println("data yang dihapus "+data);
                        System.out.println("Data dihapus "+data.getNamaMakanan());
                    });
                    btnEdit.setOnAction(eh -> {
                        Makanan data = getTreeTableView().getTreeItem(getIndex()).getValue();
                        try{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/editListMakanan.fxml"));
                    Parent root = loader.load();
                     EditListMakananController ctrl = loader.getController();
                
                     ctrl.setData(data);
                     
                     Stage stage = new Stage();
                     stage.setScene(new Scene(root));
                     stage.setOnHidden(event ->{
                     loadData();
                     });
                     stage.setTitle("Edit data Makanan "+data.getNamaMakanan()+String.valueOf(data.getIdMakanan()));
                     stage.show();
                     
                    
                }catch(Exception e){
                e.printStackTrace();
                }
                    });
                    
                    container.setAlignment(Pos.CENTER);
                    setGraphic(container);
                    }
            }
        }
        );
        ObservableList<Makanan> listPesanan = FXCollections.observableArrayList(hasilData);
        
        
        final TreeItem<Makanan> root = new RecursiveTreeItem<>(listPesanan,RecursiveTreeObject::getChildren);
        table.setRoot(root);
        table.setShowRoot(false);
    }
    
    
}
