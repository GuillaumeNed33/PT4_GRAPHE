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

/**
 * Classe AjoutSommetController
 * permettant de gérer l'affichage de la fenêtre d'ajout d'un sommet au graphe
 */
public class AjoutSommetController extends FXMLController {

    /**
     * Représente le controller d'un sommet pour vérifier la validité de la saisie de l'utilisateir
     */
    private SommetController sommetController;

    /**
     * Représente une liste déroulante comprennant toutes les formes que peuvent prendre les sommets
     */
    @FXML private ComboBox<Forme_Sommet> comboBoxForme;

    /**
     * Représente les valeurs respectivement de la taille, de la position et de l'étiquette du nouveau sommet
     */
    @FXML private TextField tailleSommet, positionSommet, tagSommet;

    /**
     * Représente le choix de couleur pour le sommet
     */
    @FXML private ColorPicker colorPickerSommet;

    /**
     * Représente le message d'erreur à afficher dans la fenêtre
     * en cas d'erreur de saisie de l'utilisateur
     */
    @FXML private Label erreurAjoutSommet;

    /**
     * Constructeur permettant l'affichage de la fenêtre d'ajout d'un sommet au graphe
     * @param grapheModel représente le graphe du Model
     * @param grapheView représente le graphe de la View
     * @throws IOException lève une exception
     */
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

    /**
     * Fonction permettant de controler la création d'un sommet.
     * fonction ajoute le nouveau sommet dans le Model et met à jour la View
     */
    @FXML public void creerSommet() {

        Size tailleSommet = sommetController.déterminationTailleRentrerParUtilisateur(comboBoxForme.getValue(), this.tailleSommet, this.erreurAjoutSommet);

        Pair<Float, Float> coordSommet = null;

        if (tailleSommet != null) {
            coordSommet = sommetController.déterminationPositionRentrerParUtilisateur(this.positionSommet, this.erreurAjoutSommet);
        }

        if (tailleSommet != null && coordSommet != null) {

            String tag = tagSommet.getText().trim();

            Sommet sommet = new Sommet(tag, coordSommet.getKey(), coordSommet.getValue());

            Color couleur = colorPickerSommet.getValue();
            String forme = comboBoxForme.getValue().toString();

            sommet.setCouleur(couleur);
            sommet.setTaille(tailleSommet);
            sommet.setForme(forme);

            if (!grapheModel.ajouterSommet(sommet, new Size((int)popUpWindow.getMaxWidth(), (int)popUpWindow.getMaxHeight()))) {
                erreurAjoutSommet.setText("Erreur - Sommet existant en cette coordonnée.");
            }
            else {
                popUpWindow.close();
                View.Sommet sommetVue = new View.Sommet(sommet.getId(), tag, Forme_Sommet.getFormeViaTexte(forme),sommet.getX(), sommet.getY());
                sommetVue.setTaille(sommet.getTaille());
                sommetVue.setColorVue(sommet.getCouleur());
                grapheView.getSommets().add(sommetVue);
                grapheView.getCanvas().getChildren().add(sommetVue);
                grapheView.getScrollPane().updateScrollPane(grapheView.getCanvas());
            }
        }
    }

    /**
     * Fonction fermant la fenêtre d'ajout d'un sommet au clic sur "Annuler"
     */
    @FXML public void fermerCreerSommet() {
        popUpWindow.close();
    }
}
