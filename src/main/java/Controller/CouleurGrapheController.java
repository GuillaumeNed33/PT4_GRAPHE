package Controller;

import Model.Graphe;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;

import java.io.IOException;


public class CouleurGrapheController extends FXMLController {

    public CouleurGrapheController(Graphe graphe) throws IOException {
        super();
        this.graphe = graphe;
        if (graphe != null) {
            FXMLLoader fxmlLoaderPopUp = new FXMLLoader(getClass().getResource("/fxml/CouleurGraphe.fxml"));
            popUpWindow.setTitle("Couleur du graphe");
            if (popUpWindow.getScene() == null) {
                fxmlLoaderPopUp.setRoot(this);
                fxmlLoaderPopUp.setController(this);
                popUpWindow.setScene(new Scene((Parent) fxmlLoaderPopUp.load()));
            }
            popUpWindow.show();
        }
    }
    @FXML CheckBox checkSommets, checkAretes;
    @FXML ColorPicker miniCouleur, maxiCouleur;
    @FXML Button OKCouleurGraphe;
    @FXML Label erreurCouleurGraphe;
    @FXML
    public void colorierGraphe(){
        if (checkAretes.isSelected()) {
            graphe.changerCouleurAretes(miniCouleur.getValue(), maxiCouleur.getValue());
            fermerPopup(OKCouleurGraphe);
        }
        if (checkSommets.isSelected() && graphe.indiceFixe()) {
            graphe.changerCouleurSommets(miniCouleur.getValue(), maxiCouleur.getValue());
            fermerPopup(OKCouleurGraphe);
        }
        else {
            erreurCouleurGraphe.setText("Erreur - Les sommets ne sont pas indicés.");
        }

    }
    private Button annulerCouleurGraphe;
    @FXML
    public void fermerPopUpCouleurGraphe(){
        fermerPopup(annulerCouleurGraphe);
    }
}
