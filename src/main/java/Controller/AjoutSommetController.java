package Controller;

import Model.Forme_Sommet;
import Model.Graphe;
import Model.Sommet;
import com.sun.glass.ui.Size;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.io.IOException;


public class AjoutSommetController extends FXMLController {
    private SommetController sommetController;
    @FXML
    private ComboBox comboBoxForme;
    AjoutSommetController(Graphe grapheModel, View.Graphe grapheView) throws IOException {
        super();
        this.grapheModel = grapheModel;
        this.grapheView = grapheView;
        sommetController = new SommetController();
        if (this.grapheModel != null) {
            FXMLLoader fxmlLoaderPopUp = new FXMLLoader(getClass().getResource("/fxml/AjoutSommet.fxml"));
            popUpWindow.setTitle("Ajouter Sommet");
            if (popUpWindow.getScene() == null) {
                fxmlLoaderPopUp.setRoot(this);
                fxmlLoaderPopUp.setController(this);
                popUpWindow.setScene(new Scene((Parent) fxmlLoaderPopUp.load()));
            }
            comboBoxForme.setItems(sommetController.formes);
            comboBoxForme.setValue(Forme_Sommet.Cercle);

            popUpWindow.show();
        }
    }

    @FXML
    private TextField tailleSommet, positionSommet, tagSommet;
    @FXML
    ColorPicker colorPickerSommet;
    @FXML
    private Label erreurAjoutSommet;
    @FXML public void creerSommet() {

        Size tailleSommet = sommetController.déterminationTailleRentrerParUtilisateur(this.tailleSommet, this.erreurAjoutSommet);

        Pair<Float, Float> coordSommet = null;

        if (tailleSommet != null) {
            coordSommet = sommetController.déterminationPositionRentrerParUtilisateur(this.positionSommet, this.erreurAjoutSommet);
        }

        if (tailleSommet != null && coordSommet != null) {

            String tag = tagSommet.getText();

            Sommet sommet = new Sommet(tag, coordSommet.getKey(), coordSommet.getValue());

            Color couleur = colorPickerSommet.getValue();
            String forme = comboBoxForme.getValue().toString();


            sommet.setCouleur(couleur);
            sommet.setForme(forme);

            if (!grapheModel.ajouterSommet(sommet, new Size((int)popUpWindow.getMaxWidth(), (int)popUpWindow.getMaxHeight()))) {
                erreurAjoutSommet.setText("Erreur - Sommet existant en cette coordonnée.");
            }
            else {
                popUpWindow.close();
                grapheView.getSommets().add(new View.Sommet(sommet.getId(), tag, Forme_Sommet.getFormeByText(forme),sommet.getX(), sommet.getY()));
                grapheView.getScrollPane().updateScrollPane(grapheView.getCanvas());
            }
        }
    }

    @FXML public void fermerCreerSommet() {
        popUpWindow.close();
    }
}
