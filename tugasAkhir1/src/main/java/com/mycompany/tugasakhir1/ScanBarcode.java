package com.mycompany.tugasakhir1;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.*;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javax.imageio.ImageIO;
/**
 *
 * @author hanif
 */
public class ScanBarcode {
    private volatile boolean isScan = true;
    private Webcam webcam;
    private Label txtHasilScan;
    private String hasil = null;
    
    public void closeWebCam(){
        if(webcam != null){
    isScan = false;
    webcam.close();}
    }
     public void initWebCam(){
     webcam = null;
     List <Webcam> cams = Webcam.getWebcams();

for (Webcam cam : cams) {
    if (cam.getName().contains("ACER")) {
        webcam = cam;
        break;
    }
}
if(webcam == null && !cams.isEmpty()){
webcam = cams.get(0);
}

if (webcam == null) {
    System.out.println("Webcam laptop tidak ditemukan");
    return;
}
if(webcam != null){
webcam.open();
System.out.println("Pakai webcam: " + webcam.getName());
}
else{
    System.out.println("tidak dapat menemukan web cam yang compatible");}
    
    }
    public void ScanBarcode128(Consumer <BufferedImage> imageCam,Consumer <String> hasilScan){
       
        Thread thread = new Thread(() ->{
            
            while(isScan){
                try{
                BufferedImage bf = webcam.getImage();
                if(bf != null){
                    if(imageCam != null){
                    imageCam.accept(bf);
                    }
                    LuminanceSource source = new BufferedImageLuminanceSource(bf);
                    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                    
                    // baca code 128 bisa yang lain ?? ntahlah belum tau
                    Result result = new MultiFormatReader().decode(bitmap);
                    
                    if(result != null){
                        Platform.runLater(() -> {
                        hasil = result.getText();
                        hasilScan.accept(hasil);
                            System.out.println("hasil scan = "+hasil);
                        System.out.println("Terseteksi: "+result.getText());
                    });
                    }
                }
                }catch(Exception e){
                
                }
                try{Thread.sleep(100);} catch(InterruptedException e){}
            }
        }
        );
        thread.setDaemon(true);
        thread.start();
        System.out.println("hasil sebelum di return "+hasil);
      
}
    public String ScanBarcodeFromUpload(File input){
    String hasil = null;
try{
    BufferedImage image = ImageIO.read(input);
    LuminanceSource source = new BufferedImageLuminanceSource(image);
     BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
     Result result = new MultiFormatReader().decode(bitmap);
     hasil = result.getText();

}catch(Exception e){
}return hasil;
}
}
