package Model;

import com.sun.glass.ui.Size;
import javafx.scene.paint.Color;

/**
 * Created by audreylentilhac on 03/02/2017.
 */
public class Arete {

    /**
     * Représente le prochain id à utiliser.
     * il est static donc commun à l'ensemble des objets de la classe.
     */
    private static int idActuel = 0;

    /**
     * Représente l'id de l'arete.
     */
    private int id;

    /**
     * Représente le premier sommet auquel l'arete est liée.
     */
    private Sommet entree;


    /**
     * Représente le second sommet auquel l'arete est liée.
     */
    private Sommet sortie;

    /**
     * Représente la couleur de l'arete.
     */
    private Color couleurArete;

    /**
     * Représente le poids d'un arête
     */
    private int poids;

    private Size epaisseur;

    /**
     * Une arete ne peut se construire que grâce à 2 sommets.
     * Elle sera toujours valide (pas besoins de vérification car elle est créée via un bouton).
     * @param entree Ce sommet représente le premier sommet au quel l'arete est liée.
     * @param sortie Ce sommet représente le second sommet au quel l'arrete est liée.
     */
    public Arete(Sommet entree, Sommet sortie){
        this.entree = entree;
        this.sortie = sortie;
        id = ++idActuel;
        couleurArete = Color.web("000000");
        this.poids = 0;
    }

    /**
     * Une arête peut être construite avec un poids notamment pour ma coloration des arêtes
     * @param entree Ce sommet représente le premier sommet au quel l'arete est liée.
     * @param sortie Ce sommet représente le second sommet au quel l'arrete est liée.
     * @param poids valeur entière représentant le poids de l'arête
     */
    public Arete(Sommet entree, Sommet sortie, int poids){
        this.entree = entree;
        this.sortie = sortie;
        id = idActuel++;
        couleurArete = Color.web("000000");
        this.poids = poids;
    }


    // Accesseur et Mutateurs

    public int id() { return id; }

    public Color getCouleurArete() {
        return couleurArete;
    }

    public void setCouleurArete(Color couleurArete) {
        this.couleurArete = couleurArete;
    }

    public int getPoids() { return poids; }

    public void setPoids(int poids) { this.poids = poids; }

    public Size getEpaisseur() {
        return epaisseur;
    }

    public void setEpaisseur(Size epaisseur) {
        this.epaisseur = epaisseur;
    }
    
    public Sommet getEntree() {
        return entree;
    }

    public void setEntree(Sommet entree) {
        this.entree = entree;
    }

    public Sommet getSortie() {
        return sortie;
    }

    public void setSortie(Sommet sortie) {
        this.sortie = sortie;
    }
}
