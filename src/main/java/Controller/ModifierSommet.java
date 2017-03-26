package Controller;

import Model.Forme_Sommet;
import Model.Graphe;
import Model.Sommet;
import com.sun.glass.ui.Size;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Pair;

import java.io.IOException;


/**
 * Classe ModifierSommet
 * affichant le contrôle de la modification d'un sommet
 */
public class ModifierSommet extends FXMLController {

    /**
     * Représente le controller d'un sommet pour vérifier la validité de la saisie de l'utilisateir
     */
    private SommetController sommetControl;

    /**
     * Représente la liste déroulante des formes possibles d'un sommet
     */
    @FXML private ChoiceBox<Forme_Sommet> formeSommet;

    /**
     * Représente respectivement la position, la taille et l'indice d'un sommet
     */
    @FXML private TextField PosSommet, tailleSommet, indiceSommet;

    /**
     * Représente le choix de couleur d'un sommet
     */
    @FXML private ColorPicker couleurSommet;

    /**
     * Représente l'étiquette d'un sommet
     */
    @FXML private Label erreurSommet;

    /**
     * Constructeur de la fenêtre de la modification d'un sommet
     * @param grapheModel représente le graphe du Model
     * @param grapheView représente le graphe de la View
     * @param sommetSelectionneModel représente le sommet sélectionné du Model
     * @param sommetSelectionneView représente le sommet sélectionné dans la View
     * @throws IOException lève une exception
     */
    ModifierSommet(Graphe grapheModel, View.Graphe grapheView, Sommet sommetSelectionneModel, View.Sommet sommetSelectionneView) throws IOException {
        super();
        sommetControl= new SommetController();
        this.grapheModel = grapheModel;
        this.grapheView = grapheView;
        this.sommetSelectionneModel = sommetSelectionneModel;
        this.sommetSelectionneView = sommetSelectionneView;
        if (grapheModel != null) {
            FXMLLoader fxmlLoaderPopUp = new FXMLLoader(getClass().getResource("/fxml/ModifierSommet.fxml"));
            popUpWindow.setTitle("Modifier le sommet " + sommetSelectionneModel.getId());
            if (popUpWindow.getScene() == null) {
                fxmlLoaderPopUp.setRoot(this);
                fxmlLoaderPopUp.setController(this);
                popUpWindow.setScene(new Scene((Parent) fxmlLoaderPopUp.load()));
                formeSommet.setValue(sommetSelectionneModel.getForme());
                ObservableList<Forme_Sommet> formes = FXCollections.observableArrayList(Forme_Sommet.values());
                formeSommet.setItems(formes);
            }

            PosSommet.setText(sommetSelectionneModel.getX() + " " + sommetSelectionneModel.getY());

            if (sommetSelectionneModel.getForme().toString().equals("Rectangle")) {
                tailleSommet.setText(sommetSelectionneModel.getTaille().width + " " + sommetSelectionneModel.getTaille().height);
            }
            else {
                tailleSommet.setText(Integer.toString(sommetSelectionneModel.getTaille().width));
            }
            indiceSommet.setText(Integer.toString(sommetSelectionneModel.getIndice()));
            couleurSommet.setValue(sommetSelectionneModel.getCouleur());

            popUpWindow.show();
        }
    }

    /**
     * Fonction permettant de modifier les propriétés d'un sommet
     */
    public void ModifierSommetSelectionne(){

        Size tailleSommet = sommetControl.déterminationTailleRentrerParUtilisateur(formeSommet.getValue(), this.tailleSommet, this.erreurSommet);
        Pair<Float, Float> coordSommet = null;
        int indiceSommet = 0;

        if (tailleSommet != null) {
            coordSommet = sommetControl.déterminationPositionRentrerParUtilisateur(this.PosSommet, this.erreurSommet);
            indiceSommet = sommetControl.déterminationIndiceRentrerParUtilisateur(this.indiceSommet, this.erreurSommet);
        }
        if (tailleSommet != null && coordSommet != null && indiceSommet >= 0) {
            sommetSelectionneModel.setTaille(tailleSommet);
            sommetSelectionneModel.setIndice(indiceSommet);
            sommetSelectionneModel.setX(coordSommet.getKey());
            sommetSelectionneModel.setY(coordSommet.getValue());
            sommetSelectionneModel.setCouleur(couleurSommet.getValue());
            sommetSelectionneModel.setForme(formeSommet.getValue().toString());

            sommetSelectionneView.setFormeEtTaille(formeSommet.getValue(), tailleSommet);
            sommetSelectionneView.setCoord(coordSommet.getKey(), coordSommet.getValue());
            sommetSelectionneView.setColorVue(couleurSommet.getValue());
            sommetSelectionneView.getLb().relocate(coordSommet.getKey() + tailleSommet.width,coordSommet.getValue());

            grapheView.misAJourAretes(sommetSelectionneView);

            popUpWindow.close();
        }
        else {
            erreurSommet.setText("Valeurs invalides");
        }
    }

    /**
     * Fonction permettant de fermer la fenêtre au clic sur "Annuler"
     */
    public void fermerPopUpModifierSommet(){
        popUpWindow.close();
    }
}
