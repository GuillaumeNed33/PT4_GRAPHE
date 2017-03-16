package Controller;

import Model.Arete;
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

/**
 * Created by audreylentilhac on 14/03/2017.
 */
public class ModifyAreteController extends FXMLController {

    private String AreteSelectionne;
    @FXML
    private ListView<String> listViewSAretes;

    ModifyAreteController(Graphe graphe) throws IOException {
        super();
        this.graphe = graphe;
        if (this.graphe != null) {
            FXMLLoader fxmlLoaderPopUp = new FXMLLoader(getClass().getResource("/fxml/ModifyArete.fxml"));
            popUpWindow.setTitle("Modifier Arête");
            if (popUpWindow.getScene() == null) {
                fxmlLoaderPopUp.setRoot(this);
                fxmlLoaderPopUp.setController(this);
                popUpWindow.setScene(new Scene((Parent) fxmlLoaderPopUp.load()));
            }
            popUpWindow.show();

            List<String> AreteStr = new ArrayList<String>();

            for (Arete a : graphe.getAretes()) {
                AreteStr.add("id : " + a.getId() + " ( tag : " + a.getTag() + ")");
            }

            ListProperty<String> listProperty = new SimpleListProperty<String>();
            listProperty.set(FXCollections.observableArrayList(AreteStr));


            listViewSAretes.itemsProperty().bind(listProperty);

            listViewSAretes.getSelectionModel().selectedItemProperty()
                    .addListener(new ChangeListener<String>() {
                        public void changed(ObservableValue<? extends String> ov,
                                            String old_val, String new_val) {
                            AreteSelectionne = new_val;
                        }
                    });
        }
    }

    @FXML public void ModifyArete() {

        if (AreteSelectionne != null) {

            int idArete = Integer.parseInt(AreteSelectionne.split(" ")[2]);

            if (idArete != -1 ) {
                /*** MODIF ***/
            }
            else
                fermerModifyArete();
        }
    }
    @FXML
    private
    Button boutonAnnulerModifyArete;
    @FXML public void fermerModifyArete() {
        fermerPopup(boutonAnnulerModifyArete);
    }
}
