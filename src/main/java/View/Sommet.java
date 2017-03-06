package View;

import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Sommet extends Pane {
    private String id;
    private Node vue;

    public Sommet(String id, int x, int y) {
        this.id = id;
        vue = new Circle(5.0f, Color.AQUA );
        vue.relocate(x,y);
        getChildren().add(vue);
    }
}
