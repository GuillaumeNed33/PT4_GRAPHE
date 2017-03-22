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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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



    ModifierAreteController(Graphe grapheModel, View.Graphe grapheView) throws IOException {
        super();
        this.grapheModel = grapheModel;
        this.grapheView = grapheView;
        if (this.grapheModel != null) {
            FXMLLoader fxmlLoaderPopUp = new FXMLLoader(getClass().getResource("/fxml/ModifierArete.fxml"));
            popUpWindow.setTitle("Supprimer/Modifier Arête");
            if (popUpWindow.getScene() == null) {
                fxmlLoaderPopUp.setRoot(this);
                fxmlLoaderPopUp.setController(this);
                popUpWindow.setScene(new Scene((Parent) fxmlLoaderPopUp.load()));
            }
            popUpWindow.show();

            List<String> AreteStr = new ArrayList<String>();

            for (Arete a : grapheModel.getAretes()) {
                AreteStr.add("tag : " + a.getTag() + " (id : " + a.getId() + " )");
            }

            ListProperty<String> listProperty = new SimpleListProperty<String>();
            listProperty.set(FXCollections.observableArrayList(AreteStr));

            listViewSAretes.itemsProperty().bind(listProperty);

            listViewSAretes.getSelectionModel().selectedItemProperty()
                    .addListener(new ChangeListener<String>() {
                        public void changed(ObservableValue<? extends String> ov,
                                            String old_val, String new_val) {
                            for (Arete a : ModifierAreteController.this.grapheModel.getAretes()) {
                                if(Integer.toString(a.getId()).equals(listViewSAretes.getSelectionModel().getSelectedItem().toString().split("id : ")[1].split(" \\)")[0])) {
                                    selected = a;
                                }
                            }
                            //AreteSelectionne = new_val;
                            couleurChoice.setValue(selected.getCouleur());
                            aretePoids.setText(Integer.toString(selected.getIndice()));
                            areteSize.setText(Integer.toString(selected.getEpaisseur()));
                        }
                    });
        }
    }

    @FXML
    private Label erreurModifierArete;
    @FXML public void modifyArete() {
        if (selected != null) {

            Pattern pattern = Pattern.compile("^[0-9]+$");
            Matcher matcher = pattern.matcher(areteSize.getText());

            if (!areteSize.getText().contains(".") && matcher.find() && Integer.parseInt(areteSize.getText()) > 0) {

                matcher = pattern.matcher(aretePoids.getText());

                if (!aretePoids.getText().contains(".") && matcher.find() && Integer.parseInt(aretePoids.getText()) > 0) {
                    int i = 0;
                    boolean foundSommet = false;
                    while (!foundSommet && i < grapheModel.getAretes().size()) {
                        if (grapheModel.getAretes().get(i).getId() == selected.getId()) {
                            grapheModel.getAretes().get(i).setCouleur(couleurChoice.getValue());
                            grapheModel.getAretes().get(i).setEpaisseur(Integer.parseInt(areteSize.getText()));
                            grapheModel.getAretes().get(i).setIndice(Integer.parseInt(aretePoids.getText()));
                            foundSommet = true;
                        } else {
                            i++;
                        }
                    }

                    grapheView.getScrollPane().updateScrollPane(grapheView.getCanvas());
                    popUpWindow.close();
                }
                else {
                    erreurModifierArete.setText("Erreur - Le poids de l'arête doit être > 1 et une valeur entière.");
                }
            }
            else {
                erreurModifierArete.setText("Erreur - L'épaisseur de l'arête doit être > 1 et une valeur entière.");
            }
        }
        else {
            erreurModifierArete.setText("Erreur - Sélectionnez une arête.");
        }
    }

    @FXML public void supprimerArete() {
        if (selected != null) {
            grapheModel.supprimerArete(selected);
            grapheView.supprimerArete(selected);
            popUpWindow.close();
        }
    }

    @FXML public void fermerModifyArete() {
        popUpWindow.close();
    }

}
