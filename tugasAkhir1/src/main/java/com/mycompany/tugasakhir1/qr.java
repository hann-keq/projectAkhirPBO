/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;








/**
 *
 * @author hanif
 */
public class qr {
    WritableImage image;
    public  WritableImage generateBarcode (String data,
            int height,int width)throws IOException,WriterException{
        
    Code128Writer writer = new Code128Writer();
    
    BitMatrix matrix = writer.encode(data,
            BarcodeFormat.CODE_128, width, height);
    image = new WritableImage(width,height);
    PixelWriter pw = image.getPixelWriter();
    
    for (int x = 0;x<width;x++){
        for(int y = 0; y<height;y++){
            pw.setColor(x, y, matrix.get(x, y) ? Color.BLACK : Color.WHITE
            );
        }
    }return image;
    }
    public String saveQR(WritableImage image){
    String desktop = System.getProperty("user.home") + "/Desktop";
File file = new File("G:" + "/barcode.png");
BufferedImage buffered = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(buffered, "png", file);
            return file.getAbsolutePath();
        } catch (IOException ex) {
            System.getLogger(qr.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
     return null;   
    }
}
 

