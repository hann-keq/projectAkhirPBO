package com.mycompany.tugasakhir1;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hanif
 */
public class Animasi {
    

    public static void AnimasiTranslate(Node node, double X ,double Y,int duration,boolean reverse,int ulang){
        TranslateTransition tt = new TranslateTransition();
        tt.setNode(node);
        tt.setDuration(Duration.millis(duration));
        tt.setByX(X);
        tt.setByY(Y);
        tt.setAutoReverse(reverse);
        
        tt.setCycleCount(ulang);
        tt.play();
        System.out.println("animasi di play");
    }
    public static void AnimasiTranslate(Node node,double X,double Y,int duration,boolean reverse){
    TranslateTransition tt = new TranslateTransition();
    tt.setNode(node);
    tt.setByX(X);
    tt.setByY(Y);
    tt.setDuration(Duration.millis(duration));
    tt.setAutoReverse(reverse);
    tt.setCycleCount(TranslateTransition.INDEFINITE);
    tt.play();
     System.out.println("animasi di play");
    }
    
    
    public static void AnimasiRotate(Node node, int angle,int duration,int ulang){
        RotateTransition rt = new RotateTransition();
        rt.setNode(node);
        rt.setDuration(Duration.millis(duration));
        rt.setByAngle(angle);
        rt.setCycleCount(ulang);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.setAxis(Rotate.Z_AXIS);
        rt.play();
    }
    


}
