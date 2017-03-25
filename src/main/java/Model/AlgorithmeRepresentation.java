package Model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe permettant de disposer les sommets pour différentes représentations.
 */
public class AlgorithmeRepresentation {
    /**
     * Représente la marge entre chaque sommet du graphe.
     */
    private static final int MARGE = 10;

    /**
     *  Représente une valeur aléatoire.
     */
    private static Random random = new Random();

    /**
     * Représente le graphe auquel un algorithme de représentation sera appliqué.
     */
    private Graphe graphe;

    /**
     * Constructeur de l'algorithme de représentation d'un graphe.
     * @param graphe Représente le graphe qui initialisera de la classe AlgorithmeRepresentation.
     */
    public AlgorithmeRepresentation(Graphe graphe){
        this.graphe = graphe;
    }

    /**
     *  Permet une distribution uniforme rectangulaire des sommets.
     */
    public void distributionAleatoire(int largeurEcran, int hauteurEcran){
        for (Sommet sommet : graphe.getSommets()) {
            sommet.setX((random.nextFloat()*largeurEcran)+MARGE);
            sommet.setY((random.nextFloat()*hauteurEcran)+MARGE);
        }

    }

    /**
     * Permet une distribution circulaire des sommets.
     */
    public void distributionCirculaire(int largeurEcran, int hauteurEcran){
        float indice = 0;
        for (Sommet sommet : graphe.getSommets()) {
            float rayon = (float)1/2 * (float)hauteurEcran;
            double angle = 2. * Math.PI / (float)graphe.getSommets().size();
            sommet.setX((float)(largeurEcran/2. + rayon * Math.cos(angle*indice)));
            sommet.setY((float)(rayon + rayon * Math.sin(angle*indice)));
            indice ++;
        }
    }

    /**
     * Permet une distribution par modele de Forces.
     */
    public void distributionModeleForces(int largeurEcran, int hauteurEcran){
        //distributionAleatoire(largeurEcran, hauteurEcran);
        for (Sommet sommet : graphe.getSommets()) {
            float forceTotale = forceAttraction(sommet) + forceRepulsion(sommet);
            sommet.setX(sommet.getX() + forceTotale);
            sommet.setY(sommet.getY() + forceTotale);
        }
    }

    /**
     * Fonction calculant la force d'attraction qu'exerce chacun des voisins u sur un sommet s.
     * f(s) = Somme (log distance(u,s)).
     * @param sommet Représente le sommet qui exerce la force d'attraction sur ses sommets voisins.
     * @return La force d'attraction.
     */
    private float forceAttraction(Sommet sommet){
        ArrayList<Sommet> voisins = graphe.sommetsVoisins(sommet);
        float force = 0;
        if (voisins != null) {
            for (Sommet voisin : voisins) {
                double distance = Math.sqrt(Math.pow((double) (voisin.getX() - sommet.getX()), 2.) + Math.pow((double) (voisin.getY() - sommet.getY()), 2.));
                force += Math.log(distance);
            }
        }
        else{
            return 0;
        }
        return force;
    }

    /**
     * Fonction calculant la force de repulsion qu'exerce chacun des non voisins v sur un sommet s.
     * f(s) = Somme (1 / distance(u,s)^2).
     * @param sommet Représente le sommet sur lequel on doit appliquer une force de répulsion pour ses sommets non voisins.
     * @return Retourne la force de répulsion.
     */
    private float forceRepulsion(Sommet sommet){
        ArrayList<Sommet> voisins = graphe.sommetsNonVoisins(graphe.sommetsVoisins(sommet), sommet);
        float force = 0;
        if (voisins != null) {
            for (Sommet voisin : voisins) {
                double distanceCarre = Math.pow((double) (voisin.getX() - sommet.getX()), 2.) + Math.pow((double) (voisin.getY() - sommet.getY()), 2.);
                force += 1 / distanceCarre;
            }
        }
        else {
            return 0;
        }
        return force;
    }
}
