package sample;

/**
 * Created by audreylentilhac on 03/02/2017.
 */
public class Arete {
    private static int m_curId = 0;
    private int m_id;
    private Sommet entree;
    private Sommet sortie;

    public Arete(boolean valide, Sommet a, Sommet b){
        entree = a;
        sortie = b;
        if (valide == true)
            m_id = m_curId++;
        else
            m_id = -1;
    }

    public int id(){
        return m_id;
    }

    public boolean valide(){
        return (m_id != -1);
    }
}
