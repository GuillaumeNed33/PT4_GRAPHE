import javafx.scene.paint.Color;
import org.junit.Test;
import Model.Arete;
import Model.Sommet;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mathieu on 06/02/2017.
 */
public class AreteTest {
    @Test
    public void testCreationArete() throws Exception {
        Sommet depart = new Sommet("s1",1,1);
        Sommet departB = new Sommet("s3",1,3);
        Sommet arrivee = new Sommet("s2",2,2);
        Sommet arriveeB = new Sommet("s4",2,4);


        Arete arete = new Arete(depart,arrivee);
        assertEquals(arete.id(),1);
        Arete areteB = new Arete(departB,arriveeB);
        assertEquals(areteB.id(),2);
    }

    @Test
    public void testCouleur() throws Exception {
        Sommet depart = new Sommet("s1",1,1);
        Sommet arrivee = new Sommet("s2",2,2);

        Color couleurArete = javafx.scene.paint.Color.web("000000");

        Arete arete= new Arete(depart,arrivee);
        assertEquals(arete.getCouleurArete(), couleurArete);

        Color couleur2 = javafx.scene.paint.Color.web("555555");

        arete.setCouleurArete(couleur2);
        assertEquals(arete.getCouleurArete(),couleur2);
    }

    @Test
    public void testPoids() throws Exception {
        Sommet depart = new Sommet("s1",1,1);
        Sommet arrivee = new Sommet("s2",2,2);

        int poids = 13;

        Arete arete = new Arete(depart,arrivee);
        assertEquals(arete.getPoids(),0);

        arete.setPoids(poids);
        assertEquals(arete.getPoids(),poids);

    }
}
