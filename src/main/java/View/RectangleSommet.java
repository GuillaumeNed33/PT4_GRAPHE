package View;

import javafx.scene.shape.Rectangle;

/**
 * Created by Thomas on 06/03/2017.
 */
public class RectangleSommet extends Sommet {
    public RectangleSommet(String id, double x, double y) {
        super(id);
        vue = new Rectangle(Sommet.RAYON_SOMMET*2,Sommet.RAYON_SOMMET*2,color);
        vue.relocate(x,y);
        getChildren().add(vue);
    }
}
