package Model;

import com.sun.glass.ui.Size;
import javafx.scene.paint.Color;

/**
 * Created by audreylentilhac on 02/02/2017.
 */
public class Sommet {

    /**
     * Représente le prochain id à utiliser.
     * il est static donc commun à l'ensemble des objets de la classe.
     */
    private static int idActuel = 0;

    /**
     * Représente l'id du sommet.
     */
    private int id;

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
    private Size tailleForme;

    /**
     * Représente la couleur d'un sommet.
     */
    private Color couleurSommet;

    /**
     * Reprséente l'indice du sommet
     */
    private int indice;

    /**
     * Constructeur paramétré de la classe Sommet.
     * @param nom Représente le nom du sommet.
     */
    public Sommet(String nom) {
        this.id = ++idActuel;
        this.tag = nom;
        this.x = 0;
        this.y = 0;
        this.forme = Forme_Sommet.Cercle;
        this.tailleForme = new Size(10,10);
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
        tailleForme = new Size(10 ,10);
        couleurSommet = Color.web("000000");
    }


    // Accesseur et Mutateurs

    public int getId() {

        return id;
    }

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

    public Size getTailleForme() {
        return tailleForme;
    }

    public void setTailleForme(Size tailleForme) {
        this.tailleForme = tailleForme;
    }

    public Color getCouleurSommet() {
        return couleurSommet;
    }

    public void setCouleurSommet(Color couleurSommet) {
        this.couleurSommet = couleurSommet;
    }

    public int getIndice() { return indice; }

    public void setIndice(int indice) { this.indice = indice; }
}
