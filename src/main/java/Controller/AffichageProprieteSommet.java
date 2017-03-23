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

public class AffichageProprieteSommet extends FXMLController {

    @FXML
    private Label idSommetSelectionne, tagSommetSelectionne, tailleSommetSelectionne, positionSommetSelectionne
            , couleurSommetSelectionne, formeSommetSelectionne;
    @FXML
    private Circle visualisationCouleur;
    public AffichageProprieteSommet(Graphe graphe, Sommet sommetSelectionneModel) throws IOException {
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
            tailleSommetSelectionne.setText("(" + sommetSelectionneModel.getTaille().width + ", " +
            sommetSelectionneModel.getTaille().height + ")");
            positionSommetSelectionne.setText("(" + sommetSelectionneModel.getX() + ", " +
                    sommetSelectionneModel.getY() + ")");
            couleurSommetSelectionne.setText(sommetSelectionneModel.getCouleur().toString());
            formeSommetSelectionne.setText(sommetSelectionneModel.getForme().toString());


            popUpWindow.show();
        }
    }
}
