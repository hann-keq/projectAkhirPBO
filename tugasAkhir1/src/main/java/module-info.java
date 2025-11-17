module com.mycompany.tugasakhir1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.mycompany.tugasakhir1 to javafx.fxml;
    exports com.mycompany.tugasakhir1;
}
