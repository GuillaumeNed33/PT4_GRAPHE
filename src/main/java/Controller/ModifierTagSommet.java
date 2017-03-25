package Controller;

import Model.Graphe;
import Model.Sommet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Classe ModifierTagSommet
 * affichant le contrôle de la modification de l'étiquette d'un sommet
 */
public class ModifierTagSommet extends FXMLController {

    /**
     * Représente la nouvelle étiquette du sommet
     */
    @FXML private TextField nouveauTag;

    /**
     * Constructeur de la fenêtre de la modification de l'étiquette d'un sommet
     * @param grapheModel représente le graphe du Model
     * @param grapheView représente le graphe de la View
     * @param sommetSelectionneModel représente le sommet sélectionné du Model
     * @param sommetSelectionneView représente le sommet sélectionné dans la View
     * @throws IOException lève une exception
     */
    ModifierTagSommet(Graphe grapheModel, View.Graphe grapheView, Sommet sommetSelectionneModel, View.Sommet sommetSelectionneView) throws IOException {
        super();
        this.grapheModel = grapheModel;
        this.grapheView = grapheView;
        this.sommetSelectionneModel = sommetSelectionneModel;
        this.sommetSelectionneView = sommetSelectionneView;

        if (grapheModel != null) {
            FXMLLoader fxmlLoaderPopUp = new FXMLLoader(getClass().getResource("/fxml/TagSommet.fxml"));
            popUpWindow.setTitle("Modifier tag du sommet d'id " + sommetSelectionneModel.getTag());
            if (popUpWindow.getScene() == null) {
                fxmlLoaderPopUp.setRoot(this);
                fxmlLoaderPopUp.setController(this);
                popUpWindow.setScene(new Scene((Parent) fxmlLoaderPopUp.load()));
            }
            nouveauTag.setText(sommetSelectionneModel.getTag());
            popUpWindow.show();
        }
    }

    /**
     * Fonction modifiant l'étiquette du sommet sélectionné
     */
    @FXML public void changerTagSommetSelectionne() {
        sommetSelectionneModel.setTag(nouveauTag.getText());
        sommetSelectionneView.setLb(nouveauTag.getText());
        grapheView.getScrollPane().updateScrollPane(grapheView.getCanvas());
        popUpWindow.close();
    }

    /**
     * Fonction fermant la fenêtre au clic sur "Annuler"
     */
    @FXML public void fermerChangerTagSommetSelectionne() {
        popUpWindow.close();
    }
}
