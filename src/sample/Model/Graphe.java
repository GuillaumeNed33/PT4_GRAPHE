package sample.Model;

import javafx.util.Pair;

import java.util.ArrayList;
import com.sun.glass.ui.Size;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import static java.lang.Float.isFinite;
import static java.lang.Float.parseFloat;

/**
 * Created by audreylentilhac on 06/02/2017.
 */

/**
 *  Classe Graphe
 */
public class Graphe {
    /**
     * Représente le nom du graphe.
     */
    private static String m_name;

    /**
     * Représente la taille du graphe.
     */
    private static Size m_size;

    /**
     * Représente l'ensemble des sommets dans le graphe.
     */
    private static ArrayList<Sommet> m_sommets;

    /**
     * Représente l'ensemble des arete dans le graphe.
     */
    private static ArrayList<Arete> m_aretes;

    /**
     * Représente une map liant pour chaque sommet sa liste d'aretes.
     */
    private static HashMap<Sommet, ArrayList<Arete> > m_incidentes;

    /**
     * Représente une map liant pour chaque arete sa paire de sommets.
     */
    private static HashMap<Arete, Pair<Sommet,Sommet>> m_extremites;    // conteneur liant pour chaque arete sa paire de sommets


    /**
     * Constructeur de la classe Graphe lisant un fichier .DOT ou .GRAPHML.
     * @param fichier
     */
    public Graphe (String fichier) {
        if (fichier.contains(".dot")) {
            lectureGraphe(fichier, 1);
        }
        else {
            lectureGraphe(fichier, 2);
        }
    }

    /**
     * Constructeur par defaut de la classe Graphe.
     */
    public Graphe(){}

    /**
     * fonction
     * @param fichier Représente le path du fichier à charger.
     * @param type Représente le type de fichier à charger.
     */
    private void lectureGraphe(String fichier, int type){
        String lecture = null;
        if (type == 1){
            chargerGrapheDOT(lecture);
        }
        else {
            chargerGrapheGRAPHML(lecture);
        }
    }

    /**
     * Charge les fichiers .DOT.
     * @param fichier Représente le path du fichier à charger.
     */
    private void chargerGrapheDOT(String fichier){
        String chaine = "";
        try{
            InputStream ips=new FileInputStream(fichier);
            InputStreamReader ipsr=new InputStreamReader(ips);
            BufferedReader br=new BufferedReader(ipsr);
            String ligne = br.readLine();
            String [] decoup = ligne.split(" ");

            if(decoup[0].equals("graph") || decoup[0].equals("digraph") ){
                m_name = decoup[1];
            }
            else {
                throw new Exception("Error syntax in the .dot file : '"+fichier+"'.");
            }
            while ((ligne=br.readLine())!=null){
/*
                if(ligne.contains("size=")) {
                    decoup = ligne.split("\"");
                    String [] decoup2 = decoup[1].split(",");
                    m_size = new Size(Integer.parseInt(decoup2[0]), Integer.parseInt(decoup2[1]));
                    System.out.println(m_size);
                }
                */

                //GESTION DES ARRETES
                if(ligne.contains("->")) {
                    decoup = ligne.split("\"");
                    Sommet s1 = new Sommet(decoup[1]);
                    Sommet s2 = new Sommet(decoup[3]);
                    ajouterArete(s1,s2);
                    if(ligne.contains("[")) {
                        //decoup1 = ligne.split("\[");
                        String [] decoup2 = decoup[1].split("]");
                        //PROPRIETE CSS DE L'ARETE
                        String properties = decoup2[0];
                    }
                }

                System.out.println(ligne);
                chaine+=ligne+"\n";
            }
            br.close();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }

    /**
     * Charge les fichiers .GRAPHML.
     * @param fichier
     */
    private void chargerGrapheGRAPHML(String fichier) {

    }

    /**
     * Permet d'ajouter un sommet dans le graphe.
     * Le boolean de retour permet de gérer les erreurs d'ajouts (aspect graphique).
     * @param tag Représente le tag du sommet à ajouter dans le graphe.
     * @param coord_x_sommet Représente la coordonnée en x du sommet à ajouter dans le graphe.
     * @param coord_y_sommet Représente la coordonnée en y du sommet à ajouter dans le graphe.
     * @return Retourne vrai si l'ajout du sommet c'est fait ou faux dans le cas contraire.
     */
    public boolean ajouterSommet(String tag, float coord_x_sommet, float coord_y_sommet){

        if (verificationPossibiliteAjoutSommet(coord_x_sommet, coord_y_sommet)) {
            m_sommets.add(new Sommet(tag, coord_x_sommet, coord_y_sommet));
        }
        else {
            return false;
        }

        return true;
    }

    /**
     * Permet de vérifier que les coordonnées du sommet sont correctes et qu'il n'existe pas un sommet à ces coordonnées.
     * @param coord_y_sommet Représente la coordonnée en y, à vérifier, du sommet.
     * @param coord_x_sommet Représente la coordonnée en x, à vérifier, du sommet.
     * @return Retourne vrai on peut ajouter un nouveau sommet ou faux dans le cas contraire.
     */
    private boolean verificationPossibiliteAjoutSommet(float coord_x_sommet, float coord_y_sommet) {

        if(coord_x_sommet >= 0 && coord_y_sommet >= 0 && coord_x_sommet <= m_size.width && coord_y_sommet <= m_size.height) {
            Iterator<Sommet> iterateur_sommet = m_sommets.iterator();
            boolean trouver = false;

            while (!trouver && iterateur_sommet.hasNext()) {
                Sommet sommet_temp = iterateur_sommet.next();

                if (sommet_temp.getX() == coord_x_sommet && sommet_temp.getY() == coord_x_sommet) {
                    trouver = true;
                }
            }

            if (trouver) {
                return false;
            }
        }
        else {
            return false;
        }

        return true;
    }

    /**
     * Permet de supprimer un sommet du graphe.
     * @param sommet Représente le sommet à supprimer du graphe.
     */
    public void supprimerSommet(Sommet sommet){

        m_sommets.remove(sommet);

        for (Arete a: m_incidentes.get(sommet)) {
            m_extremites.remove(a);
        }

        m_incidentes.remove(sommet);
    }

    /**
     * Permet d'ajouter une arete au graphe et qui se lie à 2 sommets distinct.
     * Le boolean de retour permet de gérer les erreurs d'ajouts (aspect graphique).
     * @param sommet_1 Représente le premier sommet à lier avec l'arete.
     * @param sommet_2 Représente le second sommet à lier avec l'arete.
     * @return Retourne vrai si l'ajout de l'arete c'est fait ou faux dans le cas inverse.
     */
    public boolean ajouterArete(Sommet sommet_1, Sommet sommet_2) {

        if (verificationPossibiliteAjoutArete(sommet_1, sommet_2)) {
            Arete a = new Arete(sommet_1, sommet_2);
            m_aretes.add(a);

            Pair<Sommet, Sommet> sommets = new Pair<>(sommet_1, sommet_2);

            m_extremites.put(a, sommets);
            lierAreteAuSommet(a, sommet_1);
            lierAreteAuSommet(a, sommet_2);
        }
        else {
            return false;
        }

        return true;
    }

    /**
     * Permet de vérifier si les sommets en paramètre sont différents et si ils sont bien initialisé.
     * La condition de la création de l'arete se fait exclusivement grâce aux sommets.
     * @param sommet_1 Représente le premier sommet qui sera lié à l'arete.
     * @param sommet_2 Représente le second sommet qui sera lié à l'arete.
     * @return Retourne vrai si on peut ajouter une nouvelle arete ou faux dans le cas contraire.
     */
    private boolean verificationPossibiliteAjoutArete(Sommet sommet_1, Sommet sommet_2) {

        if (sommet_1.equals(sommet_2) && (sommet_1 == null || sommet_2 == null)) {
            return false;
        }

        return true;
    }

    /**
     * Permet de lier l'arete a un sommet.
     * @param arete Représente l'arete à lier.
     * @param sommet Représente le sommet auquel l'arete doit être liée.
     */
    private void lierAreteAuSommet (Arete arete, Sommet sommet) {

        ArrayList<Arete> aretesLocalesSommet = m_incidentes.get(sommet);
        if (aretesLocalesSommet.size() == 0) {
            ArrayList<Arete> setArete = new ArrayList<>();
            setArete.add(arete);
            m_incidentes.put(sommet, setArete);
        } else {
            aretesLocalesSommet.add(arete);
            m_incidentes.put(sommet, aretesLocalesSommet);
        }
    }

    /**
     * Permet de supprimer une arete du graphe.
     * @param arete Représente l'arete à supprimer.
     */
    public void supprimerArete(Arete arete) {

        Sommet sommet_1 = m_extremites.get(arete).getKey(); // Représente le premier sommet de la Pair dans la HashMap m_extremites
        Sommet sommet_2 = m_extremites.get(arete).getValue(); // Représente le second sommet de la Pair dans la HashMap m_extremites

        supprimerAretePourUnSommetSpecifique(arete, sommet_1);
        supprimerAretePourUnSommetSpecifique(arete, sommet_2);

        m_extremites.remove(arete);
    }

    /**
     * Supprime une arete mais pour un sommet spécifique du graphe.
     * @param arete Représente l'arete à supprimer du graphe.
     * @param sommet Représente le sommet qui est lié à l'arete à supprimer du graphe.
     */
    private void supprimerAretePourUnSommetSpecifique(Arete arete, Sommet sommet) {

        boolean trouve = false;
        Iterator<Arete> it = m_incidentes.get(sommet).iterator();

        while (!trouve && it.hasNext()) {
            Arete temp_arete = it.next();
            if (temp_arete.equals(arete)) {
                it.remove();
            }
        }
    }



    // Accesseur et Mutateurs

    public static ArrayList<Sommet> getM_sommets() {

        return m_sommets;
    }

    public static void setM_sommets(ArrayList<Sommet> m_sommets) {

        Graphe.m_sommets = m_sommets;
    }

    public static ArrayList<Arete> getM_aretes() {

        return m_aretes;
    }

    public static void setM_aretes(ArrayList<Arete> m_aretes) {

        Graphe.m_aretes = m_aretes;
    }

    public static HashMap<Sommet, ArrayList<Arete>> getM_incidentes() {

        return m_incidentes;
    }

    public static void setM_incidentes(HashMap<Sommet, ArrayList<Arete>> m_incidentes) {

        Graphe.m_incidentes = m_incidentes;
    }

    public static HashMap<Arete, Pair<Sommet, Sommet>> getM_extremites() {

        return m_extremites;
    }

    public static void setM_extremites(HashMap<Arete, Pair<Sommet, Sommet>> m_extremites) {

        Graphe.m_extremites = m_extremites;
    }

}
