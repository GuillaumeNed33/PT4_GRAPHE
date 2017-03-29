package Model;

import com.sun.glass.ui.Size;
import javafx.scene.paint.Color;

/**
 * Classe permettant de construire un sommet.
 */
public class Sommet extends ComposantGraphe{

    /**
     * Représente le prochain id à utiliser.
     * Il est static donc commun à l'ensemble des objets de la classe.
     */
    private static int idActuel = 0;

    /**
     * Représente l'id contenu dans le fichier.
     */
    private int idImportation;

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
     * Représente la taille d'un sommet
     */
    private Size taille;

    /**
     * Constructeur paramétré de la classe Sommet.
     * @param nom Représente le nom du sommet.
     */
    public Sommet(String nom) {
        super(idActuel++, 0, nom, Color.BLACK);
        this.x = 0;
        this.y = 0;
        this.taille = new Size(5,5);
        this.forme = Forme_Sommet.Cercle;
    }

    /**
     * Constructeur paramétré de la classe Sommet.
     * Initialise tous ces attibuts avec les paramètres.
     * @param tag Représente le tag du sommet.
     * @param x Représente la coordonnée en x du sommet.
     * @param y Représente la coordonnée en y du sommet.
     */
    public Sommet(String tag, float x, float y) {
        super(idActuel++, 0, tag, Color.BLACK);
        this.x = x;
        this.y = y;
        this.taille = new Size(5,5);
        forme = Forme_Sommet.Cercle;
    }

    /**
     * Constructeur utilisé lors de l'importation du graphe.
     * @param idImportation Représente l'id qu'a le sommet dans le fichier.
     */
    public Sommet(int idImportation) {
        super(idActuel++, 0, "", Color.BLACK);
        this.idImportation = idImportation;
        this.x = 0;
        this.y = 0;
        this.taille = new Size(5,5);
        this.forme = Forme_Sommet.Cercle;
    }


    // ACCESSEURS ET MUTATEURS

    int getIdImportation() {

        return idImportation;
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

    public void setForme(String forme) {

        if (forme.equals("circle") ||forme.equals("Cercle") || forme.equals("ellipse") || forme.equals("Ellipse") || forme.equals("Circle")) {
            this.forme = Forme_Sommet.Cercle;
        }
        else if (forme.equals("Rectangle") || forme.equals("rectangle") || forme.equals("Carré") || forme.equals("carré") || forme.equals("Carre") || forme.equals("square")) {
            this.forme = Forme_Sommet.Rectangle;
        }
        else if (forme.equals("Triangle") || forme.equals("triangle")) {
            this.forme = Forme_Sommet.Triangle;
        }
        else if (forme.equals("Losange") || forme.equals("diamond") || forme.equals("losange") || forme.equals("Diamond")) {
            this.forme = Forme_Sommet.Losange;
        }
        else { // Par défaut
            this.forme = Forme_Sommet.Cercle;
        }
    }

    public Size getTaille() {
        return taille;
    }

    public void setTaille(Size taille) {
        this.taille = taille;
    }
}
