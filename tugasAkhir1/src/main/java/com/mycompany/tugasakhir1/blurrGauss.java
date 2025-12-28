package com.mycompany.tugasakhir1;


import com.mycompany.tugasakhir1.App;
import com.mycompany.tugasakhir1.Controller;
import java.io.IOException;
import javafx.scene.effect.BoxBlur;
import javafx.scene.shape.Ellipse;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hanif
 */
public class blurrGauss {
  
    public static void blurr(int height,int width,Ellipse shape){
        if(shape == null) {
        System.out.println(shape+" masih null!");
        return;
    }
    try{
        BoxBlur blur = new BoxBlur();
    blur.setHeight(height);
    blur.setWidth(width);
    shape.setEffect(blur);
        System.out.println(shape+"blur berhasil");
    }catch(Exception e){
        e.printStackTrace();
    }
   }

}
