package View;

import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Thomas on 13/03/2017.
 */
public class Graphe {
    private Pane canvas;
    private ZoomableScrollPane scrollPane;
    private List<Sommet> sommets;
    private List<Arete> aretes;
    public Graphe() {
        canvas = new Pane();
        scrollPane = new ZoomableScrollPane(canvas);
        scrollPane.setBorder(Border.EMPTY);
        sommets = new ArrayList<Sommet>();
        aretes = new ArrayList<Arete>();
    }

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
            Sommet s1 = null;
            Sommet s2 = null;
            /*s1 = new Sommet(e.getValue().getKey().getTag(),
                    e.getValue().getKey().getForme(),
                    e.getValue().getKey().getX(),
                    e.getValue().getKey().getY());
            s2 = new Sommet(e.getValue().getValue().getTag(),
                    e.getValue().getValue().getForme(),
                    e.getValue().getValue().getX(),
                    e.getValue().getValue().getY());*/

            s1 = rechercheSommetParId(e.getValue().getKey().getId());
            s2 = rechercheSommetParId(e.getValue().getValue().getId());

            aretes.add(new Arete(s1,s2));

        }

        canvas.getChildren().addAll(aretes);
        canvas.getChildren().addAll(sommets);
        scrollPane.updateScrollPane(canvas);
    }

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

    public void suppressionSommet(Sommet sommetASuppr) {
        sommets.remove(sommetASuppr);
        canvas.getChildren().remove(sommetASuppr);
    }

    public void suppressionAreteEnFonctionDuSommetSuppr(Sommet sommetASuppr) {
        ArrayList<Integer> indexAreteASuppr = new ArrayList<Integer>();
        int indexArete = 0;
        for (Arete arete : aretes) {
            if (arete.getSource() == sommetASuppr || arete.getDestination() == sommetASuppr) {
                indexAreteASuppr.add(indexArete);
            }

            ++indexArete;
        }

        for (Integer index : indexAreteASuppr) {
            canvas.getChildren().remove(aretes.get(index));
            aretes.remove(index);
        }
    }

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
