package View;

import Model.Forme_Sommet;
import javafx.scene.Group;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Thomas on 13/03/2017.
 */
public class Graphe {
    private Group canvas;
    private ZoomableScrollPane scrollPane;
    private List<Sommet> sommets;
    private List<Arete> aretes;
    public Graphe() {
        canvas = new Group();
        scrollPane = new ZoomableScrollPane(canvas);
        sommets = new ArrayList<Sommet>();
        aretes = new ArrayList<Arete>();
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
    }

    public void chargerGraphe(Model.Graphe graphe) {
        for (Map.Entry<Model.Arete, Pair<Model.Sommet, Model.Sommet>> e : graphe.getM_extremites().entrySet()) {
            //sommets.add(new Sommet(e.getKey().getId(),e.getKey()))
        }
    }

    public Group getCanvas() {
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
