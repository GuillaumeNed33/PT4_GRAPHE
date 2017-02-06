package sample;

/**
 * Created by audreylentilhac on 02/02/2017.
 */
public class Sommet {

    private static int m_curId = 0;
    private int m_id;

    public Sommet(boolean valide){
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
