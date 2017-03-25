package Model;

import javafx.scene.paint.Color;

/**
 * Classe mère d'arête et de sommet.
 */
public class ComposantGraphe {

    /**
     * Représente l'identifiant d'un composant du graphe (arête ou sommet).
     */
    protected int identifiant;

    /**
     * Représente l'indice d'un composant du graphe (arête ou sommet).
     */
    protected int indice;

    /**
     * Représente la couleur d'un composant du graphe (arête ou sommet).
     */
    protected Color couleur;

    /**
     * Représente le nom d'un composant du graphe (arête ou sommet).
     */
    protected String nom;

    /**
     * Constructeur permettant d'initialiser tout les attribut déclaré au-dessus.
     * @param id Paramètre initialisant l'identifiant d'un composant du graphe (arête ou sommet).
     * @param indice Paramètre initialisant l'indice d'un composant du graphe (arête ou sommet).
     * @param tag Paramètre initialisant le tag d'un composant du graphe (arête ou sommet).
     * @param couleur Paramètre initialisant la couleur d'un composant du graphe (arête ou sommet).
     */
    ComposantGraphe(int id, int indice, String tag,Color couleur){
        this.identifiant = id;
        this.indice = indice;
        this.nom = tag;
        this.couleur = couleur;
    }

    // ACCESSEURS et MUTATEURS

    public int getId() {
        return identifiant;
    }

    public void setId(int identifiant) {
        this.identifiant = identifiant;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public String getTag() {
        return nom;
    }

    public void setTag(String nom) {
        this.nom = nom;
    }
}
