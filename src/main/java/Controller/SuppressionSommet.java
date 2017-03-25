package Controller;

import Model.Graphe;
import Model.Sommet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

/**
 * Classe permettant d'afficher le contrôle pour la suppression d'un sommet.
 */
public class SuppressionSommet extends FXMLController {

    /**
     * Constructeur permettant d'initialiser la popup de la suppression d'un sommet.
     * @param graphe Représente le graphe (Model).
     * @param sommetSelectionneModel Représente le sommet (Model) sélectionné par un double clic de l'utilisateur.
     * @param sommetSelectionneView Représente le sommet (View) sélectionné par un double clic de l'utilisateur.
     * @param grapheView Représente le graphe (View).
     * @throws IOException Lève une exception.
     */
    public SuppressionSommet(Graphe graphe, Sommet sommetSelectionneModel, View.Sommet sommetSelectionneView, View.Graphe grapheView) throws IOException {
        super();
        this.grapheModel = graphe;
        this.grapheView = grapheView;
        this.sommetSelectionneModel = sommetSelectionneModel;
        this.sommetSelectionneView = sommetSelectionneView;

        if (graphe != null) {
            FXMLLoader fxmlLoaderPopUp = new FXMLLoader(getClass().getResource("/fxml/SupprSommet.fxml"));
            popUpWindow.setTitle("Confirmer suppression");
            if (popUpWindow.getScene() == null) {
                fxmlLoaderPopUp.setRoot(this);
                fxmlLoaderPopUp.setController(this);
                popUpWindow.setScene(new Scene((Parent) fxmlLoaderPopUp.load()));
            }
            popUpWindow.show();
        }
    }


    /**
     * Méthode permettant de confirmer la suppression d'un sommet.
     */
    @FXML
    public void suppressionSommetSelectionne() {
        grapheModel.supprimerSommet(sommetSelectionneModel);
        grapheView.suppressionSommet(sommetSelectionneView);
        grapheView.suppressionAreteEnFonctionDuSommetSuppr(sommetSelectionneView);
        grapheView.getScrollPane().updateScrollPane(grapheView.getCanvas());
        sommetSelectionneModel = null;
        popUpWindow.close();
    }

    /**
     * Méthode permettant d'annuler la popup de confirmation de la suppression d'un sommet.
     */
    @FXML
    public void fermerSupprimerSommetSelectionne() {
        popUpWindow.close();
    }
}
