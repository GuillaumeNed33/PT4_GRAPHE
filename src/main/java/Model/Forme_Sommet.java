package Model;

/**
 * Enumération représentant les différentes formes de sommets.
 */
public enum Forme_Sommet {
    Rectangle, Cercle, Triangle, Losange;

    /**
     * Méthode permettant d'un string retourner un des éléments correspondant dans l'énumération.
     * @param texte Représente le texte à comparer pour déterminer la forme du sommet.
     * @return Retourne un des éléments de l'énumération.
     */
    public static Forme_Sommet getFormeViaTexte(String texte) {
        if (texte.equals("Rectangle")) {
            return Rectangle;
        } else if (texte.equals("Cercle")) {
            return Cercle;
        } else if (texte.equals("Triangle")) {
            return Triangle;
        } else if (texte.equals("Losange")) {
            return Losange;
        } else {
            return Cercle;
        }
    }
}
