package sample.Model;

import com.sun.glass.ui.Size;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Float.parseFloat;

/**
 * Created by audreylentilhac on 06/02/2017.
 */
public class Graphe {
    private static String m_name;
    private static Size m_size;
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
        String lecture = fichier;
        if (type == 1){
            chargerGrapheDOT(lecture);
        }
        else
            chargerGrapheGRAPHML(lecture);
    }

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

    private void chargerGrapheGRAPHML(String fichier) {

    }

    public void ajouterSommet(Sommet s){
        m_sommets.add(s);
    }

    public void supprimetSommet(Sommet s){
        m_sommets.remove(s);
        m_incidentes.remove(s);
    }

    public void ajouterArete(Sommet n1, Sommet n2){
        Arete a = new Arete(true, n1, n2);
        m_aretes.add(a);
        Pair<Sommet,Sommet> sommets = new Pair<>(n1, n2);
        m_extremites.put(a, sommets);
        lierAreteAuSommet(a, n1);
        lierAreteAuSommet(a, n2);
    }

    private void lierAreteAuSommet (Arete a, Sommet s){
        ArrayList<Arete> aretesLocalesSommet = m_incidentes.get(s);
        if (aretesLocalesSommet.size() == 0){
            ArrayList<Arete> setArete = new ArrayList<>();
            setArete.add(a);
            m_incidentes.put(s, setArete);
        }
        else {
            aretesLocalesSommet.add(a);
            m_incidentes.put(s, aretesLocalesSommet);
        }
    }

    public static ArrayList<Sommet> getM_sommets() { return m_sommets; }

    public static void setM_sommets(ArrayList<Sommet> m_sommets) { Graphe.m_sommets = m_sommets; }

    public static ArrayList<Arete> getM_aretes() { return m_aretes;
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
