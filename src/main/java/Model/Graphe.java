package Model;

import com.sun.glass.ui.Size;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

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
    public static String nom;

    /**
     * Représente le chemin du fichier.
     */
    public static String cheminFichier;

    /**
     * Représente la taille du graphe.
     */
    public static Size taille;

    /**
     * Représente l'ensemble des sommets dans le graphe.
     */
    public static ArrayList<Sommet> sommets;

    /**
     * Représente l'ensemble des arete dans le graphe.
     */
    public static ArrayList<Arete> aretes;

    /**
     * Représente une map liant pour chaque sommet sa liste d'aretes.
     */
    public static HashMap<Sommet, ArrayList<Arete> > incidentes;

    /**
     * Représente une map liant pour chaque arete sa paire de sommets.
     */
    public static HashMap<Arete, Pair<Sommet,Sommet>> extremites;    // conteneur liant pour chaque arete sa paire de sommets

    /**
     * Représente l'ensemble des clefs de style (definies dans les fichier .graphml).
     */
    public static ArrayList<KeyStyleGRAPHML> keyGML;

    /**
     *  Représente une valeur aléatoire.
     */
    public static Random rand = new Random();

    /**
     * Représente l'algorithme de représentation du graphe
     */
    public static AlgorithmeRepresentation algorithmeRepresentation;

    /**
     * Constructeur de la classe Graphe lisant un fichier .DOT ou .GRAPHML.
     * @param fichier
     */
    public Graphe (String fichier) {
        nom = "Mon Joli Graphe";
        cheminFichier = fichier;
        taille = new Size(10,10);
        sommets = new ArrayList<Sommet>();
        aretes = new ArrayList<Arete>();
        incidentes = new HashMap<Sommet, ArrayList<Arete>>();
        extremites = new HashMap<Arete, Pair<Sommet, Sommet>>();
        algorithmeRepresentation = new AlgorithmeRepresentation(this);
        
        if (cheminFichier.contains(".dot")) {
            chargerGrapheDOT();
        }
        else if (cheminFichier.contains(".graphml")) {
            chargerGrapheGRAPHML(fichier);
        }
    }

    /**
     * Constructeur par defaut de la classe Graphe.
     */
    public Graphe(){
        nom = "Mon Joli Graphe";
        taille = new Size(10,10);
        sommets = new ArrayList<Sommet>();
        aretes = new ArrayList<Arete>();
        incidentes = new HashMap<Sommet, ArrayList<Arete>>();
        extremites = new HashMap<Arete, Pair<Sommet, Sommet>>();
        algorithmeRepresentation = new AlgorithmeRepresentation(this);
    }

    /**
     * Charge les fichiers .DOT.
     */
    private void chargerGrapheDOT(){
        String chaine = "";
        try{
            InputStream ips=new FileInputStream(cheminFichier);
            InputStreamReader ipsr=new InputStreamReader(ips);
            BufferedReader br=new BufferedReader(ipsr);
            String ligne = br.readLine();
            String [] decoup = ligne.split(" ");

            if(decoup[0].equals("graph") || decoup[0].equals("digraph") ){
                nom = decoup[1];
            }
            else {
                throw new Exception("Error syntax in the .dot file : '"+cheminFichier+"'.");
            }
            while ((ligne=br.readLine())!=null){
                if(ligne.contains("taille=")) {
                    decoup = ligne.split("\"");
                    String [] decoup2 = decoup[1].split(",");
                    //taille = new Size(Integer.parseInt(decoup2[0]), Integer.parseInt(decoup2[1]));
                    //  System.out.println(taille);
                }

                //GESTION DES NOEUDS
                if(!ligne.contains(" -> ") &&
                        !ligne.contains(" -- ") &&
                        !ligne.contains("taille")) {
                    if(ligne.contains("[")) {
                        String[] node = ligne.split("\\[");
                        Sommet s = new Sommet(node[0].trim().replaceAll("\"",""));
                    }
                    else {
                        String node = ligne.trim().replaceAll("\"", "");
                        Sommet s = new Sommet(node);
                    }

                    //PROPRIETE
                    if(ligne.contains("[")) {

                        /*** http://graphviz.org/Documentation/dotguide.pdf ***/
                        if(ligne.contains("type")) {
                            String [] type = ligne.split("type=");
                            String [] typeFinal = type[1].split(" ");
                        }
                        if(ligne.contains("style")) {
                            String [] style = ligne.split("style=");
                            String [] styleFinal = style[1].split(" ");
                        }
                        if(ligne.contains("color")) {
                            String [] color = ligne.split("color=");
                            String [] colorFinal = color[1].split(" ");
                        }
                        if(ligne.contains("fontcolor")) {
                            String [] fontcolor = ligne.split("fontcolor=");
                            String [] fontcolorFinal = fontcolor[1].split(" ");
                        }
                        if(ligne.contains("label")) {
                            String [] label = ligne.split("label=\"");
                            String [] labelFinal = label[1].split("\"");
                        }
                    }
                }

                if(nom == "graph") {
                    //GESTION DES ARETES
                    if(ligne.contains("--") && !ligne.contains("label=\"--")) {
                        decoup = ligne.split("->");
                        String [] name2 = decoup[1].split("\\[");
                        Sommet s1 = new Sommet(decoup[0].trim().replaceAll("\"", ""));
                        Sommet s2 = new Sommet(name2[0].trim().replaceAll("\"", ""));
                        ajouterArete(s1,s2);

                        //PROPRIETE
                        if(ligne.contains("[")) {
                            if(ligne.contains("type")) {
                                String [] type = ligne.split("type=");
                                String [] typeFinal = type[1].split(" ");
                            }
                            if(ligne.contains("style")) {
                                String [] style = ligne.split("style=");
                                String [] styleFinal = style[1].split(" ");
                            }
                            if(ligne.contains("color")) {
                                String [] color = ligne.split("color=");
                                String [] colorFinal = color[1].split(" ");
                            }
                            if(ligne.contains("fontcolor")) {
                                String [] fontcolor = ligne.split("fontcolor=");
                                String [] fontcolorFinal = fontcolor[1].split(" ");
                            }
                            if(ligne.contains("label")) {
                                String [] label = ligne.split("label=\"");
                                String [] labelFinal = label[1].split("\"");
                            }
                        }
                    }
                }
                else {
                    //GESTION DES ARETES DIRIGEES
                    if(ligne.contains("->") && !ligne.contains("label=\"->")) {
                        decoup = ligne.split("->");
                        String [] name2 = decoup[1].split("\\[");
                        Sommet s1 = new Sommet(decoup[0].trim().replaceAll("\"", ""));
                        Sommet s2 = new Sommet(name2[0].trim().replaceAll("\"", ""));
                        ajouterArete(s1,s2);

                        //PROPRIETE
                        /*** http://graphviz.org/Documentation/dotguide.pdf ***/
                        if(ligne.contains("[")) {
                            if(ligne.contains("type")) {
                                String [] type = ligne.split("type=");
                                String [] typeFinal = type[1].split(" ");
                            }
                            if(ligne.contains("style")) {
                                String [] style = ligne.split("style=");
                                String [] styleFinal = style[1].split(" ");
                            }
                            if(ligne.contains("color")) {
                                String [] color = ligne.split("color=");
                                String [] colorFinal = color[1].split(" ");
                            }
                            if(ligne.contains("fontcolor")) {
                                String [] fontcolor = ligne.split("fontcolor=");
                                String [] fontcolorFinal = fontcolor[1].split(" ");
                            }
                            if(ligne.contains("label")) {
                                String [] label = ligne.split("label=\"");
                                String [] labelFinal = label[1].split("\"");
                            }
                        }
                    }
                }
                // System.out.println(ligne);
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
        String chaine = "";
        keyGML = new ArrayList<KeyStyleGRAPHML>();
        try {
            InputStream ips = new FileInputStream(fichier);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            boolean directedGraph = false;

            while ((ligne = br.readLine()) != null) {
                if (ligne.contains("<key")) {
                    String [] recupId = ligne.split("id=\"");
                    String id = recupId[1].split("\"")[0];

                    String [] recupType = ligne.split("for=\"");
                    String type = recupType[1].split("\"")[0]; //node or edge

                    String [] recupAttributeName = ligne.split("attr.name=\"");
                    String keyName = recupAttributeName[1].split("\"")[0];

                    String typeAttribute="";
                    if(keyName == "color") {
                        ligne = br.readLine();
                        String[] recupAttributeType = ligne.split("<default>");
                        typeAttribute = recupAttributeType[1].split("<")[0];
                    }
                    else {
                        String[] recupAttributeType = ligne.split("attr.type=\"");
                        typeAttribute = recupAttributeType[1].split("\"")[0];
                    }
                    keyGML.add(new KeyStyleGRAPHML(id,type, keyName, typeAttribute));
                }

                else if (ligne.contains("<graph ")) {
                    String [] recupId = ligne.split("id=\"");
                    nom = recupId[1].split("\"")[0];
                    String [] recupType = ligne.split("edgedefault=\"");
                    String typeArete = recupType[1].split("\"")[0];
                    if(typeArete.equals("undirected"))
                        directedGraph = false;
                    else
                        directedGraph = true;
                }

                else if (ligne.contains("<node-style ")) {
                    String [] recupId = ligne.split("id=\"");
                    String id = recupId[1].split("\"")[0];

                    if(br.readLine().contains("<data")) {

                    }
                }

                else if (ligne.contains("<node ")) {

                    String [] recupId = ligne.split("id=\"");
                    String id = recupId[1].split("\"")[0];
                    Size tailleFenetre = new Size(10,10);
                    ajouterSommet(new Sommet(id), tailleFenetre);
                }

                else if (ligne.contains("<edge ")) {
                    boolean directedArete;
                    if(ligne.contains("directed=\"true\""))
                        directedArete = true;
                    else if(ligne.contains("directed=\"false\""))
                        directedArete = false;
                    else
                        directedArete = directedGraph;

                    String [] recupSource = ligne.split("source=\"");
                    String source = recupSource[1].split("\"")[0];

                    String [] recupTarget = ligne.split("target=\"");
                    String dest = recupTarget[1].split("\"")[0];

                    if(findSommet(source) != null && findSommet(dest) != null)
                        ajouterArete(findSommet(source), findSommet(dest));
                }
            }
            br.close();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }

    /**
     * Permet de sauvegarde le graphe en cours.
     * Le choix de l'algotithme se fait en fonction du format choisi.
     * @param chemin_sauvegarde Représente le chemin où sera sauvegardé le fichier au format .dot ou .graphml.
     * @return Retourne vrai si la sauvegarde c'est bien passé ou faux dans le cas contraire.
     */
    public boolean sauvegarderGraphe (String chemin_sauvegarde) {
        if (chemin_sauvegarde.contains(".dot")) {
            return sauvegarderGrapheDot(chemin_sauvegarde);
        }
        else if (chemin_sauvegarde.contains(".graphml")) {
            return sauvegarderGrapheGraphml(chemin_sauvegarde);
        }

        return false;
    }


    /**
     * Permet de sauvegarder un graphe au format .dot.
     * @param chemin_sauvegarde Représente le chemin où sera sauvegardé le fichier au format .dot.
     * @return Retourne vrai si la sauvegarde c'est bien passé ou faux dans le cas contraire.
     */
    private boolean sauvegarderGrapheDot (String chemin_sauvegarde) {

        try {
            FileWriter fileWriter = new FileWriter (chemin_sauvegarde);
            BufferedWriter bufferedWriter = new BufferedWriter (fileWriter);
            PrintWriter fichierSortie = new PrintWriter (bufferedWriter);

            //Les specs détaillés indique que se sont des graphs non orientés
            fichierSortie.println ("graph \"" + nom + "\" {");


            fichierSortie.println("\ttaille=\"" + taille.width + "," + taille.height + "\"");

            if (!sommets.isEmpty()) {

                for (Sommet sommet : sommets) {
                    fichierSortie.println("\t\"node" + sommet.getId() + "\" [ label=\"" + sommet.getTag() + "\" shape=\"" + sommet.getForme() + "\" ];");
                }
            }

            if (!extremites.isEmpty() && !aretes.isEmpty()) {
                for(Arete arete : aretes) {
                    String temp = "\t\"node" + extremites.get(arete).getKey().getId() + "\" ->  \"node" + extremites.get(arete).getKey().getId() + "\" " +
                            "[ color=" + arete.getCouleurArete().toString();


                    if (!arete.getTag().equals("")) {
                        temp += " label=\"" + arete.getTag() + "\" ];";
                    }
                    else {
                        temp += " ];";
                    }
                    fichierSortie.println(temp);
                }
            }

            fichierSortie.println("}");

            fichierSortie.close();
            bufferedWriter.close();
            fileWriter.close();

            return true;
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

        return false;
    }


    /**
     * Permet de sauvegarder un graphe au format .graphml.
     * @param chemin_sauvegarde Représente le chemin où sera sauvegardé le fichier au format .graphml.
     * @return Retour vrai si la sauvegarde c'est bien passé ou faux dans le cas contraire.
     */
    private boolean sauvegarderGrapheGraphml (String chemin_sauvegarde) {
        // TODO Essayer de se mettre d'accord pour les node-syle et key
        try {
            FileWriter fileWriter = new FileWriter (chemin_sauvegarde);
            BufferedWriter bufferedWriter = new BufferedWriter (fileWriter);
            PrintWriter fichierSortie = new PrintWriter (bufferedWriter);

            fichierSortie.println ("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<graphml xmlns=\"http://graphml.graphdrawing.org/xmlns\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://graphml.graphdrawing.org/xmlns/1.0/graphml.xsd\">");


            fichierSortie.println("\t<graph id=\""+ nom +"\" edgedefault=\"undirected\">");

            if (!sommets.isEmpty()) {
                for (Sommet sommet : sommets) {
                    fichierSortie.println("\t\t< node id=\"" + sommet.getTag() + "\" style=\"\" />");
                }
            }

            if (!extremites.isEmpty() && !aretes.isEmpty()) {
                for(Arete arete : aretes) {
                    fichierSortie.println("\t\t< edge id=\"" + arete.getTag() + "\" source=\""+ extremites.get(arete).getKey().getTag() +"\" target=\"" + extremites.get(arete).getValue().getTag() + "\" />");

                }
            }

            fichierSortie.println("\t</graph>");
            fichierSortie.println("</graphml>");


            fichierSortie.close();
            bufferedWriter.close();
            fileWriter.close();

            return true;
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

        return false;
    }

    private Sommet findSommet(String source) {
        boolean trouve = false;
        int i = 0;
        Sommet res = null;
        while(!trouve && i < sommets.size()) {
            if(sommets.get(i).getTag().equals(source)) {
                trouve = true;
                res = sommets.get(i);
            }
            else
                i++;
        }
        return res;
    }


    /**
     * Permet d'obtenir les sommets voisins d'un sommet.
     * @param sommet_origine Représente le sommet de référence pour déterminer si les autres sommets sont voisins.
     * @return Retourne la liste des sommets voisins.
     */
    public ArrayList<Sommet> sommmetsVoisins(Sommet sommet_origine){
        ArrayList<Sommet> voisinage = null;
        for(Arete arete : incidentes.get(sommet_origine)){
            voisinage.add(source(arete).getTag() == sommet_origine.getTag() ? destination(arete) : source(arete));
        }
        return voisinage;
    }

    /**
     * Permet d'obtenir le sommet source d'une arête.
     * @param arete Représente l'arete sur laquelle on cherche le sommet source.
     * @return Retourne le sommet source.
     */
    public Sommet source(Arete arete){

        return extremites.get(arete).getKey();
    }

    /**
     * Permet d'obtenir le sommet destination d'une arête.
     * @param arete Représente l'arete sur laquelle on cherche le sommet destination.
     * @return Retourne le sommet de destination.
     */
    public Sommet destination(Arete arete){

        return extremites.get(arete).getValue();
    }

    /**
     * Permet d'obtenir la liste des sommets non voisins.
     * @param voisins Représente la liste de sommets de référence pour déterminer les sommets n'étant pas voisins.
     * @return Retourne la liste des sommets non voisins.
     */
    public ArrayList<Sommet> sommetsNonVoisins(ArrayList<Sommet> voisins){
        ArrayList<Sommet> nonVoisins = null;
        for (Sommet s: sommets) {
            if (!voisins.contains(s))
                nonVoisins.add(s);
        }
        return nonVoisins;
    }

    /**
     * Permet d'ajouter un sommet dans le graphe.
     * Le boolean de retour permet de gérer les erreurs d'ajouts (aspect graphique).
     * @param sommet Représente le sommet à ajouter.
     * @param tailleFenetre Représente la taille de la fenêtre d'affichage.
     * @return Retourne vrai si l'ajout du sommet c'est fait ou faux dans le cas contraire.
     */
    public boolean ajouterSommet(Sommet sommet, Size tailleFenetre){

        if (verificationPossibiliteAjoutSommet(sommet.getX(), sommet.getY(), tailleFenetre)) {
            sommets.add(sommet);
            incidentes.put(sommet, new ArrayList<Arete>());
        }
        else {
            return false;
        }

        return true;
    }

    /**
     * Permet d'ajouter des sommets qui viennent de fichiers (.dot ou .graphml).
     * Nous n'avons pas besoins de vérifier les coordonnées car un algorithme de représentation placera les sommets.
     * @param sommet Représente le sommet à ajouter.
     */
    public void ajouterSommetViaFichier(Sommet sommet){

        sommets.add(sommet);
        incidentes.put(sommet, new ArrayList<Arete>());
    }

    /**
     * Permet de vérifier que les coordonnées du sommet sont correctes et qu'il n'existe pas un sommet à ces coordonnées.
     * @param coord_y_sommet Représente la coordonnée en y, à vérifier, du sommet.
     * @param coord_x_sommet Représente la coordonnée en x, à vérifier, du sommet.
     * @return Retourne vrai on peut ajouter un nouveau sommet ou faux dans le cas contraire.
     */
    private boolean verificationPossibiliteAjoutSommet(float coord_x_sommet, float coord_y_sommet, Size tailleFentre) {

        if (verificationCoordonneesValide(coord_x_sommet, coord_y_sommet, tailleFentre)) {
            Iterator<Sommet> iterateur_sommet = sommets.iterator();
            boolean trouver = false;

            while (!trouver && iterateur_sommet.hasNext()) {
                Sommet sommet_temp = iterateur_sommet.next();

                if (sommet_temp.getX() == coord_x_sommet && sommet_temp.getY() == coord_y_sommet) {
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
     * Permet de vérifier si le sommet peut-être déplacé et si c'est le cas alors effectuer le déplacement.
     * Le boolean de retour permet de gérer les erreurs d'ajouts (aspect graphique).
     * @param sommet Représente le sommet à déplacer.
     * @param nouvelle_coord_x Représente la nouvelle coordonnée en x du sommet.
     * @param nouvelle_coord_y Représente la nouvelle coordonnée en y du sommet.
     * @return Retourne vrai si le déplacement c'est bien effectué ou faux dans le cas contraire.
     */
    public boolean deplacerSommet(Sommet sommet, float nouvelle_coord_x, float nouvelle_coord_y, Size tailleFenetre) {
        if (!sommet.equals(null) && verificationCoordonneesValide(nouvelle_coord_x, nouvelle_coord_y, tailleFenetre)) {
            sommet.setX(nouvelle_coord_x);
            sommet.setY(nouvelle_coord_y);
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

        if(!sommet.equals(null)) {

            if (incidentes.containsKey(sommet)) {

                // Obligé de passer par une méthode car problème d'accès concurrents sur incidentes.
                int cpt = 0;
                int size = incidentes.get(sommet).size();

                while (cpt < size) {
                    supprimerArete(incidentes.get(sommet).get(0));
                    ++cpt;
                }

                incidentes.remove(sommet);
            }

            sommets.remove(sommet);
            boolean tempAretesASupprimer = incidentes.isEmpty();;
        }
    }

    /**
     * Permet d'ajouter une arete au graphe et qui se lie à 2 sommets distinct.
     * Le boolean de retour permet de gérer les erreurs d'ajouts (aspect graphique).
     * @param sommet_1 Représente le premier sommet à lier avec l'arete.
     * @param sommet_2 Représente le second sommet à lier avec l'arete.
     * @return Retourne vrai si l'ajout de l'arete ce fait ou faux dans le cas contraire.
     */
    public boolean ajouterArete(Sommet sommet_1, Sommet sommet_2) {

        if (verificationPossibiliteAjoutArete(sommet_1, sommet_2)) {
            Arete arete = new Arete(sommet_1, sommet_2);
            aretes.add(arete);

            extremites.put(arete, new Pair<Sommet,Sommet>(sommet_1, sommet_2));
            lierAreteAuSommet(arete, sommet_1);
            lierAreteAuSommet(arete, sommet_2);
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

        if (sommet_1.equals(sommet_2) || (sommet_1.equals(null) || sommet_2.equals(null)) || verificationDoublonArete(sommet_1, sommet_2)) {
                return false;
        }

        return true;
    }

    /**
     * Permet de vérifier s'il existe une arete entre 2 sommets.
     * @param sommet_1 Représente le premier sommet pour la vérification.
     * @param sommet_2 Représente le second sommet pour la vérification.
     * @return Retourne vrai si il existe une arete entre les 2 sommets et faux dans le cas contraire.
     */
    private boolean verificationDoublonArete(Sommet sommet_1, Sommet sommet_2) {

        for(Arete arete : incidentes.get(sommet_1)) {
            if (incidentes.get(sommet_2).contains(arete)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Permet de lier l'arete à un sommet.
     * @param arete Représente l'arete à lier.
     * @param sommet Représente le sommet auquel l'arete doit être liée.
     */
    private void lierAreteAuSommet (Arete arete, Sommet sommet) {

        if (!incidentes.containsKey(sommet)) {

            ArrayList<Arete> setArete = new ArrayList<Arete>();
            setArete.add(arete);
            incidentes.put(sommet, setArete);
        } else {
            incidentes.get(sommet).add(arete);
        }
    }

    /**
     * Permet de supprimer une arete du graphe.
     * @param arete Représente l'arete à supprimer.
     */
    public void supprimerArete(Arete arete) {

        if(!arete.equals(null)) {

            incidentes.get(extremites.get(arete).getKey()).remove(arete); // Représente le premier sommet de la Pair dans la HashMap extremites
            incidentes.get(extremites.get(arete).getValue()).remove(arete); // Représente le second sommet de la Pair dans la HashMap extremites

            extremites.remove(arete);
            aretes.remove(arete);
        }
    }

    /**
     * Permet de savoir si les coordonnées en paramètre son valide dans la fenêtre d'affichage du graphe.
     * @param coord_x Représente la coordonnée x à vérifier.
     * @param coord_y Représente la coordonnée y à vérifier.
     * @return Retourne vrai si les coordonnées sont valide ou faux le cas contraire.
     */
    public boolean verificationCoordonneesValide(float coord_x, float coord_y, Size tailleFenetre) {
        if(coord_x >= 0 && coord_y >= 0 && coord_x <= tailleFenetre.width && coord_y <= tailleFenetre.height) {
            return true;
        }

        return false;
    }

    /**
     * Affecte le degré du sommet (son nombre d'arêtes) à l'indice du sommet
     * @param s
     */
    public static void setIndiceDegre(Sommet s){
        s.setIndice(incidentes.get(s).size());
    }

    /**
     * Affecte une valeur aléatoire à l'indice du sommet
     * @param s
     */
    public static void setIndiceAleatoire (Sommet s){
        s.setIndice(rand.nextInt());
    }

    /**
     * Affecte pour chaque sommet du graphe son degré à son indice
     */
    public static void setIndiceDegre(){
        for (Sommet s: sommets) {
            setIndiceDegre(s);
        }
    }

    /**
     *
     * Affecte pour chaque sommet du graphe une valeur aléatoire à son indice
     */
    public static void setIndiceAleatoire(){
        for (Sommet s: sommets) {
            setIndiceAleatoire(s);
        }
    }

    /**
     * Fonction récupérant l'indice maximal de tous les sommets du graphe
     * @return
     */
    public int indiceMaxSommet(){
        int i = sommets.size()-1;
        int max = sommets.get(0).getIndice();
        while(--i >= 0) {
            max = (sommets.get(i).getIndice() > max) ? sommets.get(i).getIndice() : max;
        }

        return max;
    }

    /**
     * Fonction récupérant l'indice minimal de tous les sommets du graphe
     * @return
     */
    public int indiceMinSommet(){
        int i = sommets.size() - 1;
        int min = sommets.get(0).getIndice();
        while(--i >= 0) {
            min = (sommets.get(i).getIndice() < min) ? sommets.get(i).getIndice() : min;
        }
        return min;
    }

    /**
     * Fonction récupérant l'indice maximal de toutes les arêtes du graphe
     * @return
     */
    public int indiceMaxArete(){
        int i = aretes.size()-1;
        int max = aretes.get(0).getPoids();
        while(--i >= 0) {
            max = (aretes.get(i).getPoids() > max) ? aretes.get(i).getPoids() : max;
        }
        return max;
    }

    /**
     * Fonction récupérant l'indice minimal de toutes les aretes du graphe
     * @return
     */
    public int indiceMinArete(){
        int i = aretes.size() - 1;
        int min = aretes.get(0).getPoids();
        while(--i >= 0) {
            min = (aretes.get(i).getPoids() < min) ? aretes.get(i).getPoids() : min;
        }
        return min;
    }


    /**
     * Fonction permettant de changer la couleur de tous les sommet du graphe
     * en fonction d'une couleur minimale et d'une couleur maximale
     * et de la valeur de l'indice de chaque sommet
     * @param cmin
     * @param cmax
     */
    public void changerCouleurSommets(Color cmin, Color cmax){
        for (Sommet s : sommets) {
            changerCouleurSommet(s, cmin, cmax);
        }
    }

    /**
     * Fonction permettant de changer la couleur d'un sommet en fonction d'une couleur
     * minimale et d'une couleur maximale et de la valeur de l'indice du sommet
     * @param s
     * @param cmin
     * @param cmax
     */
    public void changerCouleurSommet (Sommet s, Color cmin, Color cmax){
        if (indiceFixe()) {
            int valeur = s.getIndice();
            double rouge = intensite(valeur, cmax.getRed(), cmin.getRed(), indiceMaxSommet(), indiceMinSommet());
            double vert = intensite(valeur, cmax.getGreen(), cmin.getGreen(), indiceMaxSommet(), indiceMinSommet());
            double bleu = intensite(valeur, cmax.getBlue(), cmin.getBlue(), indiceMaxSommet(), indiceMinSommet());
            s.setCouleurSommet(new Color(rouge, vert, bleu, 1.));
        }
    }

    /**
     * Fonction permettant de changer la couleur de toutes les aretes du graphe
     * en fonction d'une couleur minimale et d'une couleur maximale
     * et de la valeur du poids de chaque arete
     * @param cmin
     * @param cmax
     */
    public void changerCouleurAretes(Color cmin, Color cmax){
        for (Arete a : aretes) {
            changerCouleurArete(a, cmin, cmax);
        }
    }

    /**
     * Fonction permettant de changer la couleur d'une arete en fonction d'une couleur
     * minimale et d'une couleur maximale et de la valeur du poids de l'arête
     * @param s
     * @param cmin
     * @param cmax
     */
    public void changerCouleurArete (Arete s, Color cmin, Color cmax){
        if (indiceFixe()) {
            int valeur = s.getPoids();
            double rouge = intensite(valeur, cmax.getRed(), cmin.getRed(), indiceMaxArete(), indiceMinArete());
            double vert = intensite(valeur, cmax.getGreen(), cmin.getGreen(), indiceMaxArete(), indiceMinArete());
            double bleu = intensite(valeur, cmax.getBlue(), cmin.getBlue(), indiceMaxArete(), indiceMinArete());
            s.setCouleurArete(new Color(rouge, vert, bleu, 1.));
        }
    }


    /**
     * fonction mathematique permettant de generer une couleur pour une arete ou un sommet
     * en fonction de la valeur de son indice suivant un intervalle
     * @param valeur
     * @param cmax
     * @param cmin
     * @return
     */
    private double intensite(int valeur, double cmax, double cmin, int indiceMax, int indiceMin){
        return (((valeur - indiceMin)/(indiceMax - indiceMin)) * (cmax - cmin) + cmin);
    }


    public void changerTailleSommet(Sommet s, float maxSommet, float minSommet){
        if (indiceFixe()) {
            int valeur = s.getIndice();
            int largeur = (int) intensite(valeur, maxSommet, minSommet, indiceMaxSommet(), indiceMinSommet());
            Size taille = new Size(largeur, s.getTailleForme().height);
            s.setTailleForme(taille);
        }
    }

    public void changerTailleArete(Arete a, float maxArete, float minArete){
        if (indiceFixe()) {
            int valeur = a.getPoids();
            int largeur = (int) intensite(valeur, maxArete, minArete, indiceMaxArete(), indiceMinArete());
            Size taille = new Size(largeur, a.getEpaisseur().height);
            a.setEpaisseur(taille);
        }
    }

    public void changerTailleGraphe(float maxSommet, float minSommet, float maxArete, float minArete){
        for (Sommet s : sommets){
            changerTailleSommet(s, maxSommet, minSommet);
        }
        for (Arete a : aretes){
            changerTailleArete(a, maxArete, minArete);
        }
    }

    /**
     * Vérifie si l'indice du sommet ou de l'arete a été initialisé
     * @return
     */
    private boolean indiceFixe (){
        int i = 0;
        for (Sommet s : sommets){
            i += s.getIndice();
        }
        return (i == 0 ? false : true);
    }

    /**
     * Modifie la représentation du graphe suivant l'algorithme choisi
     * @param algorithme
     * @param largeurEcran
     */
    public void setAlgorithmeRepresentation(char algorithme, int largeurEcran){
        switch (algorithme){
            case 'a' :{
                algorithmeRepresentation.distributionAleatoire(largeurEcran);
                break;
            }
            case 'c': {
                algorithmeRepresentation.distributionCirculaire(largeurEcran);
                break;
            }
            case 'f': {
                algorithmeRepresentation.distributionModeleForces(largeurEcran);
                break;
            }
            default:
                break;
        }
    }

    // Accesseur et Mutateurs

    public static ArrayList<Sommet> getSommets() {

        return sommets;
    }

    public static void setSommets(ArrayList<Sommet> sommets) {

        Graphe.sommets = sommets;
    }

    public static ArrayList<Arete> getAretes() {

        return aretes;
    }

    public static void setAretes(ArrayList<Arete> aretes) {

        Graphe.aretes = aretes;
    }

    public static HashMap<Sommet, ArrayList<Arete>> getIncidentes() {

        return incidentes;
    }

    public static void setIncidentes(HashMap<Sommet, ArrayList<Arete>> incidentes) {

        Graphe.incidentes = incidentes;
    }

    public static HashMap<Arete, Pair<Sommet, Sommet>> getExtremites() {

        return extremites;
    }

    public static void setExtremites(HashMap<Arete, Pair<Sommet, Sommet>> extremites) {

        Graphe.extremites = extremites;
    }
    public static Size getTaille() {
        return taille;
    }

    public static void setTaille(Size taille) {
        Graphe.taille = taille;
    }

}
