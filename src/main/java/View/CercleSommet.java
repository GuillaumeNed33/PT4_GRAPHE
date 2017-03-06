package View;

import javafx.scene.shape.Circle;

/**
 * Created by Thomas on 06/03/2017.
 */
public class CercleSommet extends Sommet {
    public CercleSommet(String id, double x, double y) {
        super(id);
        vue = new Circle(Sommet.RAYON_SOMMET,color);
        vue.relocate(x,y);
        this.getChildren().add(vue);

    }
}
