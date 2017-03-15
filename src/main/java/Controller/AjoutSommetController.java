package Controller;

import Model.Graphe;
import com.sun.glass.ui.Size;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Pair;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by audreylentilhac on 14/03/2017.
 */
public class AjoutSommetController extends FXMLController {

    @FXML
    private ComboBox comboBoxForme;
    AjoutSommetController(Graphe graphe) throws IOException {
        super();
        this.graphe = graphe;
        if (this.graphe != null) {
            FXMLLoader fxmlLoaderPopUp = new FXMLLoader(getClass().getResource("/fxml/AjoutSommet.fxml"));
            popUpWindow.setTitle("Ajouter Sommet");
            if (popUpWindow.getScene() == null) {
                fxmlLoaderPopUp.setRoot(this);
                fxmlLoaderPopUp.setController(this);
                popUpWindow.setScene(new Scene((Parent) fxmlLoaderPopUp.load()));
            }

            ObservableList<String> options = FXCollections.observableArrayList("Cercle", "Carrée", "Triangle", "Losange");

            comboBoxForme.setItems(options);
            comboBoxForme.setValue("Cercle");

            popUpWindow.show();
        }
    }

    @FXML
    private TextField tailleSommet, positionSommet;
    @FXML
    private Label erreurAjoutSommet;
    @FXML public void creerSommet() {

        Size tailleSommet = déterminationTailleRentrerParUtilisateur();

        Pair<Float, Float> coordSommet = null;

        if (tailleSommet != null) {
            coordSommet = déterminationPositionRentrerParUtilisateur();
        }

        if (tailleSommet != null && coordSommet != null) {
            //ToDo créer le sommet
        }
    }


    private Size déterminationTailleRentrerParUtilisateur() {
        if (!tailleSommet.getText().contains(".")) {
            String[] elementsDansTaille = tailleSommet.getText().split(" ");
            if (elementsDansTaille.length > 1) {

                Pattern pattern = Pattern.compile("[0-9]+");
                Matcher matcher;

                int largeur = 0;
                int longueur = 0;
                int cptDetrompeur = 0; // Pour compter si il n'y a pas plus de valeurs que prévus
                int cptElement = 0;
                while (cptElement < elementsDansTaille.length && cptDetrompeur <= 2) {
                    matcher = pattern.matcher(elementsDansTaille[cptElement]);

                    if (matcher.find()) {
                        if (cptDetrompeur == 0) {
                            largeur = Integer.parseInt(matcher.group());
                        } else if (cptDetrompeur == 1) {
                            longueur = Integer.parseInt(matcher.group());
                        }

                        ++cptDetrompeur;
                    }

                    ++cptElement;
                }

                if (cptDetrompeur <= 2) {
                    return new Size(largeur, longueur);

                } else {
                    erreurAjoutSommet.setText("Erreur - Trop de valeurs.");
                }
            } else {
                erreurAjoutSommet.setText("Erreur - Une ou aucune valeur rentrée pour la taille du sommet.\n" +
                        "Les différentes valeurs doivent être séparées par un espace (Exemple : 2, 2)");
            }
        }
        else {
            erreurAjoutSommet.setText("Erreur - Seul les valeurs entières sont acceptées.");
        }

        return null;
    }


    private Pair<Float, Float> déterminationPositionRentrerParUtilisateur() {


            String[] elementsDansPosition = positionSommet.getText().split(" ");
            if (elementsDansPosition.length > 1) {

                Pattern pattern = Pattern.compile("[0-9]+\\.+[0-9]+");
                Matcher matcher;

                float coordX = 0;
                float coordY = 0;
                int cptDetrompeur = 0; // Pour compter si il n'y a pas plus de valeurs que prévus
                int cptElement = 0;
                while (cptElement < elementsDansPosition.length && cptDetrompeur != -1 && cptDetrompeur <= 2) {

                    matcher = pattern.matcher(elementsDansPosition[cptElement]);

                    if (matcher.find() && elementsDansPosition[cptElement].contains(".")) {
                        if (cptDetrompeur == 0) {
                            coordX = Float.parseFloat(matcher.group());
                        } else if (cptDetrompeur == 1) {
                            coordY = Float.parseFloat(matcher.group());
                        }

                        ++cptDetrompeur;
                    }
                    else {
                         erreurAjoutSommet.setText("Erreur - Seul les valeurs décimales sont acceptées.");
                         cptDetrompeur = -1; // Sortie de la boucle
                    }

                    ++cptElement;
                }

                if (cptDetrompeur <= 2 && cptDetrompeur != 1) {
                    return new Pair<Float, Float>(coordX, coordY);

                }
                else if (cptDetrompeur != 1) {
                    erreurAjoutSommet.setText("Erreur - Trop de valeurs.");
                }

            } else {
                erreurAjoutSommet.setText("Erreur - Une ou aucune valeur rentrée pour la taille du sommet.\n" +
                        "Les différentes valeurs doivent être séparées par un espace (Exemple : 2, 2)");
            }

        return null;
    }

    @FXML private Button annulerCreerSommet;
    @FXML public void fermerCreerSommet() {
        fermerPopup(annulerCreerSommet);
    }
}
