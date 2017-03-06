package View;

import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.awt.*;


public abstract class Sommet extends Pane {
    static public float RAYON_SOMMET = 5.0f;
    protected Color color;
    private String id;
    protected Node vue;

    public Sommet(String id) {
        this.id = id;
        this.color = Color.BLUE;
    }
}
