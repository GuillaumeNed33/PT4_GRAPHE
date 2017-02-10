package sample.Model;
/**
 * Created by audreylentilhac on 02/02/2017.
 */
public class Sommet {

    /**
     * Représente le tag du sommet.
     */
    private String m_tag;

    /**
     * Représente la coordonnée du sommet en x dans la fenêtre d'affichage.
     */
    private float x;

    /**
     * Représente la coordonnée du sommet en y dans la fenêtre d'affichage.
     */
    private float y;


    /**
     * Constructeur paramétré de la classe Sommet.
     * @param nom Représente le nom du sommet.
     */
    public Sommet(String nom) {
        this.m_tag = nom;
        this.x = 0;
        this.y = 0;
    }

    /**
     * Constructeur paramétré de la classe Sommet.
     * Initialise tous ces attibuts avec les paramètres.
     * @param tag Représente le tag du sommet.
     * @param x Représente la coordonnée en x du sommet.
     * @param y Représente la coordonnée en y du sommet.
     */
    public Sommet(String tag, float x, float y){
        this.m_tag = tag;
        this.x = x;
        this.y = y;
    }


    // Accesseur et Mutateurs

    public String getM_tag() {

        return m_tag;
    }

    public void setM_tag(String m_tag) {

        this.m_tag = m_tag;
    }

    public float getX() {

        return x;
    }

    public void setX(float x) {

        this.x = x;
    }

    public float getY() {

        return y;
    }

    public void setY(float y) {

        this.y = y;
    }
}
