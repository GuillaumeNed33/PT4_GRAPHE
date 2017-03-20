package Controller;

import Model.Arete;
import Model.Graphe;
import com.sun.glass.ui.Size;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by audreylentilhac on 14/03/2017.
 */
public class ModifierAreteController extends FXMLController {

    private String AreteSelectionne;
    private Arete selected = null;
    @FXML private ListView<String> listViewSAretes;
    @FXML private ColorPicker couleurChoice;
    @FXML private TextField areteSize;
    @FXML private TextField aretePoids;



    ModifierAreteController(Graphe graphe) throws IOException {
        super();
        this.grapheModel = graphe;
        if (this.grapheModel != null) {
            FXMLLoader fxmlLoaderPopUp = new FXMLLoader(getClass().getResource("/fxml/ModifierArete.fxml"));
            popUpWindow.setTitle("Modifier Arête");
            if (popUpWindow.getScene() == null) {
                fxmlLoaderPopUp.setRoot(this);
                fxmlLoaderPopUp.setController(this);
                popUpWindow.setScene(new Scene((Parent) fxmlLoaderPopUp.load()));
            }
            popUpWindow.show();

            List<String> AreteStr = new ArrayList<String>();

            for (Arete a : graphe.getAretes()) {
                AreteStr.add("tag : " + a.getTag() + " (id : " + a.getId() + " )");
            }

            ListProperty<String> listProperty = new SimpleListProperty<String>();
            listProperty.set(FXCollections.observableArrayList(AreteStr));

            listViewSAretes.itemsProperty().bind(listProperty);

            listViewSAretes.getSelectionModel().selectedItemProperty()
                    .addListener(new ChangeListener<String>() {
                        public void changed(ObservableValue<? extends String> ov,
                                            String old_val, String new_val) {
                            for (Arete a : grapheModel.getAretes()) {
                                if(Integer.toString(a.getId()).equals(listViewSAretes.getSelectionModel().getSelectedItem().toString().split("id : ")[1].split(" \\)")[0])) {
                                    selected = a;
                                }
                            }
                            AreteSelectionne = new_val;
                            couleurChoice.setValue(selected.getCouleur());
                            aretePoids.setText(Integer.toString(selected.getIndice()));
                            areteSize.setText(Integer.toString(selected.getEpaisseur()));
                        }
                    });
        }
    }

    @FXML public void modifyArete() {
        if (selected != null) {
                int i =0;
                boolean foundSommet = false;
                while (!foundSommet && i<grapheModel.getAretes().size()) {
                    if (grapheModel.getAretes().get(i).getId() == selected.getId()) {
                        grapheModel.getAretes().get(i).setCouleur(couleurChoice.getValue());
                        grapheModel.getAretes().get(i).setEpaisseur(Integer.parseInt(areteSize.getText()));
                        grapheModel.getAretes().get(i).setIndice(Integer.parseInt(aretePoids.getText()));
                        foundSommet=true;
                    }
                    else {
                        i++;
                    }
                }
            grapheView.getScrollPane().updateScrollPane(grapheView.getCanvas());
            popUpWindow.close();
        }
        else {
            popUpWindow.close();
        }
    }

    @FXML public void fermerModifyArete() {
        popUpWindow.close();
    }
}
