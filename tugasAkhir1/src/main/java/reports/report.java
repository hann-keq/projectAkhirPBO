/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reports;

import com.mycompany.tugasakhir1.AlertAlert;
import com.mycompany.tugasakhir1.Database;
import java.awt.Desktop;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.time.LocalDate;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author hanif
 */
public class report {
    
   public String createReports(String input, String output) {
    try {
        Connection conn = Database.getConnection();
        InputStream is = getClass().getResourceAsStream(input);
        
        if (is == null){ 
            Alert alert = new Alert(Alert.AlertType.WARNING,"file tidak ditemukan");
            alert.showAndWait();
            return null;
        }

        JasperReport jr = JasperCompileManager.compileReport(is);
        JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);

        // 1. Tentukan lokasi simpan yang "aman" (Folder Downloads)
        String downloadPath = System.getProperty("user.home") + "/Downloads/" + output + ".pdf";
        File file = new File(downloadPath);

        // 2. SIMPAN dulu ke file fisik
        JasperExportManager.exportReportToPdfFile(jp, file.getAbsolutePath());

        // 3. CEK apakah file benar-benar sudah tercipta
        if (file.exists()) {
            // 4. BARU BUKA (Gunakan cara ini agar MS Edge terpanggil paksa)
           try{
           ProcessBuilder pb = new ProcessBuilder("rundll32","url.dll,FileProtocolHandler", file.getAbsolutePath());
           pb.start();
           }catch(Exception e){
           e.printStackTrace();
           new Alert (Alert.AlertType.WARNING,"Tidak dapat membuka file di browser").showAndWait();
           }
        }

        return file.getAbsolutePath();

    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}
}
        
    


