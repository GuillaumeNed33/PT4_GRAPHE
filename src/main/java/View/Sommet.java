package View;

import Model.Forme_Sommet;
import com.sun.glass.ui.Size;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class Sommet extends Pane {

    private double coord_x;
    private double coord_y;
    private Size taille;
    private Color color;
    private Forme_Sommet fs;
    private int id;
    private Node vue;
    private Label lb;

    public Sommet(int id, String tag, Forme_Sommet fs, double x, double y) {
        this.taille = new Size(5, 5);
        this.id = id;
        this.fs = fs;
        this.color = Color.BLACK;
        this.coord_x = x;
        this.coord_y = y;
        this.lb = new Label(tag);

        redefinitionDeLaVue();

        lb.relocate(x + taille.width,y);
        vue.relocate(x - taille.width, y - taille.height);
        getChildren().addAll(vue, lb);
    }

    public Size getTaille() {
        return taille;
    }

    public void setTaille(Size taille) {
        this.taille = taille;

        int indexVue = getChildren().indexOf(vue);
        getChildren().remove(indexVue);

         redefinitionDeLaVue();

        lb.relocate(coord_x + taille.width,coord_y);
        vue.relocate(coord_x - taille.width, coord_y - taille.height);

        getChildren().add(vue);
    }

    double getCoord_x() {
        return coord_x;
    }

    double getCoord_y() {
        return coord_y;
    }

    public void setCoord(double coord_x, double coord_y) {
        this.coord_x = coord_x;
        this.coord_y = coord_y;

        vue.relocate(coord_x - taille.width, coord_y - taille.height);
    }

    public Label getLb() {
        return lb;
    }

    public void setLb(String lb) {
        this.lb.setText(lb);
    }

    public Node getVue() {
        return vue;
    }

    int getIdGraphe() {return id;}

    public Color getColor() {
        return color;
    }

    public void setColor(Color c){
        this.color = c;
     }

    public void setFormeEtTaille(Forme_Sommet forme, Size taille) {

        this.taille = taille;

        int indexVue = getChildren().indexOf(vue);
        getChildren().remove(indexVue);

        fs = forme;

        redefinitionDeLaVue();

        lb.relocate(coord_x + taille.width,coord_y);
        vue.relocate(coord_x - taille.width, coord_y - taille.height);

        getChildren().add(vue);
    }

    private void redefinitionDeLaVue() {

        switch(fs) {
            case Cercle:
                vue = new Circle(taille.width ,color);
                break;
            case Losange:
                vue = new Polygon();
                break;
            case Rectangle:
                vue = new Rectangle(taille.width*2,taille.height*2,color);
                break;
            case Triangle:
                vue = new Polygon(taille.width,0,taille.width*2,taille.width*2,0,taille.width*2);
                break;
        }
    }

    public void setColorVue(Color c) {
        switch(fs) {
            case Cercle:
                ((Circle)vue).setFill(c);
                break;
            case Losange:
                ((Polygon)vue).setFill(c);
                break;
            case Rectangle:
                ((Rectangle)vue).setFill(c);
                break;
            case Triangle:
                ((Polygon)vue).setFill(c);
                break;
        }
    }
}
