package com.mycompany.tugasakhir1;


import com.mycompany.tugasakhir1.App;
import com.mycompany.tugasakhir1.Controller;
import java.io.IOException;
import javafx.scene.effect.BoxBlur;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hanif
 */
public class blurrGauss {
  
    public static void blurr(String destination,int height,int width) throws IOException{
    Controller c = (Controller) App.setRoot(destination);

    BoxBlur blur = new BoxBlur();
    blur.setHeight(height);
    blur.setWidth(width);
    c.getEllipse().setEffect(blur);
    c.getEllipse2().setEffect(blur);
    
  
            }

    
}
