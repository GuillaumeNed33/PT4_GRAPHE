package View;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Created by Thomas on 06/03/2017.
 */
public class Arete extends Group {
    protected Sommet source;
    protected Sommet destination;
    Line ligne;
    public Arete(Sommet src, Sommet dest) {
        this.source = src;
        this.destination = dest;
        ligne = new Line();
        ligne.setStroke(Color.BLACK);
        ligne.startXProperty().bind( source.layoutXProperty().add(source.getBoundsInParent().getWidth() / 2.0));
        ligne.startYProperty().bind( source.layoutYProperty().add(source.getBoundsInParent().getHeight() / 2.0));

        ligne.endXProperty().bind( destination.layoutXProperty().add( destination.getBoundsInParent().getWidth() / 2.0));
        ligne.endYProperty().bind( destination.layoutYProperty().add( destination.getBoundsInParent().getHeight() / 2.0));

        getChildren().add(ligne);
    }
}
