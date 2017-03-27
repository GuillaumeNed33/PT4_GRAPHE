package View;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Classe représentant les arêtes graphique.
 */
public class Arete extends Group {

    /**
     * Représente le prochain id à utiliser.
     * Il est static donc commun à l'ensemble des objets de la classe.
     */
    private static int idActuel = 0;

    /**
     * Représente l'id de l'arête.
     */
    private int id;

    /**
     * Représente le sommet source de l'arête.
     */
    private Sommet source;

    /**
     * Représente le sommet destination de l'arête.
     */
    private Sommet destination;

    /**
     * Représente la version graphique de l'arête, une ligne.
     */
    private Line ligne;


    /**
     * Constructeur permettant de créer et d'ajouter une arête dans la vue.
     * @param src Représente le sommet source de l'arête.
     * @param dest Représente le sommet destination de l'arête.
     */
    public Arete(Sommet src, Sommet dest) {
        this.id = idActuel++;
        this.source = src;
        this.destination = dest;
        ligne = new Line();
        ligne.setStroke(Color.BLACK);

        misAJourCoord();

        getChildren().add(ligne);
    }

    /**
     * Méthode permettant de mettre a jour les coordonnées de l'arête en fonction des sommets source et destination.
     */
    void misAJourCoord() {
        ligne.startXProperty().bind( source.layoutXProperty().add(source.getCoord_x()));
        ligne.startYProperty().bind( source.layoutYProperty().add(source.getCoord_y()));

        ligne.endXProperty().bind( destination.layoutXProperty().add(destination.getCoord_x()));
        ligne.endYProperty().bind( destination.layoutYProperty().add(destination.getCoord_y()));
    }


    // ACCESSEURS ET MUTATEURS

    public void setCouleur(Color couleur) {
        ligne.setStroke(couleur);
    }

    public void setEpaisseur(int epaisseur) {

        ligne.setStrokeWidth(epaisseur);

        misAJourCoord();
    }

    public Line getLigne() {
        return ligne;
    }

    Sommet getSource() {
        return source;
    }

    Sommet getDestination() {
        return destination;
    }

    int getIdGraphe() {
        return id;
    }
}
