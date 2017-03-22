package View;

import Model.Forme_Sommet;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class Sommet extends Pane {
    static float RAYON_SOMMET = 5.0f;
    private Color color;
    private Forme_Sommet fs;
    private int id;
    private String tag;
    private Node vue;
    private Label lb;

    public Sommet(int id, String tag, Forme_Sommet fs, double x, double y) {
        this.id = id;
        this.tag = tag;
        this.fs = fs;
        this.color = Color.BLACK;
        this.lb = new Label(tag);
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
        lb.relocate(x+RAYON_SOMMET,y+RAYON_SOMMET);
        vue.relocate(x,y);
        getChildren().addAll(vue, lb);

    }

    public Label getLb() {
        return lb;
    }
    public Node getVue() {
        return vue;
    }

    public int getIdGraphe() {return id;}
}
