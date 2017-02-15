package Model;

import com.sun.glass.ui.Size;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

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
     * Représente l'ensemble des clefs de style (definies dans les fichier .graphml).
     */
    private static ArrayList<KeyStyleGRAPHML> m_keyGML;

    /**
     * Représente la marge entre chaque sommet du graphe.
     */
    private static final int MARGE = 10;

    /**
     *  Représente une valeur aléatoire.
     */
    private static Random rand = new Random();

    /**
     * Constructeur de la classe Graphe lisant un fichier .DOT ou .GRAPHML.
     * @param fichier
     */
    public Graphe (String fichier) {
        if (fichier.contains(".dot")) {
            chargerGrapheDOT(fichier);
        }
        else {
            chargerGrapheGRAPHML(fichier);
        }
    }

    /**
     * Constructeur par defaut de la classe Graphe.
     */
    public Graphe(){
        m_name = "Mon Joli Graphe";
        m_size = new Size(10,10);
        m_sommets = new ArrayList<Sommet>();
        m_aretes = new ArrayList<Arete>();
        m_incidentes = new HashMap<Sommet, ArrayList<Arete>>();
        m_extremites = new HashMap<Arete, Pair<Sommet, Sommet>>();
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
                if(ligne.contains("size=")) {
                    decoup = ligne.split("\"");
                    String [] decoup2 = decoup[1].split(",");
                    //m_size = new Size(Integer.parseInt(decoup2[0]), Integer.parseInt(decoup2[1]));
                    //  System.out.println(m_size);
                }

                //GESTION DES NOEUDS
                if(!ligne.contains(" -> ") &&
                        !ligne.contains(" -- ") &&
                        !ligne.contains("size")) {
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

                if(m_name == "graph") {
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
                    KeyStyleGRAPHML key = new KeyStyleGRAPHML(id,type, keyName, typeAttribute);
                    m_keyGML.add(key);
                }

                else if (ligne.contains("<graph")) {
                    String [] recupId = ligne.split("id=\"");
                    m_name = recupId[1].split("\"")[0];
                    String [] recupType = ligne.split("edgedefault=\"");
                    String typeArete = recupType[1].split("\"")[0];
                    if(typeArete=="undirected")
                        directedGraph = false;
                    else
                        directedGraph = true;
                }

                else if (ligne.contains("<node")) {

                    String [] recupId = ligne.split("id=\"");
                    String id = recupId[1].split("\"")[0];
                    Size tailleFenetre = new Size();
                    ajouterSommet(new Sommet(id), tailleFenetre);
                }

                else if (ligne.contains("<edge")) {
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

    private Sommet findSommet(String source) {
        boolean trouve = false;
        int i = 0;
        Sommet res = null;
        while(!trouve && i < m_sommets.size()) {
            if(m_sommets.get(i).getTag() == source) {
                trouve = true;
                res = m_sommets.get(i);
            }
            else
                i++;
        }
        return res;
    }

    /**
     *  Permet une distribution uniforme rectangulaire des sommets.
     */
    public void distributionAleatoire(int largeurEcran){
        for (Sommet sommet : m_sommets) {
            sommet.setX((rand.nextFloat()*largeurEcran)+MARGE);
            sommet.setY((rand.nextFloat()*largeurEcran)+MARGE);
        }

    }

    /**
     * Permet une distribution circulaire des sommets.
     */
    public void distributionCirculaire(int largeurEcran){
        for (Sommet sommet : m_sommets) {
            float rayon = rand.nextFloat() * m_size.width;
            double angle = rand.nextDouble() * 2 * Math.PI;
            sommet.setX((float)(((largeurEcran + rayon * Math.cos(angle))/2)+(2*Math.PI/m_sommets.size())));
            sommet.setY((float)(((largeurEcran + rayon * Math.sin(angle))/2)+(2*Math.PI/m_sommets.size())));
        }
    }

    /**
     * Permet une distribution par modele de Forces.
     */
    public void distributionModeleForces(int largeurEcran){
        distributionAleatoire(largeurEcran);
        for (Sommet sommet : m_sommets) {
            float forceTotale = forceAttraction(sommet) + forceRepulsion(sommet);
            sommet.setX(sommet.getX() + forceTotale);
            sommet.setY(sommet.getY() + forceTotale);
        }
    }

    /**
     * Fonction calculant la force d'attraction qu'exerce chacun des voisins u sur un sommet s.
     * f(s) = Somme (log distance(u,s)).
     * @param sommet
     * @return La force d'attraction
     */
    private float forceAttraction(Sommet sommet){
        ArrayList<Sommet> voisins = this.sommmetsVoisins(sommet);
        float force = 0;
        for (Sommet voisin : voisins) {
            double distance = Math.sqrt(Math.pow((double)(voisin.getX() - sommet.getX()), 2.) + Math.pow((double)(voisin.getY() - sommet.getY()), 2.));
            force += Math.log(distance);
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
        ArrayList<Sommet> voisins = this.sommetsNonVoisins(this.sommmetsVoisins(sommet));
        float force = 0;
        for (Sommet voisin : voisins) {
            double distanceCarre = Math.pow((double)(voisin.getX() - sommet.getX()), 2.) + Math.pow((double)(voisin.getY() - sommet.getY()), 2.);
            force += 1/distanceCarre;
        }
        return force;
    }

    /**
     * Permet d'obtenir les sommets voisins d'un sommet.
     * @param sommet_origine Représente le sommet de référence pour déterminer si les autres sommets sont voisins.
     * @return Retourne la liste des sommets voisins.
     */
    private ArrayList<Sommet> sommmetsVoisins(Sommet sommet_origine){
        ArrayList<Sommet> voisinage = null;
        for(Arete arete : m_incidentes.get(sommet_origine)){
            voisinage.add(source(arete).getTag() == sommet_origine.getTag() ? destination(arete) : source(arete));
        }
        return voisinage;
    }

    /**
     * Permet d'obtenir le sommet source d'une arête.
     * @param arete Représente l'arete sur laquelle on cherche le sommet source.
     * @return Retourne le sommet source.
     */
    private Sommet source(Arete arete){

        return m_extremites.get(arete).getKey();
    }

    /**
     * Permet d'obtenir le sommet destination d'une arête.
     * @param arete Représente l'arete sur laquelle on cherche le sommet destination.
     * @return Retourne le sommet de destination.
     */
    private Sommet destination(Arete arete){

        return m_extremites.get(arete).getValue();
    }

    /**
     * Permet d'obtenir la liste des sommets non voisins.
     * @param voisins Représente la liste de sommets de référence pour déterminer les sommets n'étant pas voisins.
     * @return Retourne la liste des sommets non voisins.
     */
    private ArrayList<Sommet> sommetsNonVoisins(ArrayList<Sommet> voisins){
        ArrayList<Sommet> nonVoisins = null;
        for (Sommet s: m_sommets) {
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
            m_sommets.add(sommet);
            m_incidentes.put(sommet, new ArrayList<Arete>());
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
    private boolean verificationPossibiliteAjoutSommet(float coord_x_sommet, float coord_y_sommet, Size tailleFentre) {

        if (verificationCoordonneesValide(coord_x_sommet, coord_y_sommet, tailleFentre)) {
            Iterator<Sommet> iterateur_sommet = m_sommets.iterator();
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

            if (m_incidentes.containsKey(sommet)) {
                for (Arete arete : m_incidentes.get(sommet)) {
                    m_extremites.remove(arete);
                    m_aretes.remove(arete);
                }

                m_incidentes.remove(sommet);
            }

            m_sommets.remove(sommet);
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
            m_aretes.add(arete);

            m_extremites.put(arete, new Pair<Sommet,Sommet>(sommet_1, sommet_2));
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

        for(Arete arete : m_incidentes.get(sommet_1)) {
            if (m_incidentes.get(sommet_2).contains(arete)) {
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

        if (!m_incidentes.containsKey(sommet)) {

            ArrayList<Arete> setArete = new ArrayList<Arete>();
            setArete.add(arete);
            m_incidentes.put(sommet, setArete);
        } else {
            m_incidentes.get(sommet).add(arete);
        }
    }

    /**
     * Permet de supprimer une arete du graphe.
     * @param arete Représente l'arete à supprimer.
     */
    public void supprimerArete(Arete arete) {

        if(!arete.equals(null)) {

            m_incidentes.get(m_extremites.get(arete).getKey()).remove(arete); // Représente le premier sommet de la Pair dans la HashMap m_extremites
            m_incidentes.get(m_extremites.get(arete).getValue()).remove(arete); // Représente le second sommet de la Pair dans la HashMap m_extremites

            m_extremites.remove(arete);
            m_aretes.remove(arete);
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
        s.setIndice(m_incidentes.get(s).size());
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
        for (Sommet s: m_sommets) {
            setIndiceDegre(s);
        }
    }

    /**
     *
     * Affecte pour chaque sommet du graphe une valeur aléatoire à son indice
     */
    public static void setIndiceAleatoire(){
        for (Sommet s: m_sommets) {
            setIndiceAleatoire(s);
        }
    }

    /**
     * Fonction récupérant l'indice maximal de tous les sommets du graphe
     * @return
     */
    private int indiceMaxSommet(){
        int i = m_sommets.size()-1;
        int max = m_sommets.get(0).getIndice();
        while(--i >= 0) {
            max = (m_sommets.get(i).getIndice() > max) ? m_sommets.get(i).getIndice() : max;
        }

        return max;
    }

    /**
     * Fonction récupérant l'indice minimal de tous les sommets du graphe
     * @return
     */
    private int indiceMinSommet(){
        int i = m_sommets.size() - 1;
        int min = m_sommets.get(0).getIndice();
        while(--i >= 0) {
            min = (m_sommets.get(i).getIndice() < min) ? m_sommets.get(i).getIndice() : min;
        }
        return min;
    }

    /**
     * Fonction récupérant l'indice maximal de toutes les arêtes du graphe
     * @return
     */
    private int indiceMaxArete(){
        int i = m_aretes.size()-1;
        int max = m_aretes.get(0).getPoids();
        while(--i >= 0) {
            max = (m_aretes.get(i).getPoids() > max) ? m_aretes.get(i).getPoids() : max;
        }
        return max;
    }

    /**
     * Fonction récupérant l'indice minimal de toutes les aretes du graphe
     * @return
     */
    private int indiceMinArete(){
        int i = m_aretes.size() - 1;
        int min = m_aretes.get(0).getPoids();
        while(--i >= 0) {
            min = (m_aretes.get(i).getPoids() < min) ? m_aretes.get(i).getPoids() : min;
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
        for (Sommet s : m_sommets) {
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
        int valeur = s.getIndice();
        double rouge = intensite(valeur, cmax.getRed(), cmin.getRed(), indiceMaxSommet(), indiceMinSommet());
        double vert =  intensite(valeur, cmax.getGreen(), cmin.getGreen(), indiceMaxSommet(), indiceMinSommet());
        double bleu = intensite(valeur, cmax.getBlue(), cmin.getBlue(), indiceMaxSommet(), indiceMinSommet());
        s.setCouleurSommet(new Color(rouge, vert, bleu, 1.));
    }

    /**
     * Fonction permettant de changer la couleur de toutes les aretes du graphe
     * en fonction d'une couleur minimale et d'une couleur maximale
     * et de la valeur du poids de chaque arete
     * @param cmin
     * @param cmax
     */
    public void changerCouleurAretes(Color cmin, Color cmax){
        for (Arete a : m_aretes) {
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
        int valeur = s.getPoids();
        double rouge = intensite(valeur, cmax.getRed(), cmin.getRed(), indiceMaxArete(), indiceMinArete());
        double vert =  intensite(valeur, cmax.getGreen(), cmin.getGreen(), indiceMaxArete(), indiceMinArete());
        double bleu = intensite(valeur, cmax.getBlue(), cmin.getBlue(), indiceMaxArete(), indiceMinArete());
        s.setCouleurArete(new Color(rouge, vert, bleu, 1.));
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
        int valeur = s.getIndice();
        int largeur = (int)intensite(valeur, maxSommet, minSommet, indiceMaxSommet(), indiceMinSommet());
        Size taille  = new Size(largeur, s.getTailleForme().height);
        s.setTailleForme(taille);
    }

    public void changerTailleArete(Arete a, float maxArete, float minArete){
        int valeur = a.getPoids();
        int largeur = (int)intensite(valeur, maxArete, minArete, indiceMaxArete(), indiceMinArete());
        Size taille  = new Size(largeur, a.getEpaisseur().height);
        a.setEpaisseur(taille);
    }

    public void changerTailleGraphe(float maxSommet, float minSommet, float maxArete, float minArete){
        for (Sommet s : m_sommets){
            changerTailleSommet(s, maxSommet, minSommet);
        }
        for (Arete a : m_aretes){
            changerTailleArete(a, maxArete, minArete);
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
