package View;

import Model.Forme_Sommet;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;




public class Sommet extends Pane {
    static public float RAYON_SOMMET = 5.0f;
    private Color color;
    private Forme_Sommet fs;
    private String id;
    private Node vue;

    public Sommet(String id, Forme_Sommet fs, double x, double y) {
        this.id = id;
        this.fs = fs;
        this.color = Color.BLUE;
        switch(fs) {
            case Cercle:
                vue = new Circle(RAYON_SOMMET,color);
                break;
            case Losange:
                vue = new Polygon();
                break;
            case Rectangle:
                vue = new Rectangle(RAYON_SOMMET*2,RAYON_SOMMET*2,color);
                break;
            case Triangle:
                vue = new Polygon(RAYON_SOMMET,0,RAYON_SOMMET*2,RAYON_SOMMET*2,0,RAYON_SOMMET*2);
                break;
        }
        vue.relocate(x,y);
        getChildren().add(vue);

    }
}
