package sample.Model;
/**
 * Created by audreylentilhac on 02/02/2017.
 */
public class Sommet {

    private static int m_curId = 0;
    private int m_id;
    private float x, y;

    public Sommet(boolean valide, float x, float y){
        if (valide) {
            m_id = m_curId++;
            this.x = x;
            this.y = y;
        }
        else
            m_id = -1;
    }

    public Sommet(String id){
            m_id = m_curId++;
    }

    public int id(){
        return m_id;
    }

    public boolean valide(){
        return (m_id != -1);
    }

    public void supprimer() {
        m_id=-1;
    }
}
