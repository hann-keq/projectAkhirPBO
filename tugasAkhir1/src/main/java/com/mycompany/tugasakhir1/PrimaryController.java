package com.mycompany.tugasakhir1;

import java.io.IOException;
import javafx.fxml.FXML;
import java.sql.Connection;
public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
