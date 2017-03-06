import Model.Forme_Sommet;
import Model.Sommet;
import com.sun.glass.ui.Size;
import javafx.scene.paint.Color;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mathieu on 06/02/2017.
 */
public class SommetTest {
    @Test
    public void testId() {

        Sommet sommet = new Sommet("UnNom");
        assertEquals(sommet.getId(),1,0);

        Sommet sommet1 = new Sommet(13);
        assertEquals(sommet1.getId(),13,0);
    }

    @Test
    public void testCreationSommet() {
        Sommet sommet= new Sommet("s1");
        assertEquals(sommet.getTag(),"s1");

        Color couleur = Color.web("000000");
        assertEquals(sommet.getCouleurSommet(), couleur);

        String size = new Size(10,10).toString();
        assertEquals(sommet.getTailleForme().toString(),size);
    }

    @Test
    public void testSetTag() {
        Sommet sommet= new Sommet("s1",10,10);
        assertEquals(sommet.getTag(),"s1");

        sommet.setTag("s2");
        assertEquals(sommet.getTag(),"s2");
    }

    @Test
    public void testSetCouleur() {
        Sommet sommet= new Sommet("s1",10,10);
        Color couleur = Color.web("000000");
        assertEquals(sommet.getCouleurSommet(), couleur);

        couleur = Color.web("010101");
        sommet.setCouleurSommet(couleur);
        assertEquals(sommet.getCouleurSommet(),couleur);
    }

    @Test
    public void testSetCoordonnees() {
        Sommet sommet= new Sommet("s1",10,10);
        assertEquals(sommet.getX(),10,0);
        assertEquals(sommet.getY(),10,0);

        sommet.setX(13);
        sommet.setY(15);
        assertEquals(sommet.getX(),13,0);
        assertEquals(sommet.getY(),15,0);
    }
    @Test
    public void testSetIndice() {
        Sommet sommet = new Sommet("s1",10,10);
        assertEquals(sommet.getIndice(),0);

        sommet.setIndice(5);
        assertEquals(sommet.getIndice(),5);
    }

    @Test
    public void testSetForme() {
        Sommet sommet = new Sommet("s1",10,10);
        assertEquals(sommet.getForme(), Forme_Sommet.Cercle);

        sommet.setForme(Forme_Sommet.Losange);
        assertEquals(sommet.getForme(),Forme_Sommet.Losange);
    }

    @Test
    public void testTaille() {
        Sommet sommet = new Sommet("UnSommet");
        assertEquals(sommet.getTailleForme().height,10,0);
        assertEquals(sommet.getTailleForme().width,10,0);

        Size size = new Size(15,15);
        sommet.setTailleForme(size);

        assertEquals(sommet.getTailleForme().height,15,0);
        assertEquals(sommet.getTailleForme().width,15,0);
    }
}