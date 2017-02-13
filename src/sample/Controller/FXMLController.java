package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class FXMLController {
    @FXML
    private Button btnOpenFile;

    public FXMLController(){
    }
    @FXML
    private void clickFichierImporter() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Importer");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("DOT", "*.dot"),
                new FileChooser.ExtensionFilter("GRAPHML", "*.graphml")
        );
        fileChooser.showOpenDialog(null);
    }
    @FXML
    private void clickFichierExporter() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Exporter");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("DOT", "*.dot"),
                new FileChooser.ExtensionFilter("GRAPHML", "*.graphml")
        );
        fileChooser.showSaveDialog(null);

    }

    @FXML
    private void clicFichierEnregistrer() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("DOT", "*.dot"),
                new FileChooser.ExtensionFilter("GRAPHML", "*.graphml")
        );
        fileChooser.showSaveDialog(null);
    }
}
