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

/**
 * Classe CouleurGrapheController
 * permettant de gérer la fenêtre de coloration des sommets et des arêtes du graphe
 */
public class CouleurGrapheController extends FXMLController {

    /**
     *  Représentent respcetivement la checkbox des sommets et la checkbox des arêtes
     */
    @FXML private CheckBox checkSommets, checkAretes;

    /**
     * Représentent respectivement le choix de couleur minimal et le choix de couleur maximal
     */
    @FXML private ColorPicker miniCouleur, maxiCouleur;

    /**
     * Représente le bouton de confirmation pour colorier le graphe
     */
    @FXML private Button OKCouleurGraphe;

    /**
     * Représente le message d'erreur à afficher dans la fenêtre
     * en cas d'erreur de saisie de l'utilisateur
     */
    @FXML private Label erreurCouleurGraphe;

    /**
     * Représente le bouton d'annulation pour sortir de la fenêtre
     * sans appliquer de modification
     */
    @FXML private Button annulerCouleurGraphe;

    /**
     * Constructeur permettant l'affichage de la fenêtre de coloration du graphe
     * @param grapheModel représente le graphe du Model
     * @param grapheView représente le graphe de la View
     * @throws IOException lève une exception
     */
    CouleurGrapheController(Graphe grapheModel, View.Graphe grapheView) throws IOException {
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

    /**
     * Fonction permettant de controler la coloration du grpahe.
     * fonction ajoute le nouveau sommet dans le Model et met à jour la View
     */
    @FXML public void colorierGraphe(){

        if (checkAretes.isSelected() || checkSommets.isSelected()) {
            if (checkAretes.isSelected()) {
                grapheModel.changerCouleurAretes(miniCouleur.getValue(), maxiCouleur.getValue());
                int i = 0;
                for (Arete a : grapheView.getAretes()) {
                    Color c = grapheModel.getAretes().get(i).getCouleur();
                    a.getLigne().setStroke(c);
                    i++;
                }
                grapheView.getScrollPane().updateScrollPane(grapheView.getCanvas());

                if (!checkSommets.isSelected())
                    popUpWindow.close();
            }
            if (checkSommets.isSelected() && grapheModel.indiceFixe()) {
                grapheModel.changerCouleurSommets(miniCouleur.getValue(), maxiCouleur.getValue());
                int i = 0;
                for (Sommet s : grapheView.getSommets()) {
                    Color c = grapheModel.getSommets().get(i).getCouleur();
                    s.setColor(c);
                    s.setColorVue(c);
                    i++;
                }
                grapheView.getScrollPane().updateScrollPane(grapheView.getCanvas());
                popUpWindow.close();
            } else {
                erreurCouleurGraphe.setText("Erreur - Les sommets ne sont pas indicés.");
            }
        }
        else {
            erreurCouleurGraphe.setText("Erreur - Vous n'avez pas sélectionné Sommets ou Arêtes.");
        }
    }

    /**
     * Fonction fermant la fenêtre d'ajout d'un sommet au clic sur "Annuler"
     */
    @FXML public void fermerPopUpCouleurGraphe(){
        popUpWindow.close();
    }
}
