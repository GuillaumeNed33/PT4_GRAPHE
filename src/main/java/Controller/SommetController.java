package Controller;

import Model.Forme_Sommet;
import com.sun.glass.ui.Size;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Pair;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by audreylentilhac on 18/03/2017.
 */
public class SommetController {

    /**
     * List d'observable permettant d'initialiser les différentes combo box via l'énumération Forme_Sommet.
     */
    protected ObservableList<Forme_Sommet> formes = FXCollections.observableArrayList(Forme_Sommet.values());
    SommetController(){
    }

    /**
     * Méthode permettant de vérifier l'intégrité des données rentré par l'utilisateur dans le Text Field de la taille.
     * @param forme_sommet Représente la forme du sommet sélectionné dans la combo box.
     * @param tailleSommet Représente le Text Field où l'utilisateur rentre la taille qu'il veut définir pour le sommet.
     * @param messageErreur Représente le Label où s'affichera un message en rouge si l'utilisateur rentre une taille invalide.
     * @return Retourne la taille rentrée par l'utiliseur sous le type Size et retourne null si la taille saisie est invalide.
     */
    protected Size déterminationTailleRentrerParUtilisateur(Forme_Sommet forme_sommet, TextField tailleSommet, Label messageErreur) {
        if (!tailleSommet.getText().contains(".")) {
            String[] elementsDansTaille = tailleSommet.getText().split(" ");

            int limiteNombreValeur = 1;
            if (forme_sommet == Forme_Sommet.Rectangle) {
                limiteNombreValeur = 2;
            }

            if (elementsDansTaille.length > limiteNombreValeur - 1) {

                Pattern pattern;
                Matcher matcherNombre;
                Matcher matcherLettre;

                int largeur = 0;
                int longueur = 0;
                int cptDetrompeur = 0; // Pour compter si il n'y a pas plus de valeurs que prévus
                int cptElement = 0;
                while (cptElement < elementsDansTaille.length && cptDetrompeur <= limiteNombreValeur) {
                    pattern = Pattern.compile("[0-9]+");
                    matcherNombre = pattern.matcher(elementsDansTaille[cptElement]);

                    pattern = Pattern.compile("[^\\d+\\.+\\ ]+");
                    matcherLettre = pattern.matcher(elementsDansTaille[cptElement]);

                    if (matcherNombre.find() && cptDetrompeur != -1 && !matcherLettre.find()) {
                        if (limiteNombreValeur == 2) {
                            if (cptDetrompeur == 0) {
                                largeur = Integer.parseInt(matcherNombre.group());
                            } else if (cptDetrompeur == 1) {
                                longueur = Integer.parseInt(matcherNombre.group());
                            }
                        }
                        else {
                            if (cptDetrompeur == 0) {
                                largeur = Integer.parseInt(matcherNombre.group());
                                longueur = Integer.parseInt(matcherNombre.group());
                            }
                        }

                        ++cptDetrompeur;
                    }
                    else {
                        messageErreur.setText("Erreur - Seul les valeurs entières sont acceptées pour la taille.");
                        cptDetrompeur = -1;
                    }

                    ++cptElement;
                }

                if (cptDetrompeur <= limiteNombreValeur) {
                    return new Size(largeur, longueur);

                } else {
                    messageErreur.setText("Erreur - Trop de valeurs (Seul la forme Rectangle nécessite 2 valeurs).");
                }
            } else {
                messageErreur.setText("Erreur - Une ou aucune valeur rentrée pour la taille du sommet.\n" +
                        "Les valeurs doivent être séparées par un espace (Exemple : 2, 2)");
            }
        }
        else {
            messageErreur.setText("Erreur - Seul les valeurs entières sont acceptées pour la taille.");
        }

        return null;
    }

    /**
     * Méthode permettant de vérifier l'intégrité des données rentré par l'utilisateur dans le Text Field de la position.
     * @param positionSommet Représente le Text Field où l'utilisateur rentre la position qu'il veut définir pour le sommet.
     * @param messageErreur Représente le Label où s'affichera un message en rouge si l'utilisateur rentre une position invalide.
     * @return Retourne la position rentrée par l'utiliseur sous le type d'une Pair et retourne null si la position saisie est invalide.
     */
    protected Pair<Float, Float> déterminationPositionRentrerParUtilisateur(TextField positionSommet, Label messageErreur) {

        String[] elementsDansPosition = positionSommet.getText().split(" ");
        if (elementsDansPosition.length > 1) {

            Pattern pattern;
            Matcher matcherNombre;
            Matcher matcherLettre;

            float coordX = 0;
            float coordY = 0;
            int cptDetrompeur = 0; // Pour compter si il n'y a pas plus de valeurs que prévus
            int cptElement = 0;
            while (cptElement < elementsDansPosition.length && cptDetrompeur != -1 && cptDetrompeur <= 2) {

                pattern = Pattern.compile("[0-9]+");
                matcherNombre = pattern.matcher(elementsDansPosition[cptElement]);

                pattern = Pattern.compile("[^\\d+\\.+\\ ]+");
                matcherLettre = pattern.matcher(elementsDansPosition[cptElement]);

                if (matcherNombre.find() && !matcherLettre.find()) {
                    if (cptDetrompeur == 0) {
                        coordX = Float.parseFloat(matcherNombre.group());
                    } else if (cptDetrompeur == 1) {
                        coordY = Float.parseFloat(matcherNombre.group());
                    }

                    ++cptDetrompeur;
                }
                else {
                    messageErreur.setText("Erreur - Seul les valeurs décimales sont acceptées pour la position.");
                    cptDetrompeur = -1; // Sortie de la boucle
                }

                ++cptElement;
            }

            if (cptDetrompeur <= 2 && cptDetrompeur != 1) {
                return new Pair<Float, Float>(coordX, coordY);

            }
            else if (cptDetrompeur != 1) {
                messageErreur.setText("Erreur - Trop de valeurs.");
            }

        } else {
            messageErreur.setText("Erreur - Une ou aucune valeur rentrée pour la position du sommet.\n" +
                    "Les valeurs doivent être séparées par un espace (Exemple : 2.2, 2.2)");
        }

        return null;
    }


    /**
     * Méthode permettant de vérifier l'intégrité des données rentré par l'utilisateur dans le Text Field de l'indice.
     * @param indiceSommet Représente le Text Field où l'utilisateur rentre la l'indice qu'il veut définir pour le sommet.
     * @param messageErreur Représente le Label où s'affichera un message en rouge si l'utilisateur rentre un indice invalide.
     * @return Retourne l'indice rentré par l'utiliseur sous le type int et retourne -1 si l'indice saisi est invalide.
     */
    protected int déterminationIndiceRentrerParUtilisateur(TextField indiceSommet, Label messageErreur) {

        String indice = indiceSommet.getText();
        if (indice.length() > 0 && !indice.contains(".") && !indice.contains(",") && !indice.contains("-")) {
            return Integer.parseInt(indice);
        }
        else{
            messageErreur.setText("L'indice doit être une valeur entière positive. Ex: 2");
        }
        return -1;
    }
}
