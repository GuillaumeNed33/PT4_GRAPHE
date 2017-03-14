package Controller;

import Model.Graphe;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Created by audreylentilhac on 14/03/2017.
 */
public class TailleGrapheController extends FXMLController {

    TailleGrapheController(Graphe graphe) throws IOException {
        super();
        this.graphe = graphe;
        if (this.graphe != null) {
       FXMLLoader fxmlLoaderPopUp = new FXMLLoader(getClass().getResource("/fxml/TailleGraphe.fxml"));
            popUpWindow.setTitle("Taille du graphe");
            if (popUpWindow.getScene() == null) {
                fxmlLoaderPopUp.setRoot(this);
                fxmlLoaderPopUp.setController(this);
                popUpWindow.setScene(new Scene((Parent) fxmlLoaderPopUp.load()));
            }
            popUpWindow.show();
        }
    }

    @FXML
    private
    TextField idMinSommet, idMaxSommet, idMinArete, idMaxArete;
    @FXML
    private
    Button okTailleGraphe;
    @FXML
    private
    Label erreurMessageId;
    @FXML public void changementTailleGraphe() {

        if (idMinSommet.getText().matches("^[0-9]+$") && idMaxSommet.getText().matches("^[0-9]+$") &&
                idMinArete.getText().matches("^[0-9]+$") && idMaxArete.getText().matches("^[0-9]+$")) {

            float minSommet = (float) Integer.parseInt(idMinSommet.getText());
            float maxSommet = (float) Integer.parseInt(idMaxSommet.getText());

            float minArete = (float) Integer.parseInt(idMinArete.getText());
            float maxArete = (float) Integer.parseInt(idMaxArete.getText());

            if ((minSommet < maxSommet && minArete < maxArete) &&
                    (minSommet >= 1 && maxSommet > 1 && minArete >= 1 && maxArete > 1)) {
                this.graphe.changerTailleGraphe(maxSommet, minSommet, maxArete, minArete);
                fermerPopup(okTailleGraphe);
            } else {
                erreurMessageId.setText("Erreur - Valeurs incorrectes \n(max > min, min >= 1 et max >1).");
            }
        } else {
            erreurMessageId.setText("Erreur - Vérifiez la présence \nde lettres, oublie de valeurs et/ou valeurs négatives.");
        }
    }

    @FXML
    private
    Button boutonAnnuler;
    @FXML public void fermerPopupChangementTaille() {
        fermerPopup(boutonAnnuler);
    }
}
