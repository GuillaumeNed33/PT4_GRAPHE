package Model;

import com.sun.glass.ui.Size;
import javafx.scene.paint.Color;

/**
 * Created by audreylentilhac on 18/03/2017.
 */
public class ComposantGraphe {
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

    public Size getTaille() {
        return taille;
    }

    public void setTaille(Size taille) {
        this.taille = taille;
    }

    public String getTag() {
        return nom;
    }

    public void setTag(String nom) {
        this.nom = nom;
    }

    protected int identifiant;
    protected int indice;
    protected Color couleur;
    protected Size taille;
    protected String nom;

    ComposantGraphe(int id, int i, String tag,Color c, Size t){
        this.identifiant = id;
        this.indice = i;
        this.nom = tag;
        this.couleur = c;
        this.taille = t;
    }
}
