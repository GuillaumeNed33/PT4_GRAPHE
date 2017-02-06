package sample.Model;

/**
 * Created by audreylentilhac on 03/02/2017.
 */
public class Arete {

    /**
     * Représente le prochain id à utiliser.
     * il est static donc commun à l'ensemble des objets de la classe.
     */
    private static int m_idActuel = 0;

    /**
     * Représente l'id de l'arete.
     */
    private int m_id;

    /**
     * Représente le premier sommet auquel l'arete est liée.
     */
    private Sommet entree;

    /**
     * Représente le second sommet auquel l'arete est liée.
     */
    private Sommet sortie;

    /**
     * Une arete ne peut se construire que grâce à 2 sommets.
     * Elle sera toujours valide (pas besoins de vérification car elle est créée via un bouton).
     * @param sommet_1 Ce Sommet représente le premier sommet au quel l'arete est liée.
     * @param sommet_2 Ce sommet représente le second sommet au quel l'arrete est liée.
     */
    public Arete(Sommet sommet_1, Sommet sommet_2){
        entree = sommet_1;
        sortie = sommet_2;
        m_id = m_idActuel++;
    }


    // Accesseur et Mutateurs

    public int id(){

        return m_id;
    }

    public void supprimer() {
        m_id=-1;
    }
}
