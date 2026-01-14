/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1;

import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 *
 * @author hanif
 */
public class imageControll {
    public Image getSquareImage(Image img) {
        double width = img.getWidth();
        double height = img.getHeight();
        
        // Tentukan dimensi kotak (ambil sisi terpendek)
        double side = Math.min(width, height);

        // Hitung posisi koordinat X dan Y agar potongannya tepat di tengah
        double x = (width - side) / 2;
        double y = (height - side) / 2;

        // Gunakan ImageView bantuan untuk memotong (cropping)
        ImageView imageView = new ImageView(img);
        Rectangle2D viewportRect = new Rectangle2D(x, y, side, side);
        imageView.setViewport(viewportRect);

        // Ambil snapshot dari hasil crop tersebut
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT); 
        
        return imageView.snapshot(params, null);
    }
}
