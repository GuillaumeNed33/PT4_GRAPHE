package sample.Model;

import javafx.scene.paint.Color;

enum Forme_Sommet {
    Rectangle, Cercle, Triangle, Losange
}

/**
 * Created by audreylentilhac on 02/02/2017.
 */
public class Sommet {

    /**
     * Représente le tag du sommet.
     */
    private String tag;

    /**
     * Représente la coordonnée du sommet en x dans la fenêtre d'affichage.
     */
    private float x;

    /**
     * Représente la coordonnée du sommet en y dans la fenêtre d'affichage.
     */
    private float y;

    /**
     * Représente la forme du sommet.
     */
     private  Forme_Sommet forme;

    /**
     * Représente la taille d'un sommet.
     */
    private float tailleForme;

    /**
     * Représente la couleur d'un sommet.
     */
    private Color couleurSommet;

    /**
     * Constructeur paramétré de la classe Sommet.
     * @param nom Représente le nom du sommet.
     */
    public Sommet(String nom) {
        this.tag = nom;
        this.x = 0;
        this.y = 0;
        this.forme = Forme_Sommet.Cercle;
        this.tailleForme = 10;
        this.couleurSommet = Color.web("000000");
    }

    /**
     * Constructeur paramétré de la classe Sommet.
     * Initialise tous ces attibuts avec les paramètres.
     * @param tag Représente le tag du sommet.
     * @param x Représente la coordonnée en x du sommet.
     * @param y Représente la coordonnée en y du sommet.
     */
    public Sommet(String tag, float x, float y) {
        this.tag = tag;
        this.x = x;
        this.y = y;
        forme = Forme_Sommet.Cercle;
        tailleForme = 10;
        couleurSommet = Color.web("000000");
    }


    // Accesseur et Mutateurs

    public String getTag() {

        return tag;
    }

    public void setTag(String tag) {

        this.tag = tag;
    }

    public float getX() {

        return x;
    }

    public void setX(float x) {

        this.x = x;
    }

    public float getY() {

        return y;
    }

    public void setY(float y) {

        this.y = y;
    }

    public Forme_Sommet getForme() {
        return forme;
    }

    public void setForme(Forme_Sommet forme) {
        this.forme = forme;
    }

    public float getTailleForme() {
        return tailleForme;
    }

    public void setTailleForme(float tailleForme) {
        this.tailleForme = tailleForme;
    }

    public Color getCouleurSommet() {
        return couleurSommet;
    }

    public void setCouleurSommet(Color couleurSommet) {
        this.couleurSommet = couleurSommet;
    }
}
