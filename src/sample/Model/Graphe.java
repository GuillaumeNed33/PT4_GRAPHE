package sample.Model;

import com.sun.glass.ui.Size;
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
     * @param tag Représente le tag du sommet à ajouter dans le graphe.
     * @param coord_x_sommet Représente la coordonnée en x du sommet à ajouter dans le graphe.
     * @param coord_y_sommet Représente la coordonnée en y du sommet à ajouter dans le graphe.
     * @return Retourne vrai si l'ajout du sommet c'est fait ou faux dans le cas contraire.
     */
    public boolean ajouterSommet(Sommet sommet, Size tailleFenetre){

        if (verificationPossibiliteAjoutSommet(sommet.getX(), sommet.getY(), tailleFenetre)) {
            m_sommets.add(sommet);
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

            Pair<Sommet, Sommet> sommets = new Pair<>(sommet_1, sommet_2);

            m_extremites.put(arete, sommets);
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

        if (sommet_1.equals(sommet_2) && (sommet_1.equals(null) || sommet_2.equals(null))) {
            return false;
        }

        return true;
    }

    /**
     * Permet de lier l'arete à un sommet.
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
