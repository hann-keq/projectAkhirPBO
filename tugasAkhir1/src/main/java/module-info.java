module com.mycompany.tugasakhir1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    requires com.jfoenix;
    requires com.gluonhq.attach.util;
    requires com.gluonhq.charm.glisten;
    requires javafx.graphics;
 

    opens com.mycompany.tugasakhir1 to javafx.fxml;
    opens com.mycompany.tugasakhir1.WelcomeScreen to javafx.fxml;
    opens com.mycompany.tugasakhir1.Home to javafx.fxml;
    opens com.mycompany.tugasakhir1.Location to javafx.fxml;
    opens com.mycompany.tugasakhir1.Profile to javafx.fxml;
    exports com.mycompany.tugasakhir1;
    
}
