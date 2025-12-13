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
    exports com.mycompany.tugasakhir1;
}
