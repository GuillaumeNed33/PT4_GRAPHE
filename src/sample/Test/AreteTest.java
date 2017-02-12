package sample.Test;

import org.junit.Test;
import sample.Model.Arete;
import sample.Model.Sommet;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mathieu on 06/02/2017.
 */
public class AreteTest {
    @Test
    public void testAjoutArete() throws Exception {
        Sommet depart = new Sommet("s1",1,1);
        Sommet arrivee = new Sommet("s2",2,2);

        Arete arete = new Arete(depart,arrivee);
        assertEquals(arete.id(),0);
    }

    @Test
    public void testSuppressionArete() throws Exception {
        Sommet depart = new Sommet("s1",4,4);
        Sommet arrivee = new Sommet("s2",4,4);

        Arete arete = new Arete(depart,arrivee);
        arete.supprimer();

        assertEquals(arete.id(),-1);
    }
}