package View;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 *
 */
public class Arete extends Group {
    private Sommet source;
    private Sommet destination;
    private Line ligne;
    public Arete(Sommet src, Sommet dest) {
        this.source = src;
        this.destination = dest;
        ligne = new Line();
        ligne.setStroke(Color.BLACK);
        ligne.startXProperty().bind( source.layoutXProperty().add(source.getBoundsInParent().getWidth()-Sommet.RAYON_SOMMET));
        ligne.startYProperty().bind( source.layoutYProperty().add(source.getBoundsInParent().getHeight()-Sommet.RAYON_SOMMET));

        ligne.endXProperty().bind( destination.layoutXProperty().add( destination.getBoundsInParent().getWidth()-Sommet.RAYON_SOMMET));
        ligne.endYProperty().bind( destination.layoutYProperty().add( destination.getBoundsInParent().getHeight()-Sommet.RAYON_SOMMET));

        getChildren().add(ligne);
    }
}
