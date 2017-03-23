package Controller;

import Model.Graphe;
import View.Arete;
import View.Sommet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.io.IOException;


public class CouleurGrapheController extends FXMLController {

    public CouleurGrapheController(Graphe grapheModel, View.Graphe grapheView) throws IOException {
        super();
        this.grapheModel = grapheModel;
        this.grapheView = grapheView;
        if (grapheModel != null) {
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
            grapheModel.changerCouleurAretes(miniCouleur.getValue(), maxiCouleur.getValue());
            int i = 0;
            for (Arete a : grapheView.getAretes()){
                Color c = grapheModel.getAretes().get(i).getCouleur();
                a.getLigne().setStroke(c);
                i++;
            }
            grapheView.getScrollPane().updateScrollPane(grapheView.getCanvas());
            fermerPopup(OKCouleurGraphe);
        }
        if (checkSommets.isSelected() && grapheModel.indiceFixe()) {
            grapheModel.changerCouleurSommets(miniCouleur.getValue(), maxiCouleur.getValue());
            int i = 0;
            for (Sommet s : grapheView.getSommets()){
                Color c = grapheModel.getSommets().get(i).getCouleur();
                s.setColor(c);
                s.setColorVue(c);
                i++;
            }
            grapheView.getScrollPane().updateScrollPane(grapheView.getCanvas());
            fermerPopup(OKCouleurGraphe);
        }
        else {
            erreurCouleurGraphe.setText("Erreur - Les sommets ne sont pas indicés \net / ou vous n'avez pas sélectionné Sommets ou Arêtes.");
        }

    }
    private Button annulerCouleurGraphe;
    @FXML
    public void fermerPopUpCouleurGraphe(){
        fermerPopup(annulerCouleurGraphe);
    }
}
