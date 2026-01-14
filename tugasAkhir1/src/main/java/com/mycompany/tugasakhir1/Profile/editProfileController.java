/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1.Profile;

import com.jfoenix.controls.JFXButton;
import com.mycompany.tugasakhir1.DaoKonsumen;
import com.mycompany.tugasakhir1.imageControll;
import com.mycompany.tugasakhir1.sesiAktif;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author hanif
 */
public class editProfileController implements Initializable {
@FXML private Circle profile;
@FXML private JFXButton btnSimpan,btnBatal;
@FXML private TextField txtNama,txtEmail,txtTlp,txtAlamat;
private final DaoKonsumen dao = new DaoKonsumen();
private String path;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        profile.setOnMouseClicked(eh -> {
            uploadData();
        });
        
        
          try{
    FileInputStream input = new FileInputStream(sesiAktif.getInstance().getPathFile());
    Image originial = new Image(input);
    
    imageControll square = new imageControll();
    Image squareImg = square.getSquareImage(originial);
    
    profile.setFill(new ImagePattern(squareImg));
    }catch(Exception e){
    e.printStackTrace();
    }
          Listener();
    }
    
    
    private void Listener(){
        btnSimpan.setOnAction(eh ->{
            inputToDatabase();
            Stage currentStage = (Stage)((Node)eh.getSource()).getScene().getWindow();
            currentStage.close();
        });
        btnBatal.setOnAction(eh ->{
            Stage currentStage = (Stage)((Node)eh.getSource()).getScene().getWindow();
            currentStage.close();
        });
    }
    
    private void uploadData() {
  File desktopDir = new File(System.getProperty("user.home"), "Desktop");
if(!desktopDir.exists() || !desktopDir.isDirectory()) {
    desktopDir = new File(System.getProperty("user.home")); // fallback ke home
}

FileChooser fileChooser = new FileChooser();
fileChooser.setTitle("Upload new profile image");
fileChooser.setInitialDirectory(desktopDir);
fileChooser.getExtensionFilters().addAll(
    new FileChooser.ExtensionFilter("PNG Image", "*.png"),
    new FileChooser.ExtensionFilter("All image files", "*.jpg", "*.png")
);

Stage stage = new Stage(); // stage sementara
File selectedFile = fileChooser.showOpenDialog(stage);

if(selectedFile != null) {
    System.out.println("File selected: " + selectedFile.getAbsolutePath());
    path = selectedFile.getAbsolutePath();
    try{
    FileInputStream input = new FileInputStream(selectedFile.getAbsolutePath());
    Image originalImage = new Image(input);
    
    imageControll square = new imageControll();
        // 2. Potong bagian tengah agar menjadi kotak (1:1)
        Image squareImg = square.getSquareImage(originalImage);

        // 3. Masukkan ke lingkaran
        
        profile.setFill(new ImagePattern(squareImg));
    }catch(Exception e){
        e.printStackTrace();
    }
} else {
    System.out.println("No file selected");
}

}
    private void inputToDatabase(){
        if(txtNama.getText().isEmpty() || txtAlamat.getText().isEmpty() || txtEmail.getText().isEmpty()||
               txtTlp.getText().isEmpty()){
            System.out.println("field tidak boleh ada yang kosong");
            return;
        }
        dao.Updateprofile(txtNama.getText(), txtAlamat.getText(), txtEmail.getText(),
                Long.parseLong(txtTlp.getText()),path );
        }
    private void loadData(){
    }
}

