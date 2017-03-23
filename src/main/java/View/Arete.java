package View;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 *
 */
public class Arete extends Group {
    private static int idActuel = 0;
    private int id;
    private Sommet source;
    private Sommet destination;
    private Color couleur;
    private int epaisseur;
    private Line ligne;
    public Arete(Sommet src, Sommet dest) {
        this.id = idActuel++;
        this.source = src;
        this.destination = dest;
        this.couleur = Color.BLACK;
        ligne = new Line();
        ligne.setStroke(couleur);

        ligne.startXProperty().bind( source.layoutXProperty().add(source.getCoord_x() + source.getTaille().width));
        ligne.startYProperty().bind( source.layoutYProperty().add(source.getCoord_y() + source.getTaille().height));

        ligne.endXProperty().bind( destination.layoutXProperty().add(destination.getCoord_x() + destination.getTaille().width));
        ligne.endYProperty().bind( destination.layoutYProperty().add(destination.getCoord_y() + destination.getTaille().height));

        getChildren().add(ligne);
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
        ligne.setStroke(couleur);
    }

    public void setEpaisseur(int epaisseur) {
        this.epaisseur = epaisseur;
        ligne.setStrokeWidth(epaisseur);
    }

    public void misAJourCoord() {
        ligne.startXProperty().bind( source.layoutXProperty().add(source.getCoord_x() + source.getTaille().width));
        ligne.startYProperty().bind( source.layoutYProperty().add(source.getCoord_y() + source.getTaille().height));

        ligne.endXProperty().bind( destination.layoutXProperty().add(destination.getCoord_x() + destination.getTaille().width));
        ligne.endYProperty().bind( destination.layoutYProperty().add(destination.getCoord_y() + destination.getTaille().height));
    }

    public Line getLigne() {
        return ligne;
    }

    public Sommet getSource() {
        return source;
    }

    public Sommet getDestination() {
        return destination;
    }

    public int getIdGraphe() {
        return id;
    }
}
