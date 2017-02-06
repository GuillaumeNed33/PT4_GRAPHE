package sample.Model;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by audreylentilhac on 06/02/2017.
 */
public class Graphe {
    private static ArrayList<Sommet> m_sommets;
    private static ArrayList<Arete> m_aretes;
    private static HashMap<Sommet, ArrayList<Arete> > m_incidentes;
    private static HashMap<Arete, Pair<Sommet,Sommet>> m_extremites;
    public Graphe (String fichier) {
        if (fichier.contains(".dot"))
            lectureGraphe(fichier, 1);
        else
            lectureGraphe(fichier, 2);
    }

    private void lectureGraphe(String fichier, int type){
        String lecture = null;

        if (type == 1){
            chargerGrapheDOT(lecture);
        }
        else
            chargerGrapheGRAPHML(lecture);
    }

    private void chargerGrapheDOT(String fichier){

    }

    private void chargerGrapheGRAPHML(String fichier){

    }

}
