module com.mycompany.tugasakhir1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    requires com.jfoenix;
    requires com.gluonhq.attach.util;
    requires com.gluonhq.charm.glisten;
    requires javafx.graphics;
    requires java.desktop;
    requires javafx.swing;
   
 

    opens com.mycompany.tugasakhir1 to javafx.fxml;
    opens com.mycompany.tugasakhir1.WelcomeScreen to javafx.fxml;
    opens com.mycompany.tugasakhir1.Home to javafx.fxml;
    opens com.mycompany.tugasakhir1.Location to javafx.fxml;
    opens com.mycompany.tugasakhir1.Profile to javafx.fxml;
    opens com.mycompany.tugasakhir1.AdminController to javafx.fxml;
    opens com.mycompany.tugasakhir1.Dashboard to javafx.fxml;
    opens com.mycompany.tugasakhir1.kelolaMakanan to javafx.fxml;
    opens com.mycompany.tugasakhir1.cartController to javafx.fxml;
    opens reports;
    opens com.mycompany.tugasakhir1.pembayaran to javafx.fxml;
    exports com.mycompany.tugasakhir1;
    requires com.maxmind.geoip2;
    requires javafx.web;
    requires com.google.zxing;
    requires com.google.zxing.javase;
    requires webcam.capture;
    requires javafx.swingEmpty;
    requires jasperreports;
    
   

    
}
