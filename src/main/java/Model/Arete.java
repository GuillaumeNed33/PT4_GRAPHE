package Model;

import com.sun.glass.ui.Size;
import javafx.scene.paint.Color;

/**
 * Created by audreylentilhac on 03/02/2017.
 */
public class Arete extends ComposantGraphe {

    /**
     * Représente le prochain id à utiliser.
     * il est static donc commun à l'ensemble des objets de la classe.
     */
    private static int idActuel = 0;

    /**
     * Représente le premier sommet auquel l'arete est liée.
     */
    private Sommet entree;

    /**
     * Représente le second sommet auquel l'arete est liée.
     */
    private Sommet sortie;


    /**
     * Représente la couleur de l'arete en string.
     */
    private String couleurAreteStr;


    /**
     * Une arete ne peut se construire que grâce à 2 sommets.
     * Elle sera toujours valide (pas besoins de vérification car elle est créée via un bouton).
     * @param entree Ce sommet représente le premier sommet au quel l'arete est liée.
     * @param sortie Ce sommet représente le second sommet au quel l'arrete est liée.
     */
    public Arete(Sommet entree, Sommet sortie){
        super(++idActuel, 1, "", Color.BLACK, new Size(1, 1));
        this.entree = entree;
        this.sortie = sortie;
        couleurAreteStr = "Black";
    }

    /**
     * Une arête peut être construite avec un poids notamment pour la coloration des arêtes
     * @param entree Ce sommet représente le premier sommet au quel l'arete est liée.
     * @param sortie Ce sommet représente le second sommet au quel l'arrete est liée.
     * @param poids valeur entière représentant le poids de l'arête
     */
    public Arete(Sommet entree, Sommet sortie, int poids){
        super(++idActuel, poids, "", Color.BLACK, new Size(1,1));
        this.entree = entree;
        this.sortie = sortie;
        couleurAreteStr = "Black";
    }

    /**
     * Une arête peut être construite avec un nom
     * @param entree Ce sommet représente le premier sommet au quel l'arete est liée.
     * @param sortie Ce sommet représente le second sommet au quel l'arrete est liée.
     * @param tag String représentant le nom de l'arête
     */
    public Arete(Sommet entree, Sommet sortie, String tag){
        super(++idActuel, 1, tag, Color.BLACK, new Size(1,1));
        this.entree = entree;
        this.sortie = sortie;
        couleurAreteStr = "Black";
    }


    // Accesseur et Mutateurs

    public String getCouleurAreteStr() {
        return couleurAreteStr;
    }

    public void setCouleurAreteStr(String couleurAreteStr) {
        this.couleurAreteStr = couleurAreteStr;
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
