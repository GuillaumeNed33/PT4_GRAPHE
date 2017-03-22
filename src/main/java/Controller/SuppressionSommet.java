package Controller;

import Model.Graphe;
import Model.Sommet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

public class SuppressionSommet extends FXMLController {


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


    @FXML
    public void suppressionSommetSelectionne() {
        grapheModel.supprimerSommet(sommetSelectionneModel);
        grapheView.suppressionSommet(sommetSelectionneView);
        grapheView.suppressionAreteEnFonctionDuSommetSuppr(sommetSelectionneView);
        grapheView.getScrollPane().updateScrollPane(grapheView.getCanvas());
        sommetSelectionneModel = null;
        popUpWindow.close();
    }

    @FXML
    private Button annulerSuppressionSommetSelectionne;
    @FXML
    public void fermerSupprimerSommetSelectionne() {
        fermerPopup(annulerSuppressionSommetSelectionne);
    }
}
