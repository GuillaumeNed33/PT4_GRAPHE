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

/**
 * Classe permettant de créer un sommet pour l'affichage du graphe.
 */
public class Sommet extends Pane {

    /**
     * Représente la coordonnée en x du sommet.
     */
    private double coord_x;

    /**
     * Représente la coordonnée en y du sommet.
     */
    private double coord_y;

    /**
     * Représente la taille du sommet.
     */
    private Size taille;

    /**
     * Représente la couleur du sommet.
     */
    private Color color;

    /**
     * Représente la forme du sommet.
     */
    private Forme_Sommet fs;

    /**
     * Représente l'id du sommet.
     */
    private int id;

    /**
     * Représente l'aspect graphique du sommet.
     */
    private Node vue;

    /**
     * Représente l'aspect graphique du tag.
     */
    private Label lb;


    /**
     * Constructeur pour créer un sommet graphique.
     * @param id Représente l'identifiant du sommet.
     * @param tag Rerpésente la tag du sommet.
     * @param formeSommet Représente la forme du sommet.
     * @param x Représente la coordonnée en x du sommet.
     * @param y Représente la coordonnée en y du sommet.
     */
    public Sommet(int id, String tag, Forme_Sommet formeSommet, double x, double y) {
        this.taille = new Size(5, 5);
        this.id = id;
        this.fs = formeSommet;
        this.color = Color.BLACK;
        this.coord_x = x;
        this.coord_y = y;
        this.lb = new Label(tag);

        redefinitionDeLaVue();

        lb.relocate(x + taille.width,y);
        vue.relocate(x - taille.width, y - taille.height);
        getChildren().addAll(vue, lb);
    }

    /**
     * Permet de changer la forme graphique du sommet.
     */
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


    // ACCESSEURS ET MUTATEURS

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

    public double getCoord_x() {
        return coord_x;
    }

    public double getCoord_y() {
        return coord_y;
    }

    public void setCoord(double coord_x, double coord_y) {
        this.coord_x = coord_x;
        this.coord_y = coord_y;

        lb.relocate(coord_x + taille.width,coord_y);
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
