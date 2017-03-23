package Controller;

import Model.Graphe;
import Model.Sommet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ModifierTagSommet extends FXMLController {

    @FXML
    private TextField nouveauTag;
    public ModifierTagSommet(Graphe grapheModel, View.Graphe grapheView, Sommet sommetSelectionneModel, View.Sommet sommetSelectionneView) throws IOException {
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


    @FXML
    public void changerTagSommetSelectionne() {

        sommetSelectionneModel.setTag(nouveauTag.getText());
        sommetSelectionneView.setLb(nouveauTag.getText());
        grapheView.getScrollPane().updateScrollPane(grapheView.getCanvas());
        popUpWindow.close();
    }

    @FXML
    public void fermerChangerTagSommetSelectionne() {
        popUpWindow.close();
    }
}
