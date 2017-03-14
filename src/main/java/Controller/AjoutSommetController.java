package Controller;

import Model.Graphe;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

/**
 * Created by audreylentilhac on 14/03/2017.
 */
public class AjoutSommetController extends FXMLController {
    public Graphe getGraphe() {
        return graphe;
    }

    private Graphe graphe;
    public AjoutSommetController(Graphe graphe) throws IOException {
        super();
        this.graphe = graphe;
        if (this.graphe != null) {
            FXMLLoader fxmlLoaderPopUp = new FXMLLoader(getClass().getResource("/fxml/AjoutSommet.fxml"));
            popUpWindow.setTitle("Ajouter Sommet");
            if (popUpWindow.getScene() == null) {
                fxmlLoaderPopUp.setRoot(this);
                fxmlLoaderPopUp.setController(this);
                popUpWindow.setScene(new Scene((Parent) fxmlLoaderPopUp.load()));
            }
            popUpWindow.show();

        }
    }


    @FXML public void ajouterSommet() {

    }
    @FXML private Button boutonAnnulerAjoutSommet;
    @FXML public void fermerAjoutArete() {
        fermerPopup(boutonAnnulerAjoutSommet);
    }
}
