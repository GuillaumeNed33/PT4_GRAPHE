package Controller;


import Model.Graphe;
import Model.Sommet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

import java.io.IOException;

/**
 * Classe AffichagePropriétéSommet
 * affichant le contrôle du tableau des propriétés d'un sommet
 */
class AffichageProprieteSommet extends FXMLController {
    /**
     * Représente respectivement  l'identifiant, l'étiquette, la taille,
     * la position, la couleur et la forme du sommet sélectionné
     */
    @FXML
    private Label idSommetSelectionne, tagSommetSelectionne, tailleSommetSelectionne, positionSommetSelectionne
            , couleurSommetSelectionne, formeSommetSelectionne;
    /**
     * Représente la couleur du sommet sous forme d'un cercle
     */
    @FXML
    private Circle visualisationCouleur;
    /**
     * Constructeur de l'affichage des paramètres des propriétés d'un sommet
     * @param graphe représente la graphe du Model
     * @param sommetSelectionneModel représente le sommet sélectionné du graphe dans le Model
     * @throws IOException lève une exception
     */
    AffichageProprieteSommet(Graphe graphe, Sommet sommetSelectionneModel) throws IOException {
        super();
        this.grapheModel = graphe;
        if (graphe != null) {
            FXMLLoader fxmlLoaderPopUp = new FXMLLoader(getClass().getResource("/fxml/ProprietesSommetTab.fxml"));
            popUpWindow.setTitle("Propriété du sommet d'id " + sommetSelectionneModel.getId());
            if (popUpWindow.getScene() == null) {
                fxmlLoaderPopUp.setRoot(this);
                fxmlLoaderPopUp.setController(this);
                popUpWindow.setScene(new Scene((Parent) fxmlLoaderPopUp.load()));
            }
            visualisationCouleur.setFill(sommetSelectionneModel.getCouleur());
            idSommetSelectionne.setText(Integer.toString(sommetSelectionneModel.getId()));
            tagSommetSelectionne.setText(sommetSelectionneModel.getTag());
            tailleSommetSelectionne.setText("(" + sommetSelectionneModel.getTaille().width + ", " + sommetSelectionneModel.getTaille().height + ")");
            positionSommetSelectionne.setText("(" + sommetSelectionneModel.getX() + ", " + sommetSelectionneModel.getY() + ")");
            couleurSommetSelectionne.setText(sommetSelectionneModel.getCouleur().toString());
            formeSommetSelectionne.setText(sommetSelectionneModel.getForme().toString());

            popUpWindow.show();
        }
    }
}
