/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.util.Duration;


/**
 *
 * @author hanif
 */
public class AlertAlert {
    public static void AlertTemplate(AlertType type,String message,ButtonType button,int duration ){
       Alert alert = new Alert(type,message,button);
               alert.show();
               
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(duration),
                ae -> alert.close())
        );
        timeline.play();
    }
    public static void AlertTemplate(AlertType type,String message,ButtonType button){
       AlertTemplate(type,message,button,10000);
    }
    public static void AlertTemplate(AlertType type,String message){
        AlertTemplate(type,message,ButtonType.OK);
        
    }
    public static void AlertTemplate(String message,ButtonType button){
        AlertTemplate(AlertType.WARNING,message,button);
    }
    public static void AlertTemplate(String message){
        AlertTemplate(AlertType.WARNING,message,ButtonType.OK);
    }
}
