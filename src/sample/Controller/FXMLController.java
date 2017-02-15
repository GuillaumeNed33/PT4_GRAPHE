package sample.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

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
    private void clickFichierEnregistrer() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("DOT", "*.dot"),
                new FileChooser.ExtensionFilter("GRAPHML", "*.graphml")
        );
        fileChooser.showSaveDialog(null);
    }

}
