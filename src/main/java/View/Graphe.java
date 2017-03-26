package View;

import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Classe représentant la version graphique du graphe.
 * Permet ainsi de stocker les sommets et les arêtes.
 */
public class Graphe {

    /**
     * Représente le panneau où s'affichera le graphe.
     */
    private Pane canvas;

    /**
     * Représente se qui permettra de contenir le panneau permettant l'affichage du graphe.
     */
    private ZoomableScrollPane scrollPane;

    /**
     * Représente la liste des sommets.
     */
    private List<Sommet> sommets;

    /**
     * Représente la liste des arêtes.
     */
    private List<Arete> aretes;

    /**
     * Constructeur permettant d'initialiser l'aspect de l'affichage.
     */
    public Graphe() {
        canvas = new Pane();
        scrollPane = new ZoomableScrollPane(canvas);
        scrollPane.setBorder(Border.EMPTY);
        sommets = new ArrayList<Sommet>();
        aretes = new ArrayList<Arete>();
    }

    /**
     * Permet d'initialiser le graphe (View) un graphe grâce à une autre graphe (Model)
     * @param graphe Représente le graphe (Model) où sont stockés toutes les données sur les sommets et les arêtes.
     */
    public void chargerGraphe(Model.Graphe graphe) {

        for (Model.Sommet sommet : graphe.getSommets()) {
            Sommet sommet1 = new Sommet(
                    sommet.getId(),
                    sommet.getTag(),
                    sommet.getForme(),
                    sommet.getX(),
                    sommet.getY());
            if (!sommets.contains(sommet1)) {
                sommets.add(sommets.size(), sommet1);
            }
        }

        for (Map.Entry<Model.Arete, Pair<Model.Sommet, Model.Sommet>> e : graphe.getM_extremites().entrySet()) {

            Sommet s1 = rechercheSommetParId(e.getValue().getKey().getId());
            Sommet s2 = rechercheSommetParId(e.getValue().getValue().getId());

            aretes.add(new Arete(s1,s2));

        }

        canvas.getChildren().addAll(aretes);
        canvas.getChildren().addAll(sommets);
        scrollPane.updateScrollPane(canvas);
    }

    /**
     * Méthode permettant de rechercher un sommet via son id.
     * @param id_recherche Représente l'id à rechercher.
     * @return Retourne le sommet correspondant l'id en paramètre (existe obligatoirement)
     */
    public Sommet rechercheSommetParId(int id_recherche) {

        boolean trouve = false;
        int cptSommet = 0;

        while (!trouve && cptSommet < sommets.size()) {
            if (id_recherche == sommets.get(cptSommet).getIdGraphe()) {
                trouve = true;
            }
            else {
                ++cptSommet;
            }
        }

        return sommets.get(cptSommet);
    }

    /**
     * Méthode permettant de supprimer un sommet de la vue.
     * @param sommetASuppr Représente le sommet à supprimer.
     */
    public void suppressionSommet(Sommet sommetASuppr) {
        suppressionAreteEnFonctionDuSommetSuppr(sommetASuppr);
        sommets.remove(sommetASuppr);
        canvas.getChildren().remove(sommetASuppr);
    }

    /**
     * Méthode permettant de supprimer une arête lié à un sommet qu'on supprime.
     * @param sommetASuppr Représente le sommet que l'on cherche à supprimer.
     */
    private void suppressionAreteEnFonctionDuSommetSuppr(Sommet sommetASuppr) {
        ArrayList<Integer> indexAreteASuppr = trouverAretesViaSommet(sommetASuppr);
        int cptIndex = 0;

        for (Integer index : indexAreteASuppr) {
            canvas.getChildren().remove(aretes.get(index - cptIndex));
            aretes.remove((int) index - cptIndex);
            ++cptIndex;
        }
    }


    /**
     * Méthode permetant de retourner une liste d'index correspondant aux arêtes lié au sommet.
     * @param sommetLie Représente le sommet pour lequel on cherche les arêtes qui lui sont liées.
     * @return Retourne une liste d'index de la liste d'arêtes.
     */
    private ArrayList<Integer> trouverAretesViaSommet(Sommet sommetLie) {
        ArrayList<Integer> indexAretes = new ArrayList<Integer>();
        int indexArete = 0;
        for (Arete arete : aretes) {
            if (arete.getSource() == sommetLie || arete.getDestination() == sommetLie) {
                indexAretes.add(indexArete);
            }

            ++indexArete;
        }

        return indexAretes;
    }

    /**
     * Méthode permettant de mettre à jour les coordonnées des arêtes lorsque un sommet est modifié.
     * @param sommetLie Représente le sommet modifié.
     */
    public void misAJourAretes(Sommet sommetLie) {
        ArrayList<Integer> indexAretes = trouverAretesViaSommet(sommetLie);

        for (Integer index : indexAretes) {
            aretes.get(index).misAJourCoord();
        }
    }

    /**
     * Méthode permettant de supprimer une arête dans la liste des arêtes.
     * @param areteASuppr Représente l'arête à supprimer.
     */
    public void supprimerArete(Model.Arete areteASuppr) {

        boolean trouve = false;
        int cptArete = 0;

        while (cptArete < aretes.size() && ! trouve) {
            if (areteASuppr.getId() == aretes.get(cptArete).getIdGraphe()) {
                canvas.getChildren().remove(aretes.get(cptArete));
                aretes.remove(cptArete);
                trouve = true;
            }
            else {
                ++cptArete;
            }
        }
    }

    // ACCESSEUR ET MUTATEUR

    public Pane getCanvas() {
        return canvas;
    }

    public ZoomableScrollPane getScrollPane() {
        return scrollPane;
    }

    public List<Sommet> getSommets() {
        return sommets;
    }

    public List<Arete> getAretes() {
        return aretes;
    }
}
