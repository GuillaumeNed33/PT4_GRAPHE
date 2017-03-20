package Controller;

import Model.Graphe;
import Model.Sommet;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AjoutAreteController extends FXMLController {

    private String sommetEntreSelectionne, sommetSortieSelectionne;
    @FXML
    private
    ListView<String> listViewSommetsE;
    @FXML
    private ListView<String> listViewSommetsS;

    AjoutAreteController(Graphe graphe) throws IOException {
        super();
        this.grapheModel = graphe;
        if (this.grapheModel != null) {
            FXMLLoader fxmlLoaderPopUp = new FXMLLoader(getClass().getResource("/fxml/AjoutArete.fxml"));
            popUpWindow.setTitle("Ajouter Arête");
            if (popUpWindow.getScene() == null) {
                fxmlLoaderPopUp.setRoot(this);
                fxmlLoaderPopUp.setController(this);
                popUpWindow.setScene(new Scene((Parent) fxmlLoaderPopUp.load()));
            }
            popUpWindow.show();
            List<String> sommetsStr = new ArrayList<String>();

            for (Sommet sommet : graphe.getSommets()) {
                sommetsStr.add("tag : " + sommet.getTag() + " (id : " + sommet.getId() + " )");
            }

            ListProperty<String> listProperty = new SimpleListProperty<String>();
            listProperty.set(FXCollections.observableArrayList(sommetsStr));


            listViewSommetsE.itemsProperty().bind(listProperty);
            listViewSommetsS.itemsProperty().bind(listProperty);

            listViewSommetsE.getSelectionModel().selectedItemProperty()
                    .addListener(new ChangeListener<String>() {
                        public void changed(ObservableValue<? extends String> ov,
                                            String old_val, String new_val) {
                            sommetEntreSelectionne = new_val;
                        }
                    });

            listViewSommetsS.getSelectionModel().selectedItemProperty()
                    .addListener(new ChangeListener<String>() {
                        public void changed(ObservableValue<? extends String> ov,
                                            String old_val, String new_val) {
                            sommetSortieSelectionne = new_val;
                        }
                    });
        }
    }

    @FXML
    private
    Label erreurAjoutArete;
    @FXML public void ajouterArete() {

        if (sommetEntreSelectionne != null && sommetSortieSelectionne != null) {

            int idSommetEntre = Integer.parseInt(sommetEntreSelectionne.split(" ")[5]);
            int idSommetSortie = Integer.parseInt(sommetSortieSelectionne.split(" ")[5]);

            if (!grapheModel.ajouterArete(grapheModel.trouverSommetParID(idSommetEntre), grapheModel.trouverSommetParID(idSommetSortie))) {
                erreurAjoutArete.setText("Erreur - Arete existante ou 2 \nsommets identiques sélectionnés.");
            }
            else {
                popUpWindow.close();
            }
        }
        else {
            erreurAjoutArete.setText("Erreur - Sélectionnez 2 sommets.");
        }
    }


    @FXML public void fermerAjoutArete() {
        popUpWindow.close();
    }
}
