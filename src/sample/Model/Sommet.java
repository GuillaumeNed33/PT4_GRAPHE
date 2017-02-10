package sample.Model;
/**
 * Created by audreylentilhac on 02/02/2017.
 */
public class Sommet {
    private String m_id;
    private float x;
    private float y;

    public Sommet(String name){
        this.m_id = name;
        this.x = 0;
        this.y = 0;
    }

    public Sommet(String name, float x, float y){
        this.m_id = name;
        this.x = x;
        this.y = y;
    }

    public String getM_id() {
        return m_id;
    }

    public void setM_id(String m_id) {
        this.m_id = m_id;
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
