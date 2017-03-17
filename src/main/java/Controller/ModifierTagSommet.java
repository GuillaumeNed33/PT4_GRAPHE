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

    private Sommet sommetSelectionne;

    @FXML
    private TextField nouveauTag;
    public ModifierTagSommet(Graphe graphe, Sommet sommetSelectionne) throws IOException {
        super();
        this.graphe = graphe;
        this.sommetSelectionne = sommetSelectionne;

        if (graphe != null) {
            FXMLLoader fxmlLoaderPopUp = new FXMLLoader(getClass().getResource("/fxml/TagSommet.fxml"));
            popUpWindow.setTitle("Modifier tag du sommet d'id " + sommetSelectionne.getTag());
            if (popUpWindow.getScene() == null) {
                fxmlLoaderPopUp.setRoot(this);
                fxmlLoaderPopUp.setController(this);
                popUpWindow.setScene(new Scene((Parent) fxmlLoaderPopUp.load()));
            }

            nouveauTag.setText(sommetSelectionne.getTag());

            popUpWindow.show();
        }
    }


    @FXML
    public void changerTagSommetSelectionne() {

        sommetSelectionne.setTag(nouveauTag.getText());
        popUpWindow.close();
    }

    @FXML
    public void fermerChangerTagSommetSelectionne() {
        popUpWindow.close();
    }
}
